package com.github.correa.finalreality.model.Inventory;

import com.github.correa.finalreality.model.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WeaponInventory {
  private final List<Weapon> weaponInventory = new ArrayList<>();

  public void addWeapon(Weapon weapon){
    weaponInventory.add(weapon);
  }

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
