package com.github.cc3002.finalreality.model.weapon.types;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.enums.WeaponType;
import com.github.correa.finalreality.model.weapon.IWeapon;
import com.github.correa.finalreality.model.weapon.commonweapons.Knife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Set of tests for the Knife class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class KnifeTest extends AbstractWeaponTest {

  private static final String KNIFE_NAME = "Test Knife";
  private static final int DAMAGE = 15;
  private static final int SPEED = 10;

  private Knife expectedKnife;

  /**
   * Setup method.
   */
  @BeforeEach
  void setUp() {
    expectedKnife = new Knife(KNIFE_NAME, DAMAGE, SPEED);
    testWeapon = expectedKnife;
  }

  @Override
  @Test
  public void constructorTest() {
    var testEqualKnife = expectedKnife;
    checkIWeaponConstruction( expectedKnife,
        testEqualKnife, new Knife("Test", DAMAGE, SPEED));
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
        WeaponType.KNIFE,
        WeaponType.valueOf(
            testInfo.get(Stats.WEAPON_TYPE)));
  }
}
