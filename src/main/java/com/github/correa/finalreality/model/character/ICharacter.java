package com.github.correa.finalreality.model.character;

import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.model.character.enemy.Enemy;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Engineer;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Knight;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Thief;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.BlackMage;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.WhiteMage;

import java.util.HashMap;

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
   * Returns the character's attack.
   */
  int getAttack();

  /**
   * Return this character's weight.
   */
  int getWeight();

  /**
   * Returns the character's max hp.
   */
  int getMaxHitPoints();

  /**
   * Returns the character's actual seed.
   */
  long getSeed();

  /**
   * Sets the character's actual seed.
   */
  void setSeed(final long seed);

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

  /**
   * A character attack another one.
   */
  void attack(ICharacter opponent);

  /**
   * Returns the character's info.
   */
  HashMap<Stats, String> getInfo();

  /**
   * A character receive an attack from an enemy.
   */
  void attackedByEnemy(Enemy enemy);

  /**
   * A character receive an attack from an engineer.
   */
  void attackedByEngineer(Engineer engineer);

  /**
   * A character receive an attack from a knight.
   */
  void attackedByKnight(Knight knight);

  /**
   * A character receive an attack from a Thief.
   */
  void attackedByThief(Thief thief);

  /**
   * A character receive an attack from a white mage.
   */
  void attackedByWhiteMage(WhiteMage whiteMage);

  /**
   * A character receive an attack from a black mage.
   */
  void attackedByBlackMage(BlackMage blackMage);
}
