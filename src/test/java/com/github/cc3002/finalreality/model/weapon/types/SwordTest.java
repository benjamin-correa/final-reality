package com.github.cc3002.finalreality.model.weapon.types;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.enums.WeaponType;
import com.github.correa.finalreality.model.weapon.IWeapon;
import com.github.correa.finalreality.model.weapon.commonweapons.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Set of tests for the Sword class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class SwordTest extends AbstractWeaponTest {
  private static final String SWORD_NAME = "Test Sword";
  private static final int DAMAGE = 15;
  private static final int SPEED = 10;

  private Sword expectedSword;

  /**
   * Setup method.
   */
  @BeforeEach
  void setUp() {
    expectedSword = new Sword(SWORD_NAME, DAMAGE, SPEED);
    testWeapon = expectedSword;
  }

  @Override
  @Test
  public void constructorTest() {
    var testEqualSword = expectedSword;
    checkIWeaponConstruction( expectedSword,
        testEqualSword, new Sword("Test", DAMAGE, SPEED));
  }

  @Override
  @Test
  protected void getInfoTest() {
    checkGetInfo(testWeapon);
  }

  @Override
  protected void checkGetInfo(IWeapon weapon) {
    super.checkGetInfo(weapon);
    var testInfo = weapon.getInfo();
    assertEquals(
        WeaponType.SWORD,
        WeaponType.valueOf(
            testInfo.get(Stats.WEAPON_TYPE)));
  }

  void checkToString(IWeapon weapon) {
    var string = weapon.toString();
    var expectedString = "SWORD, Name: " +
        weapon.getName() + ", DMG: " + weapon.getDamage() +
        ", Weight: " + weapon.getWeight();
    assertEquals(expectedString, string);
  }

  @Override
  @Test
  protected void toStringTest() {
    checkToString(testWeapon);
  }
}
