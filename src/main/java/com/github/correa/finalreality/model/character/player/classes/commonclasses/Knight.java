package com.github.correa.finalreality.model.character.player.classes.commonclasses;

import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.correa.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class Knight extends AbstractPlayerCharacter {

  /**
   * Creates a new Knight class character.
   *
   * @param turnsQueue
   *  the queue with the characters waiting for their turn.
   * @param name
   *  the Knight's name.
   * @param hitPoints
   *  the Knight's hit points.
   * @param defensePoints
   *  the Knight's defense points.
   */
  public Knight(
      @NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, int hitPoints,
      int defensePoints) {
    super(turnsQueue, name,
        hitPoints, defensePoints);
  }

  @Override
  public void attack(final @NotNull ICharacter opponent){
    opponent.attackedByKnight(this);
  }

  @Override
  public void equip(final @NotNull IWeapon weapon) {
    weapon.equippedByKnight(this);
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
    if (!(o instanceof Knight)) {
      return false;
    }
    final Knight that = (Knight) o;
    return getName().equals(that.getName());
  }
}
