package com.github.cc3002.finalreality.model.weapon.types;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.correa.finalreality.model.weapon.commonweapons.Axe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
  }

  @Override
  @Test
  public void constructorTest() {
    var testEqualAxe = expectedAxe;
    checkIWeaponConstruction( expectedAxe,
        testEqualAxe, new Axe("Test", DAMAGE, SPEED));
  }

}
