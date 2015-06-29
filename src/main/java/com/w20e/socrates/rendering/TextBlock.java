package com.w20e.socrates.rendering;



/**
 * The TextBlock class adds the possibility of having arbitrary text in your pages.
 */
public class TextBlock extends RenderableImpl {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1505360129571705660L;

	/**
	 * Hold the text.
	 */
	private Translatable text = TranslatableImpl.EMPTY;
	
	public TextBlock(){

		super();
	}

	public TextBlock(final String id){
		
		super(id);
	}
	
	//@Override
	public String getType() {

		return "text";
	}

	public Translatable getText() {
	
		return this.text;
	}

	public void setText(Translatable newText) {
	
		this.text = newText;
	}

	public void setText(String newText) {
		
		this.text = new TranslatableImpl(newText);
	}

}
