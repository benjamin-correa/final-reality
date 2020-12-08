package com.github.correa.finalreality.model.character.player.classes.commonclasses;

import com.github.correa.finalreality.enums.CharacterType;
import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.correa.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
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
   * @param maxHitPoints
   *  the Knight's maximum hit points.
   * @param defensePoints
   *  the Knight's defense points.
   */
  public Knight(
      @NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, int maxHitPoints,
      int defensePoints) {
    super(turnsQueue, name,
        maxHitPoints, defensePoints);
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
  public HashMap<Stats, String> getInfo() {
    super.getInfo();
    info.put(
        Stats.CHARACTER_TYPE,
        String.valueOf(CharacterType.KNIGHT));
    return info;
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
