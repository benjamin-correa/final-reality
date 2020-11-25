package com.github.cc3002.finalreality.model.character.player.classes;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.correa.finalreality.model.character.enemy.Enemy;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Set of tests for the Knight class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class KnightTest extends AbstractPlayerCharacterTest {

  private static final String KNIGHT_NAME = "Adelbert";
  private static final int BASE_TEST_HP = 10;
  private static final int BASE_TEST_DP = 5;

  private Knight testKnight;

  /**
   * Setup method.
   * Creates a new knight character.
   */
  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testKnight = new Knight(turns, KNIGHT_NAME,
        BASE_TEST_HP, BASE_TEST_DP);
    testCharacter = testKnight;
    tryToEquip(testKnight);
  }

  @Override
  @Test
  public void waitTurnTest() {
    tryToEquip(testKnight);
    checkWaitTurn(testKnight);
  }

  @Override
  @Test
  protected void attackTest() {
    assertEquals(testCharacter, getCharacter(CharacterType.KNIGHT));
    checkAttack(getCharacter(CharacterType.ENEMY));
    checkAttack(getCharacter(CharacterType.WHITE_MAGE));
    checkAttack(getCharacter(CharacterType.ENGINEER));
    checkAttack(getCharacter(CharacterType.BLACK_MAGE));
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
    var testEqualKnight = testKnight;
    checkCharacterConstruction(testKnight,
        testEqualKnight,
        new Knight(turns, "Test", BASE_TEST_HP,
            BASE_TEST_DP),
        new Enemy(KNIGHT_NAME, BASE_TEST_HP, 10,
            5, turns, 5));
  }

  @Override
  @Test
  public void checkEquipTest() {
    var testEquipKnight = new Knight(turns, KNIGHT_NAME,
        BASE_TEST_HP, BASE_TEST_DP);
    testEquipKnight.setHitPoints(0);
    tryToEquip(testEquipKnight);
    assertEquals(null, testEquipKnight.getEquippedWeapon());
    tryToEquip(testKnight);
    assertNotEquals(null, testKnight.getEquippedWeapon());
  }
}
