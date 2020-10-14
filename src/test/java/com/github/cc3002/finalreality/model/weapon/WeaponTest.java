package com.github.cc3002.finalreality.model.weapon;

import com.github.correa.finalreality.model.weapon.Weapon;
import com.github.correa.finalreality.model.weapon.WeaponType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Set of tests for the Weapon class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
class WeaponTest {

  private static final String AXE_NAME = "Test Axe";
  private static final String AXE2_NAME = "Test Axe2";
  private static final String AXE3_NAME = "Test Axe3";
  private static final String STAFF_NAME = "Test Staff";
  private static final String SWORD_NAME = "Test Sword";
  private static final String BOW_NAME = "Test Bow";
  private static final String KNIFE_NAME = "Test Knife";
  private static final int DAMAGE = 15;
  private static final int SPEED = 10;

  private Weapon testAxe;
  private Weapon testAxe2;
  private Weapon testAxe3;
  private Weapon testStaff;
  private Weapon testSword;
  private Weapon testSword2;
  private Weapon testBow;
  private Weapon testKnife;

  @BeforeEach
  void setUp() {
    testAxe = new Weapon(AXE_NAME, DAMAGE, SPEED, WeaponType.AXE);
    testAxe2 = new Weapon(AXE2_NAME, 10, 10, WeaponType.AXE);
    testAxe3 = new Weapon(AXE3_NAME, 15, 15, WeaponType.AXE);
    testStaff = new Weapon(STAFF_NAME, DAMAGE, SPEED, WeaponType.STAFF);
    testSword = new Weapon(SWORD_NAME, DAMAGE, SPEED, WeaponType.SWORD);
    testSword2 = new Weapon(AXE_NAME, DAMAGE, SPEED, WeaponType.SWORD);
    testBow = new Weapon(BOW_NAME, DAMAGE, SPEED, WeaponType.BOW);
    testKnife = new Weapon(KNIFE_NAME, DAMAGE, SPEED, WeaponType.KNIFE);
  }

  /**
   * Checks that the weapon class constructor and equals method works properly.
   */
  @Test
  void constructorTest() {
    var expectedAxe = new Weapon(AXE_NAME, DAMAGE, SPEED, WeaponType.AXE);
    var expectedStaff = new Weapon(STAFF_NAME, DAMAGE, SPEED, WeaponType.STAFF);
    var expectedSword = new Weapon(SWORD_NAME, DAMAGE, SPEED, WeaponType.SWORD);
    var expectedBow = new Weapon(BOW_NAME, DAMAGE, SPEED, WeaponType.BOW);
    var expectedKnife = new Weapon(KNIFE_NAME, DAMAGE, SPEED, WeaponType.KNIFE);

    assertEquals(expectedAxe, testAxe);
    assertEquals(expectedAxe.hashCode(), testAxe.hashCode());
    assertEquals(expectedStaff, testStaff);
    assertEquals(expectedStaff.hashCode(), testStaff.hashCode());
    assertEquals(expectedSword, testSword);
    assertEquals(expectedSword.hashCode(), testSword.hashCode());
    assertEquals(expectedBow, testBow);
    assertEquals(expectedBow.hashCode(), testBow.hashCode());
    assertEquals(expectedKnife, testKnife);
    assertEquals(expectedKnife.hashCode(), testKnife.hashCode());
    assertNotEquals(expectedAxe, testSword);
    assertNotEquals(expectedAxe, testAxe2);
    assertNotEquals(expectedAxe, testAxe3);
    assertNotEquals(expectedAxe, testSword2);
    assertNotEquals(testAxe, new Object());
  }
}