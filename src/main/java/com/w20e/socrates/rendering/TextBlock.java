package com.w20e.socrates.rendering;



/**
 * The TextBlock class adds the possibility of having arbitrary text in your pages.
 */
public class TextBlock extends RenderableImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1505360129571705660L;

	/**
	 * Hold the text.
	 */
	private String text;
	
	private String id;

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

	public String getText() {
	
		return this.text;
	}

	public void setText(String newText) {
	
		this.text = newText;
	}

}
