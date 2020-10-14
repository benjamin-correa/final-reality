package com.github.cc3002.finalreality.model.character.classes;

import com.github.correa.finalreality.model.character.player.classes.commonclasses.Engineer;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Knight;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Thief;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.BlackMage;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Set of tests for the {@code GameCharacter} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */

public class ClassesTest extends AbstractPlayerCharacterTest {

  private static final String BLACK_MAGE_NAME = "Vivi";
  private static final String KNIGHT_NAME = "Adelbert";
  private static final String WHITE_MAGE_NAME = "Eiko";
  private static final String ENGINEER_NAME = "Cid";
  private static final String THIEF_NAME = "Zidane";
  private static final int BASE_TEST_HP = 10;
  private static final int BASE_TEST_DP = 5;
  private static final int BASE_TEST_MP = 20;
  private Engineer testEngineer;
  private Knight testKnight;
  private Thief testThief;
  private BlackMage testBlackMage;
  private WhiteMage testWhiteMage;

  /**
   * Setup method.
   * Creates a new character named Vivi with 10 speed and links it to a turn queue.
   */
  @BeforeEach
  void setUp() {
    super.basicSetUp();

    testPlayerCharacters.add(
        new Engineer(turns, ENGINEER_NAME, BASE_TEST_HP, BASE_TEST_DP));

    testEngineer = new Engineer(turns, ENGINEER_NAME,BASE_TEST_HP, BASE_TEST_DP );
    testKnight = new Knight(turns, KNIGHT_NAME, BASE_TEST_HP, BASE_TEST_DP);
    testThief = new Thief(turns, THIEF_NAME, BASE_TEST_HP, BASE_TEST_DP);
    testBlackMage = new BlackMage(turns, BLACK_MAGE_NAME, BASE_TEST_HP, BASE_TEST_DP, BASE_TEST_MP);
    testWhiteMage = new WhiteMage(turns, WHITE_MAGE_NAME, BASE_TEST_HP, BASE_TEST_DP, BASE_TEST_MP);

  }

  /**
   * Checks that the class' constructor and equals method works properly.
   */
  @Test
  void constructorTest(){
    var testEqualEngineer = testEngineer;
    var testEqualKnight = testKnight;
    var testEqualThief = testThief;
    var testEqualBlackMage = testBlackMage;
    var testEqualWhiteMage = testWhiteMage;
    checkEngineerConstruction(testEngineer,
        testEqualEngineer,
        new Engineer(turns, "Test", BASE_TEST_HP, BASE_TEST_DP));
    checkKnightConstruction(testKnight,
        testEqualKnight,
        new Knight(turns, "Test", BASE_TEST_HP, BASE_TEST_DP));
    checkThiefConstruction(testThief,
        testEqualThief,
        new Thief(turns, "Test", BASE_TEST_HP, BASE_TEST_DP));
    checkBlackMageConstruction(testBlackMage,
        testEqualBlackMage,
        new BlackMage(turns, "Test", BASE_TEST_HP, BASE_TEST_DP, BASE_TEST_MP));
    checkWhiteMageConstruction(testWhiteMage,
        testEqualWhiteMage,
        new WhiteMage(turns, "Test", BASE_TEST_HP, BASE_TEST_DP, BASE_TEST_MP));
  }

  /**
   * Checks that the equip method works correctly.
   */
  @Test
  void equipWeaponTest() {
    for (var character :
        testPlayerCharacters) {
      assertNull(character.getEquippedWeapon());
      character.equip(testWeapon);
      assertEquals(testWeapon, character.getEquippedWeapon());
    }
  }
}
