package com.github.correa.finalreality.controller.states;

import com.github.correa.finalreality.controller.GameController;
import com.github.correa.finalreality.enums.States;


/**
 * A class that has the methods of the waiting state.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class WaitingState extends State {

  /**
   * Creates a new WaitState class.
   *
   * @param controller The game controller.
   */
  public WaitingState(GameController controller) {
    super(controller);
  }

  @Override
  public void toBeginTurnState() {
    controller.setState(new BeginTurnState(controller));
  }


  @Override
  public String toString() {
    return States.WAITING_STATE + "";
  }
}
