package com.github.cc3002.finalreality.model.weapon.types;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.correa.finalreality.model.weapon.commonweapons.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
  }

  @Override
  @Test
  public void constructorTest() {
    var testEqualSword = expectedSword;
    checkIWeaponConstruction( expectedSword,
        testEqualSword, new Sword("Test", DAMAGE, SPEED));
  }
}
