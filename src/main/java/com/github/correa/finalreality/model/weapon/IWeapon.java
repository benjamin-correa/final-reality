package com.github.correa.finalreality.model.weapon;

import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Engineer;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Knight;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Thief;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.BlackMage;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.WhiteMage;

import java.util.HashMap;

/**
 * This represents a weapon from the game.
 * A weapon can be equipped only by player character´s.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public interface IWeapon {

  /**
   * Returns this weapon's name.
   */
  String getName();

  /**
   * Returns this weapon's damage.
   */
  int getDamage();

  /**
   * Returns this weapon's weight.
   */
  int getWeight();

  /**
   * Returns the weapon's info.
   */
  HashMap<Stats, String> getInfo();

  /**
   * Returns this weapon's magic damage.
   */
  int getMagicDamage();

  /**
   * An Engineer tries to equip the weapon.
   */
  void equippedByEngineer(Engineer engineer);

  /**
   * A Knight tries to equip the weapon.
   */
  void equippedByKnight(Knight knight);

  /**
   * A Thief tries to equip the weapon.
   */
  void equippedByThief(Thief thief);

  /**
   * A Black Mage tries to equip the weapon.
   */
  void equippedByBlackMage(BlackMage blackMage);

  /**
   * An Engineer tries to equip the weapon.
   */
  void equippedByWhiteMage(WhiteMage whiteMage);
}
