package com.github.cc3002.finalreality.model.weapon.types;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.correa.finalreality.model.weapon.commonweapons.Bow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the Bow class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class BowTest extends AbstractWeaponTest {

  private static final String BOW_NAME = "Test Bow";
  private static final int DAMAGE = 15;
  private static final int SPEED = 10;

  private Bow expectedBow;

  /**
   * Setup method.
   */
  @BeforeEach
  void setUp() {
    expectedBow = new Bow(BOW_NAME, DAMAGE, SPEED);
  }

  @Override
  @Test
  public void constructorTest() {
    var testEqualBow = expectedBow;
    checkIWeaponConstruction( expectedBow,
        testEqualBow, new Bow("Test", DAMAGE, SPEED));
  }

}
