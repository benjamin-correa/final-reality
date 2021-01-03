package com.github.cc3002.finalreality.model.weapon;


import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Abstract class containing the common tests for all the classes of player characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 * @see IWeapon
 */
public abstract class AbstractWeaponTest {
  protected IWeapon testWeapon;

  /**
   * Checks that the weapon constructor, equals method  and hash method work properly.
   */
  @Test
  public abstract void constructorTest();

  /**
   * Checks the construction of the IWeapon
   */
  protected void checkIWeaponConstruction(final IWeapon expectedIWeapon,
      final IWeapon testEqualIWeapon,
      final IWeapon testDifferentIWeapon) {
    assertEquals(expectedIWeapon, testEqualIWeapon);
    assertNotEquals(testEqualIWeapon, testDifferentIWeapon);
    assertEquals(expectedIWeapon.hashCode(), testEqualIWeapon.hashCode());
    assertNotEquals(testEqualIWeapon, new Object());
  }

  /**
   * Checks if the magic damage of the weapons works properly.
   */
  @Test
  protected void magicDamageTest() {
    var expectedMagicDamage = testWeapon.getMagicDamage();
    assertEquals(expectedMagicDamage, testWeapon.getMagicDamage());
  }

  /**
   * Get info test.
   */
  @Test
  protected abstract void getInfoTest();

  /**
   * Checks if the get info method works properly
   */
  protected void checkGetInfo(IWeapon weapon) {
    var testInfo = testWeapon.getInfo();
    assertEquals(
        testWeapon.getName(),
        testInfo.get(Stats.NAME));
    assertEquals(
        testWeapon.getWeight(),
        Integer.parseInt(testInfo.get(Stats.WEIGHT)));
    assertEquals(
        testWeapon.getDamage(),
        Integer.parseInt(testInfo.get(Stats.DMG)));
  }

  protected abstract void toStringTest();

}
