package com.github.cc3002.finalreality.model.weapon;


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

  /**
   * Checks that the weapon constructor, equals method  and hash method work properly.
   */
  @Test
  public abstract void constructorTest();

  protected void checkIWeaponConstruction(final IWeapon expectedIWeapon,
      final IWeapon testEqualIWeapon,
      final IWeapon testDifferentIWeapon) {
    assertEquals(expectedIWeapon, testEqualIWeapon);
    assertNotEquals(testEqualIWeapon, testDifferentIWeapon);
    assertEquals(expectedIWeapon.hashCode(), testEqualIWeapon.hashCode());
    assertNotEquals(testEqualIWeapon, new Object());
  }

}
