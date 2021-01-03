package com.github.correa.finalreality.controller.states;

import com.github.correa.finalreality.controller.GameController;
import com.github.correa.finalreality.enums.States;
import com.github.correa.finalreality.model.character.player.IPlayerCharacter;
import com.github.correa.finalreality.model.weapon.IWeapon;


/**
 * A class that has the methods of the on turn state.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class OnTurnState extends State {

  /**
   * Creates a new OnTurnState class.
   *
   * @param controller
   *  The game controller.
   */
  public OnTurnState(GameController controller) {
    super(controller);
  }

  @Override
  public void toSelectingTargetState() {
    controller.setState(new SelectingTargetState(controller));
  }

  @Override
  public void equip(
      IPlayerCharacter character, IWeapon weapon){
    controller.equip(character, weapon);
    if (character.getEquippedWeapon() != null){
      toSelectingTargetState();
    }
  }

  @Override
  public String toString() {
    return States.ON_TURN_STATE + "";
  }
}
