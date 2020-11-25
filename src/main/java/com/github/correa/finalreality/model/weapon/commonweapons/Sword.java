package com.github.correa.finalreality.model.weapon.commonweapons;

import com.github.correa.finalreality.model.character.player.classes.commonclasses.Knight;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Thief;
import com.github.correa.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

public class Sword extends AbstractWeapon {

  /**
   * Creates a new Sword class weapon.
   * @param name
   *  the name of the sword.
   * @param damage
   *  the damage of the sword.
   * @param weight
   *  the weight of the sword.
   */
  public Sword(String name, int damage, int weight) {
    super(name, damage, weight);
  }

  @Override
  public void equippedByKnight(Knight knight) {
    knight.equippedBy(this);
  }

  @Override
  public void equippedByThief(Thief thief) {
    thief.equippedBy(this);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Sword)) {
      return false;
    }
    final Sword weapon = (Sword) o;
    return getName().equals(weapon.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
