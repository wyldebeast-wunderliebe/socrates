package com.w20e.socrates.rendering;

import java.util.Properties;

/**
 * The TextBlock class adds the possibility of having arbitrary text in your pages.
 */
public class TextBlock extends Properties implements Renderable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1505360129571705660L;

	/**
	 * Everything renderable needs to have an id.
	 */
	private String id;

	/**
	 * Hold the text.
	 */
	private String text;


	public TextBlock(final String newId){

		this.id = newId;
	}
	
	/**
	 * Return the id for this block;
	 */
	public String getId() {

		return this.id;
	}

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
