package com.github.cc3002.finalreality.model.Inventory;

import com.github.correa.finalreality.model.Inventory.WeaponInventory;
import com.github.correa.finalreality.model.weapon.Weapon;
import com.github.correa.finalreality.model.weapon.WeaponType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponWeaponInventoryTest {
  private WeaponInventory testWeaponInventory;

  @BeforeEach
  void setUp() { testWeaponInventory = new WeaponInventory(); }

  @Test
  void basicTest(){
    var expectedWeaponInventory = new WeaponInventory();
    var sameWeaponInventory = testWeaponInventory;
    assertEquals(expectedWeaponInventory, testWeaponInventory);
    assertEquals(expectedWeaponInventory.hashCode(), testWeaponInventory.hashCode());
    assertNotEquals(testWeaponInventory, new Object());
    assertEquals(sameWeaponInventory, testWeaponInventory);
  }

  @Test
  void InventoryTest(){
    assertTrue(testWeaponInventory.getWeaponInventory().isEmpty());
    for (int i= 0; i < 10; i++) {
      testWeaponInventory.addWeapon(
          new Weapon("Test weapon", 10, 10, WeaponType.AXE));
      assertEquals(i+1, testWeaponInventory.getWeaponInventory().size());
    }
  }
}
