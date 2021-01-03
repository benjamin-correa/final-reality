package com.github.correa.finalreality.controller.states;

import com.github.correa.finalreality.controller.GameController;
import com.github.correa.finalreality.enums.States;


/**
 * A class that has the methods of the end turn state.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class EndTurnState extends State{

  /**
   * Creates a new EndTurnState class.
   *
   * @param controller The game controller.
   */
  public EndTurnState(GameController controller) {
    super(controller);
  }

  @Override
  public void endTurn() {
    controller.endTurn();
    toBeginTurnState();
  }

  @Override
  public void toBeginTurnState() {
    if (controller.isQueueEmpty()){
      controller.setState(new WaitingState(controller));
    }
    else {
      controller.setState(new BeginTurnState(controller));
    }
  }

  @Override
  public String toString() {
    return States.END_TURN_STATE + "";
  }
}
