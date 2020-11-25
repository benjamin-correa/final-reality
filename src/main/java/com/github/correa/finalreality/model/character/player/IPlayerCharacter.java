package com.github.correa.finalreality.model.character.player;

import com.github.correa.finalreality.model.weapon.IWeapon;

/**
 * This represents a player character from the game.
 * A player character can be controlled only by the player.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public interface IPlayerCharacter {

  /**
   * Return this character's equipped weapon.
   */
  IWeapon getEquippedWeapon();

  /**
   * Equips a weapon to the player character.
   */
  void equip(IWeapon weapon);

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
   * Returns this character hit points.
   */
  int getHitPoints();

  /**
   * Set's the character hit points.
   */
  void setHitPoints(int hitPoints);

  /**
   * Returns this character defense points.
   */
  int getDefensePoints();

  /**
   * Return's true if the character is alive.
   */
  boolean isAlive();
}
