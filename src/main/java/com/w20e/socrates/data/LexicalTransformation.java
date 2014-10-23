package com.w20e.socrates.data;

import java.util.Locale;

import com.w20e.socrates.expression.XObject;

/**
 * Lexical transformations are used to be able to provide the lexical space
 * value of a datatype. This may or may not involve a locale setting. For
 * currency or date representations for example, usually a locale setting will
 * be used, for string datatypes it won't.
 * 
 * @author dokter
 * 
 */
public interface LexicalTransformation {

	/**
	 * Transform the Object given the locale provided.
	 */
	XObject transform(XObject obj, Locale locale) throws TransformationException;
}
