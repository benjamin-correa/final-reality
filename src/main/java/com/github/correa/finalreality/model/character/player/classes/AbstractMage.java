package com.github.correa.finalreality.model.character.player.classes;

import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.AbstractPlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

/**
 * Set of tests for the {@code GameCharacter} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */

public abstract class AbstractMage extends AbstractPlayerCharacter {

  private final int maxManaPoints;
  private int manaPoints;

  /**
   * Creates a new Mage character.
   *
   * @param turnsQueue
   *  the queue with the characters waiting for their turn.
   * @param name
   *  the mage's name.
   * @param maxHitPoints
   *  the mage's maximum hit points.
   * @param defensePoints
   *  the mage's defense points.
   * @param maxManaPoints
   *  the mage's maximum mana points.
   */
  public AbstractMage(
      @NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, int maxHitPoints,
      int defensePoints, int maxManaPoints) {
    super(turnsQueue, name, maxHitPoints, defensePoints);
    this.maxManaPoints = maxManaPoints;
    this.manaPoints = maxManaPoints;
  }

  /**
   * Sets the mage mana points.
   */
  public void setManaPoints(int manaPoints) {
    if (manaPoints >= maxManaPoints) {
      this.manaPoints = maxManaPoints;
    }
    else {
      this.manaPoints = Math.max(manaPoints, 0);
    }
  }

  /**
   * Returns the character's mana points.
   */
  public int getManaPoints() {
    return manaPoints;
  }

  /**
   * Returns the character's max mana points.
   */
  public int getMaxManaPoints() {
    return maxManaPoints;
  }

  /**
   * Returns the character's magic damage.
   */
  public int getMagicDamage() {
    if (getEquippedWeapon() != null) {
      return getEquippedWeapon().getMagicDamage();
    }
    else {
      return 0;
    }
  }

  @Override
  public HashMap<Stats, String> getInfo() {
    super.getInfo();
    info.put(Stats.MAX_MP, String.valueOf(getMaxManaPoints()));
    info.put(Stats.MP, String.valueOf(getManaPoints()));
    info.put(Stats.MAGIC_DMG, String.valueOf(getMagicDamage()));
    return info;
  }
}
