package com.github.correa.finalreality.controller.states;


import com.github.correa.finalreality.controller.GameController;
import com.github.correa.finalreality.controller.states.exceptions.InvalidMovementException;
import com.github.correa.finalreality.controller.states.exceptions.InvalidTransitionException;
import com.github.correa.finalreality.enums.States;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.IPlayerCharacter;
import com.github.correa.finalreality.model.weapon.IWeapon;

/**
 * A class that has the basic components of a state.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class State {
  protected GameController controller;

  /**
   * Creates a new State class.
   * @param controller
   *  The game controller.
   */
  public State(GameController controller) {
    this.controller = controller;
  }

  /**
   * Starts the game.
   */
  public void startGame() throws InvalidMovementException {
    throw new InvalidMovementException(
        "Can't start game on " + this.toString() + ".");
  }

  /**
   * The state where the turn begins.
   */
  public void toBeginTurnState() throws InvalidTransitionException{
    throw new InvalidTransitionException(
        "Can't change from " + this.toString() + " to " + States.BEGIN_TURN_STATE);
  }

  /**
   * Begins the character's turn.
   */
  public void beginTurn() throws InvalidMovementException {
    throw new InvalidMovementException(
        "Can't begin turn on " + this.toString() + ".");
  }

  /**
   * The state where the character can equip things.
   */
  public void toOnTurnState() throws InvalidTransitionException {
    throw new InvalidTransitionException(
        "Can't change from " + this.toString() + " to " + States.ON_TURN_STATE);
  }

  /**
   * Equips a weapon to the character.
   */
  public void equip(
      IPlayerCharacter character, IWeapon weapon)
      throws InvalidMovementException {
    throw new InvalidMovementException(
        "Can't equip on " + this.toString() + ".");
  }

  /**
   * The state where the character choose an objective.
   */
  public void toSelectingTargetState() throws InvalidTransitionException {
    throw new InvalidTransitionException(
        "Can't change from " + this.toString() + " to " + States.SELECTING_TARGET_STATE);
  }

  /**
   * Sets the characters objective
   */
  public void setObjective(
      ICharacter objective) throws InvalidMovementException {
    throw new InvalidMovementException(
        "Can't set objective on " + this.toString() + ".");
  }

  /**
   * The state where the character attacks the objective.
   */
  public void attack(
      ICharacter attacker, ICharacter defender)
      throws InvalidMovementException {
    throw new InvalidMovementException(
        "Can't attack on " + this.toString() + ".");
  }

  /**
   * Changes to end game state.
   */
  public void toEndGameState() throws InvalidTransitionException {
    throw new InvalidTransitionException(
        "Can't change from " + this.toString() + " to " + States.END_GAME_STATE);
  }

  /**
   * The state where the game ends.
   */
  public void endGame() throws InvalidMovementException {
    throw new InvalidMovementException(
        "Can't end turn on " + this.toString() + ".");
  }

  /**
   * Changes to end turn state.
   */
  public void toEndTurnState() throws InvalidTransitionException {
    throw new InvalidTransitionException(
        "Can't change from " + this.toString() + " to " + States.END_TURN_STATE);
  }

  /**
   * The state where the character ends his turn.
   */
  public void endTurn() throws InvalidMovementException {
    throw new InvalidMovementException(
        "Can't end turn on " + this.toString() + ".");
  }

}
