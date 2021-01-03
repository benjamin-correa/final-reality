package com.github.cc3002.finalreality.model.weapon.types;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.enums.WeaponType;
import com.github.correa.finalreality.model.weapon.IWeapon;
import com.github.correa.finalreality.model.weapon.commonweapons.Axe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Set of tests for the Axe class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class AxeTest extends AbstractWeaponTest {
  private static final String AXE_NAME = "Test Axe";
  private static final int DAMAGE = 15;
  private static final int SPEED = 10;

  private Axe expectedAxe;

  /**
   * Setup method.
   */
  @BeforeEach
  void setUp() {
    expectedAxe = new Axe(AXE_NAME, DAMAGE, SPEED);
    testWeapon = expectedAxe;
  }

  @Override
  @Test
  public void constructorTest() {
    var testEqualAxe = expectedAxe;
    checkIWeaponConstruction( expectedAxe,
        testEqualAxe, new Axe("Test", DAMAGE, SPEED));
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
        WeaponType.AXE,
        WeaponType.valueOf(
            testInfo.get(Stats.WEAPON_TYPE)));
  }

  void checkToString(IWeapon weapon) {
    var string = weapon.toString();
    var expectedString = "AXE, Name: " +
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
