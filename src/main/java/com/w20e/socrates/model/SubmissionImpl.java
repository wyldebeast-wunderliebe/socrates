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
 * File      : SubmissionImpl.java
 * Classname : SubmissionImpl
 * Author    : Duco Dokter
 * Created   : Thu Jan 27 14:57:07 2005
 * Version   : $Revision: 1.7 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model;

import java.io.Serializable;
import java.net.URI;

import com.w20e.socrates.model.Submission;

/**
 * Implementation class for Submission.
 */
public class SubmissionImpl implements Submission, Serializable {

  /**
   * Serialization uid.
   */
  private static final long serialVersionUID = -4250556627896959725L;

  /**
   * Holds the method for this submission.
   *
   */
  private String method;

  /**
   * Hold the action for this submission.
   *
   */
  private URI action;

  /**
   * Id for this submission.
   *
   */
  private String id;

  /**
   * Get the method for this submission.
   *
   * @return a <code>String</code> value
   */
  @Override
public final String getMethod() {

    return this.method;
  }

  /**
   * Set the method to be used for submission.
   *
   * @param newMethod a <code>String</code> value
   */
  public final void setMethod(final String newMethod) {

    this.method = newMethod;
  }

  /**
   * Get the Id for this submission.
   *
   * @return a <code>String</code> value
   */
  @Override
public final String getId() {

    return this.id;
  }

  /**
   * Set the id for this submission.
   *
   * @param newId a <code>String</code> value
   */
  public final void setId(final String newId) {

    this.id = newId;
  }

  /**
   * Get the action for this submission.
   *
   * @return a <code>URL</code> value
   */
  @Override
public final URI getAction() {

    return this.action;
  }

  /**
   * Set the action for this submission.
   *
   * @param newAction a <code>URI</code> value
   */
  @Override
public final void setAction(final URI newAction) {

    this.action = newAction;
  }
}
