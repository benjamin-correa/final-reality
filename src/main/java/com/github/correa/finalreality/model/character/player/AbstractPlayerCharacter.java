package com.github.correa.finalreality.model.character.player;

import com.github.correa.finalreality.model.character.AbstractCharacter;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.IPlayerCharacter;
import com.github.correa.finalreality.model.weapon.Weapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {

  private final CharacterClass characterClass;
  private Weapon equippedWeapon = null;

  /**
   * Creates a new player character.
   * @param turnsQueue
   *  the queue with the characters waiting for their turn.
   * @param name
   * the player character's name.
   * @param hitPoints
   *  the player character's hit points.
   * @param defensePoints
   *  the player character's defense points.
   * @param characterClass
   *  the player character's class.
   */
  public AbstractPlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                 @NotNull String name,
                                 int hitPoints, int defensePoints,
                                 CharacterClass characterClass) {
    super(turnsQueue, name, hitPoints, defensePoints);
    this.characterClass = characterClass;
  }

  @Override
  public void equip(Weapon weapon) { this.equippedWeapon = weapon; }

  @Override
  public Weapon getEquippedWeapon() { return equippedWeapon; }

  @Override
  public int getWeight() {return this.equippedWeapon.getWeight();  }

  @Override
  public CharacterClass getCharacterClass() {
    return characterClass;
  }
}
