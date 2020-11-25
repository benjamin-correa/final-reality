package com.github.correa.finalreality.model.weapon.commonweapons;

import com.github.correa.finalreality.model.character.player.classes.commonclasses.Engineer;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Thief;
import com.github.correa.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

public class Bow extends AbstractWeapon {
  /**
   * Creates a new Bow class weapon.
   * @param name
   *  the name of the bow.
   * @param damage
   *  the damage of the bow.
   * @param weight
   *  the weight of the bow.
   */
  public Bow(String name, int damage, int weight) {
    super(name, damage, weight);
  }

  @Override
  public void equippedByEngineer(Engineer engineer) {
    engineer.equippedBy(this);
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
    if (!(o instanceof Bow)) {
      return false;
    }
    final Bow weapon = (Bow) o;
    return getName().equals(weapon.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
