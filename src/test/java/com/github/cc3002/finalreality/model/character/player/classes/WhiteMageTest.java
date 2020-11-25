package com.github.cc3002.finalreality.model.character.player.classes;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.correa.finalreality.model.character.enemy.Enemy;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Set of tests for the White Mage class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class WhiteMageTest extends AbstractPlayerCharacterTest {

  private static final String WHITE_MAGE_NAME = "Eiko";
  private static final int BASE_TEST_HP = 10;
  private static final int BASE_TEST_DP = 5;
  private static final int BASE_TEST_MP = 20;

  private WhiteMage testWhiteMage;

  /**
   * Setup method.
   * Creates a new knight character.
   */
  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testWhiteMage = new WhiteMage(
        turns, WHITE_MAGE_NAME, BASE_TEST_HP,
        BASE_TEST_DP, BASE_TEST_MP);
    testCharacter = testWhiteMage;
    tryToEquip(testWhiteMage);
  }

  @Override
  @Test
  public void waitTurnTest() {
    tryToEquip(testWhiteMage);
    checkWaitTurn(testWhiteMage);
  }

  @Override
  @Test
  protected void attackTest() {
    assertEquals(testCharacter, getCharacter(CharacterType.WHITE_MAGE));
    checkAttack(getCharacter(CharacterType.ENEMY));
    checkAttack(getCharacter(CharacterType.BLACK_MAGE));
    checkAttack(getCharacter(CharacterType.ENGINEER));
    checkAttack(getCharacter(CharacterType.KNIGHT));
    checkAttack(getCharacter(CharacterType.THIEF));
    testCharacter.setHitPoints(0);
    checkAttack(getCharacter(CharacterType.ENEMY));
    checkAttack(getCharacter(CharacterType.WHITE_MAGE));
    checkAttack(getCharacter(CharacterType.ENGINEER));
    checkAttack(getCharacter(CharacterType.KNIGHT));
    checkAttack(getCharacter(CharacterType.THIEF));
  }

  @Override
  @Test
  public void constructorTest(){
    var testEqualWhiteMage = testWhiteMage;
    checkCharacterConstruction(testWhiteMage,
        testEqualWhiteMage,
        new WhiteMage(
            turns, "Test", BASE_TEST_HP,
            BASE_TEST_DP, BASE_TEST_MP),
        new Enemy(WHITE_MAGE_NAME, BASE_TEST_HP, 10,
            5, turns, 5));
  }

  @Override
  @Test
  public void checkEquipTest() {
    var testEquipWhiteMage = new WhiteMage(
        turns, WHITE_MAGE_NAME, BASE_TEST_HP,
        BASE_TEST_DP, BASE_TEST_MP);
    testEquipWhiteMage.setHitPoints(0);
    tryToEquip(testEquipWhiteMage);
    assertEquals(null, testEquipWhiteMage.getEquippedWeapon());
    tryToEquip(testWhiteMage);
    assertNotEquals(null, testWhiteMage.getEquippedWeapon());
  }
}

