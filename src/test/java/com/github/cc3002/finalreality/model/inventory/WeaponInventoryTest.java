package com.github.cc3002.finalreality.model.inventory;

import com.github.correa.finalreality.model.inventory.WeaponInventory;
import com.github.correa.finalreality.model.weapon.commonweapons.Axe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Set of tests for the WeaponInventory class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class WeaponInventoryTest {
  private WeaponInventory testWeaponInventory;

  @BeforeEach
  void setUp() { testWeaponInventory = new WeaponInventory(); }

  /**
   * Checks that the weapon inventory constructor and equals method works properly.
   */
  @Test
  void constructorTest(){
    var expectedWeaponInventory = new WeaponInventory();
    var sameWeaponInventory = testWeaponInventory;
    assertEquals(expectedWeaponInventory, testWeaponInventory);
    assertEquals(expectedWeaponInventory.hashCode(), testWeaponInventory.hashCode());
    assertNotEquals(testWeaponInventory, new Object());
    assertEquals(sameWeaponInventory, testWeaponInventory);
  }

  /**
   * Checks that the inventory works properly.
   */
  @Test
  void InventoryTest(){
    assertTrue(testWeaponInventory.getWeaponInventory().isEmpty());
    for (int i= 0; i < 10; i++) {
      testWeaponInventory.addWeapon(
          new Axe("Test weapon", 10, 10));
      assertEquals(i+1, testWeaponInventory.getWeaponInventory().size());
    }
  }
}
