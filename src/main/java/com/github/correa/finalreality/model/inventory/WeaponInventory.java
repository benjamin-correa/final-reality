package com.github.correa.finalreality.model.inventory;

import com.github.correa.finalreality.model.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class that has all the weapons of the player.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */

public class WeaponInventory {

  private final List<Weapon> weaponInventory = new ArrayList<>();

  /**
   *Adds a Weapon to the WeaponInventory.
   */
  public void addWeapon(Weapon weapon){
    weaponInventory.add(weapon);
  }

  /**
   * Return's the actual WeaponInventory.
   */
  public List<Weapon> getWeaponInventory() {
    return List.copyOf(weaponInventory);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(WeaponInventory.class);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof WeaponInventory)) {
      return false;
    }
    final WeaponInventory weaponInventory = (WeaponInventory) o;
    return getWeaponInventory().equals(weaponInventory.getWeaponInventory());
  }
}
