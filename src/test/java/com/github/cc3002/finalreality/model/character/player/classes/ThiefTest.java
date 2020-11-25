package com.github.cc3002.finalreality.model.character.player.classes;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.correa.finalreality.model.character.enemy.Enemy;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Set of tests for the Thief class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class ThiefTest extends AbstractPlayerCharacterTest {

  private static final String THIEF_NAME = "Zidane";
  private static final int BASE_TEST_HP = 10;
  private static final int BASE_TEST_DP = 5;

  private Thief testThief;

  /**
   * Setup method.
   * Creates a new thief character.
   */
  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testThief = new Thief(turns, THIEF_NAME,
        BASE_TEST_HP, BASE_TEST_DP);
    testCharacter = testThief;
    tryToEquip(testThief);
  }

  @Override
  @Test
  public void waitTurnTest() {
    tryToEquip(testThief);
    checkWaitTurn(testThief);
  }

  @Override
  @Test
  protected void attackTest() {
    assertEquals(testCharacter, getCharacter(CharacterType.THIEF));
    checkAttack(getCharacter(CharacterType.ENEMY));
    checkAttack(getCharacter(CharacterType.WHITE_MAGE));
    checkAttack(getCharacter(CharacterType.ENGINEER));
    checkAttack(getCharacter(CharacterType.KNIGHT));
    checkAttack(getCharacter(CharacterType.BLACK_MAGE));
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
    var testEqualThief = testThief;
    checkCharacterConstruction(testThief,
        testEqualThief,
        new Thief(turns, "Test", BASE_TEST_HP,
            BASE_TEST_DP),
        new Enemy(THIEF_NAME, BASE_TEST_HP, 10,
            5, turns, 5));
  }

  @Override
  @Test
  public void checkEquipTest() {
    var testEquipThief = new Thief(turns, THIEF_NAME,
        BASE_TEST_HP, BASE_TEST_DP);
    testEquipThief.setHitPoints(0);
    tryToEquip(testEquipThief);
    assertEquals(null, testEquipThief.getEquippedWeapon());
    tryToEquip(testThief);
    assertNotEquals(null, testThief.getEquippedWeapon());
  }
}
