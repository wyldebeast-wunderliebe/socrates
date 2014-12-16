/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package com.w20e.socrates.factories;

import org.apache.commons.digester3.AbstractObjectCreationFactory;
import org.apache.commons.digester3.Digester;
import org.xml.sax.Attributes;

import com.w20e.socrates.rendering.OptionList;
import com.w20e.socrates.rendering.RenderConfig;

/**
 * @author dokter
 * Expression factory to create expressions based on XPath
 */
public class OptionsFactory extends AbstractObjectCreationFactory<OptionList> {

  /**
   * Create a new expression based on the input string, or null if no
   * input string.
   * @param expr String to parse
   * @return the expression created.
   * @throws Exception in case the compiler can't parse.
   */
  public final OptionList createObject(final Attributes attrs)
  throws Exception {

	  Digester dig = this.getDigester();
	  
	  RenderConfig rConfig = dig.getRoot();

	  return rConfig.getOptionList(attrs.getValue("ref"));
  }
  
}
