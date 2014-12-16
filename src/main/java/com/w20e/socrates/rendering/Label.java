package com.w20e.socrates.rendering;

public class Label extends TranslatableImpl {

	public static final Label EMPTY = new Label("");
	
	public Label(String text) {
		super(text);
	}

	public Label() {
		super();
	}

}
