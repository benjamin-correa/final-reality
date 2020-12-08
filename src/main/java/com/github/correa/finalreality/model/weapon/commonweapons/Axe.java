package com.github.correa.finalreality.model.weapon.commonweapons;

import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.enums.WeaponType;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Engineer;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Knight;
import com.github.correa.finalreality.model.weapon.AbstractWeapon;

import java.util.HashMap;
import java.util.Objects;

public class Axe extends AbstractWeapon {

  /**
   * Creates a new Axe class weapon.
   * @param name
   *  the name of the axe.
   * @param damage
   *  the damage of the axe.
   * @param weight
   *  the weight of the axe.
   */
  public Axe(String name, int damage, int weight) {
    super(name, damage, weight);
  }

  @Override
  public void equippedByEngineer(Engineer engineer) {
    engineer.equippedBy(this);
  }

  @Override
  public void equippedByKnight(Knight knight) {
    knight.equippedBy(this);
  }

  @Override
  public HashMap<Stats, String> getInfo() {
    super.getInfo();
    info.put(
        Stats.WEAPON_TYPE,
        String.valueOf(WeaponType.AXE));
    return info;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Axe)) {
      return false;
    }
    final Axe weapon = (Axe) o;
    return getName().equals(weapon.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
