package com.github.correa.finalreality.controller.states;

import com.github.correa.finalreality.controller.GameController;
import com.github.correa.finalreality.enums.States;
import com.github.correa.finalreality.model.character.ICharacter;


/**
 * A class that has the methods of the selecting target state.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class SelectingTargetState extends State {

  /**
   * Creates a new SelectingTargetState class.
   *
   * @param controller The game controller.
   */
  public SelectingTargetState(GameController controller) {
    super(controller);
  }

  @Override
  public void toEndGameState() {
    controller.setState(
        new EndGameState(controller));
  }

  @Override
  public void endGame() {
    toEndGameState();
  }

  @Override
  public void toEndTurnState() {
    controller.setState(
        new EndTurnState(controller));
  }


  @Override
  public void attack(ICharacter attacker, ICharacter defender) {
    controller.attack(attacker, defender);
  }

  @Override
  public void setObjective(
      ICharacter objective) {
    controller.setObjective(objective);
  }

  @Override
  public String toString() {
    return States.SELECTING_TARGET_STATE + "";
  }
}
