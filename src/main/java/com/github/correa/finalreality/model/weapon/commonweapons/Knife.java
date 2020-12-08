package com.github.correa.finalreality.model.weapon.commonweapons;

import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.enums.WeaponType;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Knight;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Thief;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.BlackMage;
import com.github.correa.finalreality.model.weapon.AbstractWeapon;

import java.util.HashMap;
import java.util.Objects;

public class Knife extends AbstractWeapon {
  /**
   * Creates a new Knife class weapon.
   * @param name
   *  the name of the knife.
   * @param damage
   *  the damage of the knife.
   * @param weight
   *  the weight of the knife.
   */
  public Knife(String name, int damage, int weight) {
    super(name, damage, weight);
  }

  @Override
  public void equippedByThief(Thief thief) {
    thief.equippedBy(this);
  }

  @Override
  public void equippedByKnight(Knight knight) {
    knight.equippedBy(this);
  }

  @Override
  public void equippedByBlackMage(BlackMage blackMage) {
    blackMage.equippedBy(this);
  }

  @Override
  public HashMap<Stats, String> getInfo() {
    super.getInfo();
    info.put(
        Stats.WEAPON_TYPE,
        String.valueOf(WeaponType.KNIFE));
    return info;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Knife)) {
      return false;
    }
    final Knife weapon = (Knife) o;
    return getName().equals(weapon.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
