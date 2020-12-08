package com.github.correa.finalreality.model.character.player;

import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.weapon.IWeapon;

/**
 * This represents a player character from the game.
 * A player character can be controlled only by the player.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public interface IPlayerCharacter extends ICharacter {

  /**
   * Return this character's equipped weapon.
   */
  IWeapon getEquippedWeapon();

  /**
   * Equips a weapon to the player character.
   */
  void equip(IWeapon weapon);

  /**
   * Set the equipped weapon to null.
   */
  void unequip();

}
