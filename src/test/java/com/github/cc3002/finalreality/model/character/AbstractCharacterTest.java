package com.github.cc3002.finalreality.model.character;

import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.enemy.Enemy;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Engineer;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Knight;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Thief;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.BlackMage;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.WhiteMage;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests for all the types of characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 * @see ICharacter
 */
public abstract class AbstractCharacterTest {

  protected BlockingQueue<ICharacter> turns;
  protected ICharacter testCharacter;
  protected long seed;

  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */
  public void checkWaitTurn(ICharacter character) {
    Assertions.assertTrue(turns.isEmpty());
    character.waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertEquals(character, turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */
  @Test
  public abstract void waitTurnTest();

  /**
   * Checks that the characters are built correctly.
   */
  @Test
  public abstract void constructorTest();

  protected void checkCharacterConstruction(
      final ICharacter expectedCharacter,
      final ICharacter testEqualCharacter,
      final ICharacter sameClassDifferentCharacter,
      final ICharacter differentClassCharacter) {
    assertEquals(expectedCharacter, testEqualCharacter);
    assertNotEquals(sameClassDifferentCharacter, expectedCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());
    assertNotEquals(testEqualCharacter, differentClassCharacter);
  }

  /**
   * Checks if isAlive works correctly.
   */
  @Test
  protected void checkIsAliveTest() {
    assertTrue(testCharacter.isAlive());
    testCharacter.setHitPoints(0);
    assertFalse(testCharacter.isAlive());
  }

  protected abstract void attackTest();

  /**
   * Checks if the attack method works properly
   */
  protected void checkAttack(final @NotNull ICharacter opponent) {
    opponent.setSeed(seed);
    if (testCharacter.isAlive()) {
      int expectedHP = opponent.getHitPoints() - (
          opponent.getDefensePoints() >= testCharacter.getAttack()
              ? 1 : testCharacter.getAttack() - opponent.getDefensePoints());
      testCharacter.attack(opponent);
      assertEquals(Math.max(expectedHP, 0), opponent.getHitPoints(), "Test failed with seed: " + seed);
    }
    else {
      int expectedHP = opponent.getHitPoints();
      testCharacter.attack(opponent);
      assertEquals(expectedHP, opponent.getHitPoints(), "Test failed with seed: " + seed);
    }
  }

  @Test
  public void testSeed() {
    long expectedSeed = 11;
    testCharacter.setSeed(expectedSeed);
    assertEquals(expectedSeed, testCharacter.getSeed());
  }

  protected void basicSetUp() {
    seed = new Random().nextLong();
    turns = new LinkedBlockingQueue<>();
  }

  protected ICharacter getCharacter(CharacterType characterType) {
    int BASE_TEST_HP = 15;
    int BASE_TEST_DP = 5;
    if (characterType == CharacterType.ENEMY) {
      return new Enemy(
          "Goblin", BASE_TEST_HP, 6, BASE_TEST_DP, turns, 10);
    }
    else if (characterType == CharacterType.ENGINEER) {
      return new Engineer(
          turns, "Cid", BASE_TEST_HP, BASE_TEST_DP);
    }
    else if (characterType == CharacterType.KNIGHT) {
      return new Knight(
          turns, "Adelbert", BASE_TEST_HP, BASE_TEST_DP);
    }
    else if (characterType == CharacterType.THIEF) {
      return new Thief(
          turns, "Zidane", BASE_TEST_HP, BASE_TEST_DP);
    }
    else if (characterType == CharacterType.BLACK_MAGE) {
      return new BlackMage(
          turns, "Vivi", BASE_TEST_HP, BASE_TEST_DP, 20);
    }
    else {
      return new WhiteMage(
          turns, "Eiko", BASE_TEST_HP, BASE_TEST_DP, 20);
    }
  }

  protected enum CharacterType {
    ENEMY, KNIGHT, ENGINEER, THIEF, BLACK_MAGE, WHITE_MAGE
  }
}
