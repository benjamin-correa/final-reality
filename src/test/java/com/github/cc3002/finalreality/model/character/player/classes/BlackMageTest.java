package com.github.cc3002.finalreality.model.character.player.classes;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.correa.finalreality.model.character.enemy.Enemy;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.BlackMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Set of tests for the Black Mage class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class BlackMageTest extends AbstractPlayerCharacterTest {

  private static final String BLACK_MAGE_NAME = "Vivi";
  private static final int BASE_TEST_HP = 10;
  private static final int BASE_TEST_DP = 5;
  private static final int BASE_TEST_MP = 20;

  private BlackMage testBlackMage;

  /**
   * Setup method.
   * Creates a new black mage character.
   */
  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testBlackMage = new BlackMage(turns, BLACK_MAGE_NAME, BASE_TEST_HP,
        BASE_TEST_DP, BASE_TEST_MP);
    testCharacter = testBlackMage;
    tryToEquip(testBlackMage);
  }

  @Override
  @Test
  public void waitTurnTest() {
    checkWaitTurn(testBlackMage);
  }

 @Override
 @Test
 protected void attackTest() {
   assertEquals(testCharacter, getCharacter(CharacterType.BLACK_MAGE));
   checkAttack(getCharacter(CharacterType.ENEMY));
   checkAttack(getCharacter(CharacterType.WHITE_MAGE));
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
    var testEqualBlackMage = testBlackMage;
    checkCharacterConstruction(testBlackMage,
        testEqualBlackMage,
        new BlackMage(turns, "Test", BASE_TEST_HP,
            BASE_TEST_DP, BASE_TEST_MP),
        new Enemy(BLACK_MAGE_NAME, BASE_TEST_HP, 10,
            5, turns, 5));
  }

  @Override
  @Test
  public void checkEquipTest() {
    var testEquipBlackMage = new BlackMage(turns, BLACK_MAGE_NAME, BASE_TEST_HP,
        BASE_TEST_DP, BASE_TEST_MP);
    testEquipBlackMage.setHitPoints(0);
    tryToEquip(testEquipBlackMage);
    assertEquals(null, testEquipBlackMage.getEquippedWeapon());
    tryToEquip(testBlackMage);
    assertNotEquals(null, testBlackMage.getEquippedWeapon());
  }
}

