package com.github.correa.finalreality.controller.states.exceptions;


/**
 * A class that has the basic information of the invalid transition exception.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class InvalidTransitionException extends Exception{

  /**
   * Throws a message if the controller tries call a method in a wrong state.
   */
  public InvalidTransitionException(final String message) {
    super(message);
  }
}
