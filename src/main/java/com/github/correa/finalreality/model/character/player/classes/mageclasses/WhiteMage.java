package com.github.correa.finalreality.model.character.player.classes.mageclasses;

import com.github.correa.finalreality.enums.CharacterType;
import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.classes.AbstractMage;
import com.github.correa.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class WhiteMage extends AbstractMage {

  /**
   * Creates a new White Mage class character.
   *
   * @param turnsQueue
   *  the queue with the characters waiting for their turn.
   * @param name
   *  the White Mage's name.
   * @param maxHitPoints
   *  the White Mage's maximum hit points.
   * @param defensePoints
   *  the White Mage's defense points.
   * @param maxManaPoints
   *  the mage's maximum mana points.
   */
  public WhiteMage(@NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, int maxHitPoints,
      int defensePoints, int maxManaPoints) {
    super(
        turnsQueue, name, maxHitPoints,
        defensePoints, maxManaPoints);
  }

  @Override
  public void attack(final @NotNull ICharacter opponent) {
    opponent.attackedByWhiteMage(this);
  }

  @Override
  public void equip(final @NotNull IWeapon weapon) {
    weapon.equippedByWhiteMage(this);
  }

  @Override
  public HashMap<Stats, String> getInfo() {
    super.getInfo();
    info.put(
        Stats.CHARACTER_TYPE,
        String.valueOf(CharacterType.WHITE_MAGE));
    return info;
  }

  @Override
  public String toString() {
    return super.toString() + CharacterType.WHITE_MAGE;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof WhiteMage)) {
      return false;
    }
    final WhiteMage that = (WhiteMage) o;
    return getName().equals(that.getName());
  }
}
