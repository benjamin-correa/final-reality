package com.github.correa.finalreality.model.weapon.magicweapons;

import com.github.correa.finalreality.model.character.player.classes.mageclasses.BlackMage;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.WhiteMage;

import java.util.Objects;

public class Staff extends AbstractMagicWeapon {
  /**
   * Creates a new Staff class weapon.
   * @param name
   *  the name of the staff.
   * @param damage
   *  the damage of the staff.
   * @param magicDamage
   *  the magic damage of the staff.
   * @param weight
   *  the weight of the staff.
   */
  public Staff(String name, int damage,
               int magicDamage, int weight) {
    super(name, damage, magicDamage, weight);
  }

  @Override
  public void equippedByBlackMage(BlackMage blackMage) {
    blackMage.equippedBy(this);
  }

  @Override
  public void equippedByWhiteMage(WhiteMage whiteMage) {
    whiteMage.equippedBy(this);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Staff)) {
      return false;
    }
    final Staff weapon = (Staff) o;
    return getName().equals(weapon.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getName());
  }
}
