package com.github.correa.finalreality.model.character.player;

import com.github.correa.finalreality.model.character.AbstractCharacter;
import com.github.correa.finalreality.model.character.ICharacter;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import com.github.correa.finalreality.model.weapon.Weapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class PlayerCharacter extends AbstractCharacter {

  private final CharacterClass characterClass;
  private Weapon equippedWeapon = null;

  
  /**
   * Creates a new character.
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   * @param name
   *     the character's name
   * @param healthPoints
   *     the character's healthPoints
   * @param defensePoints
   *     the character's defensePoints
   * @param characterClass
   *     the class of this character
   */

  public PlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name,
      int healthPoints, int defensePoints,
      CharacterClass characterClass) {
    super(turnsQueue, name, healthPoints, defensePoints);
    this.characterClass = characterClass;
  }

  /**
   * Equips a weapon to the character.
   */
  public void equip(Weapon weapon) {
      this.equippedWeapon = weapon;
  }


  /**
   * Return this character's equipped weapon.
   */
  public Weapon getEquippedWeapon() {
    return equippedWeapon;
  }

  @Override
  public int getWeight() {return this.equippedWeapon.getWeight();  }

  /**
   * Returns this character's class.
   */
  public CharacterClass getCharacterClass() {
    return characterClass;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCharacterClass());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PlayerCharacter)) {
      return false;
    }
    final PlayerCharacter that = (PlayerCharacter) o;
    return getCharacterClass() == that.getCharacterClass()
        && getName().equals(that.getName());
  }


}
