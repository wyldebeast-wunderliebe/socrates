/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 * File      : PathMatcher.java
 * Classname : PathMatcher
 * Author    : Duco Dokter
 * Date      : 17 Jan 2005
 * Version   : $Revision: 1.9 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model.util;

import java.util.Collection;
import java.util.List;

import com.w20e.socrates.data.Node;

/**
 * @author dokter
 *
 * PathMatcher interface.
 */
public interface PathMatcher {

  /**
   * Check List for keys that match the path expression, and return the results
   * as a List. Pattern matching is simply based on the '*' symbol. This method
   * assumes that a 'toString' method can be used on the List object.
   *
   * @param pathExpr
   *          the expression to match.
   * @param list
   *          The list to use for keys.
   * @return a list of objects requested through a pathExpr.
   */
  List<Node> match(String pathExpr, Collection<Node> list);

  /**
   * Check Map for keys that match the path expression, and return the results
   * as a List.
   *
   * @param pathExpr
   *          the expression to match.
   * @param map
   *          The map to use for keys.
   * @return a list of objects requested through a pathExpr.
   */
  //List match(String pathExpr, Map<String, List> map);

  /**
   * Check whether the path expression matches the given string.
   *
   * @param pathExpr
   *          the expression to match.
   * @param path
   *          The string to use.
   * @return true or false, stating whether a pathExpr matches
   *          the requested path
   */
  boolean matches(String pathExpr, String path);

  /**
   * Return first match of pattern in list.
   * @param pathExpr expression fpr matching
   * @param list list of objects to test for matching
   * @return the first matching item
   */
  Node matchFirst(String pathExpr, List<Node> list);

  /**
   * Check whether the expression actually contains wildcards according
   * to the matching algorithm. If not, some implementations might decide
   * not to use pattern matching at all.
   * @param pathExpr expression to test.
   * @return whether the expression contains wildcards
   */
  boolean containsWildcard(String pathExpr);
}
