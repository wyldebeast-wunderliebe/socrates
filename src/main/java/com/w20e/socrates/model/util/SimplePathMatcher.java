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
 * File      : SimplePathMatcher.java
 * Classname : SimplePathMatcher
 * Author    : Duco Dokter
 * Date      : 17 Jan 2005
 * Version   : $Revision: 1.20 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import com.w20e.socrates.data.Node;

/**
 * Simple matching algorithm for path expressions. This algorithm can only
 * expand the '*' symbol for now.
 */
public final class SimplePathMatcher implements PathMatcher {

  /**
   * Path seperator that is used in path expressions.
   */
  public static final String PATH_SEPARATOR = "/";

  /**
   * Hold singleton instance.
   */
  private static SimplePathMatcher instance = null;

  /**
   * Privatize constructor.
   */
  private SimplePathMatcher() {
	  // Empty constructor.
  }

  /**
   * Get an instance of the matcher.
   * @return the instance.
   */
  public static synchronized SimplePathMatcher getInstance() {
    if (SimplePathMatcher.instance == null) {
      SimplePathMatcher.instance = new SimplePathMatcher();
    }
    return SimplePathMatcher.instance;
  }

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
  @Override
public List<Node> match(final String pathExpr, final Collection<Node> list) {

    List<Node> results = new ArrayList<Node>();

    Pattern pat = createPattern(pathExpr);

    for (Node n : list) {

      if (pat.matcher(n.toString()).matches()) {

        results.add(n);
      }
    }

    return results;
  }

  /**
   * Check whether the path expression matches the given string.
   *
   * @param pathExpr
   *          the expression to match.
   * @param path
   *          The path to use.
   * @return true or false, stating whether a pathExpr matches
   *          the requested path
   */
  @Override
public boolean matches(final String pathExpr, final String path) {

    return (createPattern(pathExpr).matcher(path).matches());
  }

  /**
   * Create a regular expression string from a Unix style path.
   * @param expr a path expression to reformat
   * @return a reformatted query string (path expression)
   */
  private Pattern createPattern(final String expr) {

    String pathExpr = expr;

    if (!pathExpr.startsWith("/")) {
      pathExpr = "/" + pathExpr;
    }

    String pat = Pattern.compile("\\*").matcher(pathExpr).replaceAll(".*");
    pat = Pattern.compile("^/").matcher(pat).replaceAll("/?");

    // Anchor to beginning of string
    pat = "^" + pat;

    // Remove trailing sep
    pat = Pattern.compile("\\/$").matcher(pat).replaceAll("");

    return Pattern.compile(pat);
  }

  /**
   * Find first match and return it. If no match is found, return null.
   * @param pathExpr expression to match with.
   * @param list list of objects to use.
   * @return macthing object
   */
  @Override
public Node matchFirst(final String pathExpr, final List<Node> list) {

    Pattern pat = createPattern(pathExpr);

    Node n;

    for (Iterator<Node> i = list.iterator(); i.hasNext();) {

      n = i.next();

      if (pat.matcher(n.toString()).matches()) {

        return n;
      }
    }

    return null;
  }

  /**
   * Check whether the expression actually contains wildcards. These are
   * * and ?.
   * @param pathExpr expression to test.
   * @return whether the expression contains wildcards
   */
  @Override
public boolean containsWildcard(final String pathExpr) {

    if (pathExpr.indexOf('*') != -1 || pathExpr.indexOf('?') != -1) {
      return true;
    }
    return false;
  }
}
