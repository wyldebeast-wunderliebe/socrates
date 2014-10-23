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

package com.w20e.socrates.workflow;

import org.apache.commons.digester.AbstractObjectCreationFactory;
import org.xml.sax.Attributes;

/**
 * Factory for creating Mapping objects.
 *
 * @author <a href="mailto:dokter@wyldebeast">Duco Dokter</a>
 * @version 1.0
 */
public class MappingFactory
  extends AbstractObjectCreationFactory {

  /**
   * Create a new Mapping object. This will only work if the
   * attributes provided as argument contains a non-null, non-empty
   * value for 'from', 'to' and 'condition'.
   *
   * @param attrs an <code>Attributes</code> value
   * @return an <code>Object</code> value
   * @exception Exception if an error occurs
   */
  public final Object createObject(final Attributes attrs)
    throws Exception {

    if (attrs.getValue("from") == null
        ||
        attrs.getValue("to") == null
        ||
        attrs.getValue("condition") == null
        ||
        "".equals(attrs.getValue("from"))
        ||
        "".equals(attrs.getValue("to"))
        ||
        "".equals(attrs.getValue("condition"))
        ) {
      throw new Exception("No from, to or condition specified!");
    }

    return new Mapping(
                       ActionFactory.getAction(attrs.getValue("from")),
                       ActionFactory.getAction(attrs.getValue("to")),
                       attrs.getValue("condition")
                       );
  }
}
