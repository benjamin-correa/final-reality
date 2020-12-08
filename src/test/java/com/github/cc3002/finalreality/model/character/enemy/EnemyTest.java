package com.github.cc3002.finalreality.model.character.enemy;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.correa.finalreality.enums.CharacterType;
import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.enemy.Enemy;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Engineer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Set of tests for the Enemy class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";
  private static final int ENEMY_HP = 15;
  private static final int ENEMY_DAMAGE = 6;
  private static final int ENEMY_WEIGHT = 10;
  private static final int ENEMY_DEFENSE = 5;

  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testCharacter = new Enemy(ENEMY_NAME, ENEMY_HP, ENEMY_DAMAGE, ENEMY_DEFENSE,
        turns, ENEMY_WEIGHT);
  }

  @Override
  @Test
  public void waitTurnTest() {
    checkWaitTurn(testCharacter);
  }

  /**
   * Checks that the enemy class constructor and equals method works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    var testEqualCharacter = testCharacter;
    checkCharacterConstruction(testCharacter,
        testEqualCharacter,
        new Enemy(
            "Test", ENEMY_HP, ENEMY_DAMAGE, ENEMY_DEFENSE,
            turns, ENEMY_WEIGHT),
        new Engineer(
            turns, ENEMY_NAME, ENEMY_HP, ENEMY_DEFENSE));
    var differentWeight = new Enemy(
        ENEMY_NAME, ENEMY_HP, ENEMY_DAMAGE,
        ENEMY_DEFENSE, turns, 5);
    var differentStats = new Enemy(
        ENEMY_NAME, ENEMY_HP, 5,
        ENEMY_DEFENSE, turns, ENEMY_WEIGHT);
    assertNotEquals(testCharacter, differentWeight);
    assertEquals(testCharacter, differentStats);
  }

  @Override
  @RepeatedTest(500)
  protected void attackTest() {
    assertEquals(testCharacter, getCharacter(CharacterType.ENEMY));
    checkAttack(getCharacter(CharacterType.BLACK_MAGE));
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
  protected void getInfoTest() {
    checkGetInfo(testCharacter);
  }

  @Override
  protected void checkGetInfo(ICharacter character) {
    super.checkGetInfo(character);
    var testInfo = character.getInfo();
    assertEquals(
        CharacterType.ENEMY,
        CharacterType.valueOf(
            testInfo.get(Stats.CHARACTER_TYPE)));
  }

}