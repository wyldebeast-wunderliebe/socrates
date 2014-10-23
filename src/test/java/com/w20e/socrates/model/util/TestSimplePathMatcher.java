/*
 * File      : TestSimplePathMatcher.java
 * Classname : TestSimplePathMatcher
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.8 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model.util;


import com.w20e.socrates.model.util.SimplePathMatcher;

import junit.framework.TestCase;

public class TestSimplePathMatcher extends TestCase {

  private SimplePathMatcher matcher;

  public TestSimplePathMatcher(String name) {
    super(name);
  }

  public void setUp() {

    this.matcher = SimplePathMatcher.getInstance();
  }

  public void testMatches() {

      assertTrue(this.matcher.matches("/*", "/a"));
      assertTrue(this.matcher.matches("/a/*", "/a/b"));
      assertTrue(this.matcher.matches("a/*", "/a/b"));
  }

  
  public void testContainsWildcard() {
    assertTrue(this.matcher.containsWildcard("*/*"));
    assertTrue(this.matcher.containsWildcard("*/?"));
    assertTrue(this.matcher.containsWildcard("a/*"));
    assertFalse(this.matcher.containsWildcard("a/a"));
  }
}
