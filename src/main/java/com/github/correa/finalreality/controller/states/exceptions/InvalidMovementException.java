package com.github.correa.finalreality.controller.states.exceptions;


/**
 * A class that has the basic information of the invalid movement exception.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class InvalidMovementException extends Exception {

  /**
   * Throws a message if the controller tries call a method in a wrong state.
   */
  public InvalidMovementException(final String message) {
    super(message);
  }
}
