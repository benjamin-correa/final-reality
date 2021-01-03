package com.github.correa.finalreality.controller.states;

import com.github.correa.finalreality.controller.GameController;
import com.github.correa.finalreality.enums.States;

/**
 * A class that has the methods of the start game state.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class StartGameState extends State {

  /**
   * Creates a new StartGameState class.
   *
   * @param controller
   *  The game controller.
   */
  public StartGameState(GameController controller) {
    super(controller);
  }

  @Override
  public void startGame() {
    controller.startGame();
    toBeginTurnState();
  }

  @Override
  public void toBeginTurnState() {
    controller.setState(new BeginTurnState(controller));
  }

  @Override
  public String toString() {
    return States.START_GAME_STATE + "";
  }
}
