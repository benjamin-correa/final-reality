package com.github.correa.finalreality.model.weapon.magicweapons;

import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.model.weapon.AbstractWeapon;

import java.util.HashMap;

public abstract class AbstractMagicWeapon extends AbstractWeapon {

  private final int magicDamage;

  /**
   * Creates a new magic weapon.
   * @param name
   *  the weapon´s name.
   * @param damage
   *  the weapon´s damage.
   * @param magicDamage
   *  the weapon´s magic damage.
   * @param weight
   *  the weapon´s weight.
   */
  public AbstractMagicWeapon(
      String name, int damage,
      int magicDamage, int weight) {
    super(name, damage, weight);
    this.magicDamage = magicDamage;
  }

  @Override
  public int getMagicDamage(){
    return magicDamage;
  }

  @Override
  public HashMap<Stats, String> getInfo() {
    super.getInfo();
    info.put(
        Stats.MAGIC_DMG,
        String.valueOf(getMagicDamage()));
    return info;
  }
}
