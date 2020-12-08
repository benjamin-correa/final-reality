package com.github.cc3002.finalreality.model.weapon.types;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.enums.WeaponType;
import com.github.correa.finalreality.model.weapon.IWeapon;
import com.github.correa.finalreality.model.weapon.magicweapons.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Set of tests for the Staff class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class StaffTest extends AbstractWeaponTest {
  private static final String STAFF_NAME = "Test Staff";
  private static final int DAMAGE = 15;
  private static final int SPEED = 10;
  private static final int MAGIC_DAMAGE = 10;

  private Staff expectedStaff;

  /**
   * Set up method.
   */
  @BeforeEach
  void setUp() {
    expectedStaff = new Staff(STAFF_NAME, DAMAGE, MAGIC_DAMAGE, SPEED);
    testWeapon = expectedStaff;
  }

  @Override
  @Test
  public void constructorTest() {
    var testEqualStaff = expectedStaff;
    checkIWeaponConstruction( expectedStaff,
        testEqualStaff, new Staff("Test", DAMAGE, MAGIC_DAMAGE, SPEED));
  }

  /**
   * Checks if the magic damage of the weapons works properly.
   */
  @Test
  protected void magicDamageTest() {
    var expectedMagicDamage = 10;
    assertEquals(expectedMagicDamage, expectedStaff.getMagicDamage());
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
        WeaponType.STAFF,
        WeaponType.valueOf(
            testInfo.get(Stats.WEAPON_TYPE)));
    assertEquals(
        testWeapon.getMagicDamage(),
        Integer.parseInt(testInfo.get(Stats.MAGIC_DMG)));
  }

}
