package com.github.correa.finalreality.model.character.player.classes;

import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.CharacterClass;
import com.github.correa.finalreality.model.character.player.AbstractPlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * Set of tests for the {@code GameCharacter} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */

public abstract class Mage extends AbstractPlayerCharacter {

  private final int manaPoints;

  /**
   * Creates a new Mage character.
   *
   * @param turnsQueue
   *  the queue with the characters waiting for their turn.
   * @param name
   *  the mage's name.
   * @param hitPoints
   *  the mage's hit points.
   * @param defensePoints
   *  the mage's defense points.
   * @param manaPoints
   *  the mage's mana points.
   * @param characterClass
   *  the mage's class.
   */

  public Mage(@NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, int hitPoints,
      int defensePoints, int manaPoints, CharacterClass characterClass) {
    super(turnsQueue, name, hitPoints, defensePoints, characterClass);
    this.manaPoints = manaPoints;
  }

  public int getManaPoints(){
    return manaPoints;
  }
}
