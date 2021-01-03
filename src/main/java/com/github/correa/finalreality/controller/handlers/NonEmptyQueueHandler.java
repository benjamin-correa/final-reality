package com.github.correa.finalreality.controller.handlers;

import com.github.correa.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;

/**
 * A class that handle the notification when a character enters to the queue.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class NonEmptyQueueHandler implements IEventHandler{
  private final GameController controller;

  /**
   * Creates a new non empty queue handler.
   * @param controller
   *  the controller that will update the handler.
   */
  public NonEmptyQueueHandler(final GameController controller) {
    this.controller = controller;
  }

  /**
   * This method gets called when a bound property is changed.
   *
   * @param evt
   *     A PropertyChangeEvent object describing the event source
   *     and the property that has changed.
   */
  @Override
  public void propertyChange(final PropertyChangeEvent evt) {
    controller.tryToGoToBeginTurnState();
  }
}
