package com.github.correa.finalreality.controller.states;

import com.github.correa.finalreality.controller.GameController;
import com.github.correa.finalreality.enums.States;

/**
 * A class that has the methods of the end game state.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class EndGameState extends State{

  /**
   * Creates a new EndGameState class.
   *
   * @param controller
   *  The game controller.
   */
  public EndGameState(GameController controller) {
    super(controller);
  }

  @Override
  public String toString() {
    return States.END_GAME_STATE + "";
  }
}
