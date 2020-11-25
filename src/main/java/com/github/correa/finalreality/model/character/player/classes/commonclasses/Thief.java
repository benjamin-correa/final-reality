package com.github.correa.finalreality.model.character.player.classes.commonclasses;

import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.correa.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class Thief extends AbstractPlayerCharacter {

  /**
   * Creates a new Thief class character.
   *
   * @param turnsQueue
   *  the queue with the characters waiting for their turn.
   * @param name
   *  the Thief's name.
   * @param hitPoints
   *  the Thief's hit points.
   * @param defensePoints
   *  the Thief's defense points.
   */
  public Thief(
      @NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, int hitPoints,
      int defensePoints) {
    super(
        turnsQueue, name,
        hitPoints, defensePoints);
  }

  @Override
  public void attack(final @NotNull ICharacter opponent){
    opponent.attackedByThief(this);
  }

  @Override
  public void equip(final @NotNull IWeapon weapon) {
    weapon.equippedByThief(this);
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
    if (!(o instanceof Thief)) {
      return false;
    }
    final Thief that = (Thief) o;
    return getName().equals(that.getName());
  }
}
