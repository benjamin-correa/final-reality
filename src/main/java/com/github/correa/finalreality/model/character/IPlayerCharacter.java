package com.github.correa.finalreality.model.character;

import com.github.correa.finalreality.model.character.player.CharacterClass;
import com.github.correa.finalreality.model.weapon.Weapon;

/**
 * This represents a player character from the game.
 * A player character can be controlled only by the player.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public interface IPlayerCharacter {

  /**
   * Equips a weapon to the character.
   */
  void equip(Weapon weapon);

  /**
   * Return this character's equipped weapon.
   */
  Weapon getEquippedWeapon();

  /**
   * Returns this character's class.
   */
  CharacterClass getCharacterClass();
}
