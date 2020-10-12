package com.github.correa.finalreality.model.character;

import com.github.correa.finalreality.model.character.player.CharacterClass;
import com.github.correa.finalreality.model.weapon.Weapon;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

  /**
   * Adds this character to the turns queue.
   */
  void addToQueue();

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * Returns this character health points.
   */
  int getHealthPoints();

  /**
   * Returns this character defense points.
   */
  int getDefensePoints();
}
