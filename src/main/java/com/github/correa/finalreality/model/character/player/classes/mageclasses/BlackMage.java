package com.github.correa.finalreality.model.character.player.classes.mageclasses;

import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.classes.AbstractMage;
import com.github.correa.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class BlackMage extends AbstractMage {

  /**
   * Creates a new Black Mage class character.
   *
   * @param turnsQueue
   *  the queue with the characters waiting for their turn.
   * @param name
   *  the Black Mage's name.
   * @param hitPoints
   *  the Black Mage's hit points.
   * @param defensePoints
   *  the Black Mage's defense points.
   */
  public BlackMage(
      @NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, int hitPoints,
      int defensePoints, int manaPoints) {
    super(
        turnsQueue, name, hitPoints,
        defensePoints, manaPoints);
  }

  @Override
  public void attack(final @NotNull ICharacter opponent){
    opponent.attackedByBlackMage(this);
  }

  @Override
  public void equip(final @NotNull IWeapon weapon) {
    weapon.equippedByBlackMage(this);
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
    if (!(o instanceof BlackMage)) {
      return false;
    }
    final BlackMage that = (BlackMage) o;
    return getName().equals(that.getName());
  }
}
