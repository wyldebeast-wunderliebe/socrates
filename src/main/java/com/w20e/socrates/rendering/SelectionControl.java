package com.w20e.socrates.rendering;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * The SelectionControl can be used as a base class for those types that
 * actually provide a vocabulary of options to choose from.
 * 
 * @author dokter
 * 
 */
public abstract class SelectionControl extends ControlImpl implements Vocabulary {

    /**
     * Default serial id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default reference value.
     */
    public static final String DEFAULT_OPTION_LIST = "default_option_list";

    private Map<String, OptionList> optionlists;

    /**
     * Create new SelectionControl
     * @param newId
     */
    public SelectionControl(String newId) {

        super(newId);
        this.optionlists = new HashMap<String, OptionList>();
        this.addOptions(new OptionList());
    }
    
    /**
     * Add an option to the item.
     * 
     * @param opt
     *            option to add.
     */
    public final void addOption(final Option opt) {
        this.optionlists.get(SelectionControl.DEFAULT_OPTION_LIST).add(opt.getValue(), opt);
    }

    /**
     * Get all options for this item.
     * 
     * @return options.
     */
    @Override
	public final Collection<Option> getOptions() {
        return this.getOptions(SelectionControl.DEFAULT_OPTION_LIST);
    }

    /**
     * Get the option list for the given reference value.
     * @param refvalue
     * @return
     */
    @Override
	public Collection<Option> getOptions(String refvalue) {

    	return this.optionlists.get(refvalue).getOptions();
    }

    
    /**
     * Set options for this item.
     */
    public final void addOptions(OptionList newOptions) {

        this.optionlists.put(newOptions.getRefValue(), newOptions);
    }

    /**
     * Implement vocabulary like behavior for the OptionList.
     * 
     * @param value
     *            value to get label for.
     * @return String (nice) presentation for this option.
     */
    public final String getOptionLabel(String value) {

    	if (this.optionlists.get(SelectionControl.DEFAULT_OPTION_LIST).contains(value)) {
            return this.optionlists.get(SelectionControl.DEFAULT_OPTION_LIST).get(value).getLabel();
    	} else {
    		return value;
    	}
    }

    /**
     * Implement vocabulary like behavior for the OptionList.
     * 
     * @param value
     *            value to get label for.
     * @return String (nice) presentation for this option.
     */
    public final String getOptionLabel(String value, String ref) {

    	if (this.optionlists.get(ref).contains(value)) {
            return this.optionlists.get(ref).get(value).getLabel();
    	} else {
    		return value;
    	}
    }

    /**
     * Return vocabulary value.
     */
    @Override
	public final Object getDisplayValue(Object value, Class<?> datatype, Locale locale) {

        if (value == null) {
            return "";
        }
        
        try {
            return getOptionLabel(value.toString());
        } catch (Exception e) {
            return value;
        }
    }

    /**
     * Return vocabulary value.
     */
    public final Object getDisplayValue(Object value, Class<?> datatype, Locale locale, String ref) {

        if (value == null) {
            return "";
        }
        
        try {
            return getOptionLabel(value.toString(), ref);
        } catch (Exception e) {
            return value;
        }
    }
    
    /**
     * Return node reference descriptor for the node upon which this control
     * depends for it's vocabulary.
     * @return
     */
    @Override
	public String getNodeRef() {

        return this.getProperty("noderef");
    }

}
