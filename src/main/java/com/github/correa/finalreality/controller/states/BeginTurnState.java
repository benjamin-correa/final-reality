package com.github.correa.finalreality.controller.states;

import com.github.correa.finalreality.controller.GameController;
import com.github.correa.finalreality.enums.States;


/**
 * A class that has the methods of the begin turn state.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class BeginTurnState extends State{

  /**
   * Creates a new BeginTurnState class.
   *
   * @param controller
   *  The game controller.
   */
  public BeginTurnState(GameController controller) {
    super(controller);
  }

  @Override
  public void toOnTurnState() {
    controller.setState(new OnTurnState(controller));
  }

  @Override
  public void beginTurn() {
    controller.beginTurn();
    toOnTurnState();
  }

  @Override
  public String toString() {
    return States.BEGIN_TURN_STATE + "";
  }
}
