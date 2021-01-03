package com.github.cc3002.finalreality.model.character;

import com.github.correa.finalreality.enums.CharacterType;
import com.github.correa.finalreality.enums.Stats;
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

  /**
   * Checks the construction of the ICharacters
   */
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
   * Checks if setHitPoints works properly.
   */
  @Test
  protected void checkSetHitPointsTest () {
    var expectedHP = testCharacter.getMaxHitPoints();
    testCharacter.setHitPoints(expectedHP + expectedHP);
    assertEquals(expectedHP, testCharacter.getHitPoints());
    testCharacter.setHitPoints(expectedHP - expectedHP);
    assertEquals(0, testCharacter.getHitPoints());
    testCharacter.setHitPoints(1);
    assertEquals(1, testCharacter.getHitPoints());
  }

  /**
   * Checks if isAlive works properly.
   */
  @Test
  protected void checkIsAliveTest() {
    assertTrue(testCharacter.isAlive());
    testCharacter.setHitPoints(0);
    assertFalse(testCharacter.isAlive());
  }

  /**
   * Checks if the seed works correctly.
   */
  @Test
  public void testSeed() {
    long expectedSeed = 11;
    testCharacter.setSeed(expectedSeed);
    assertEquals(expectedSeed, testCharacter.getSeed());
  }

  /**
   * Basic set up for testing.
   */
  protected void basicSetUp() {
    seed = new Random().nextLong();
    turns = new LinkedBlockingQueue<>();
  }

  /**
   * Checks if attack method works properly.
   */
  @Test
  protected abstract void attackTest();

  /**
   * Checks if the attack method works properly.
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

  /**
   * Get info test.
   */
  @Test
  protected abstract void getInfoTest();

  /**
   * Checks if the get info method works properly
   */
  protected void checkGetInfo(ICharacter character) {
    var testInfo = testCharacter.getInfo();
    assertEquals(
        testCharacter.getHitPoints(),
        Integer.parseInt(testInfo.get(Stats.HP)));
    assertEquals(
        testCharacter.getDefensePoints(),
        Integer.parseInt(testInfo.get(Stats.DEF)));
    assertEquals(
        testCharacter.getName(),
        testInfo.get(Stats.NAME));
    assertEquals(
        testCharacter.getWeight(),
        Integer.parseInt(testInfo.get(Stats.WEIGHT)));
    assertEquals(
        testCharacter.isAlive(),
        Boolean.valueOf(testInfo.get(Stats.IS_ALIVE)));
  }

  /**
   * Returns a character for testing.
   */
  protected ICharacter getCharacter(CharacterType characterType) {
    int TEST_HP = new Random(seed).nextInt(99) + 1;
    int TEST_DP = new Random(seed). nextInt(50);
    int TEST_DMG = new Random(seed).nextInt(99) + 1;
    return switch (characterType) {
      case ENEMY -> new Enemy(
          "Goblin", TEST_HP, TEST_DMG,
          TEST_DP, turns, 10);
      case ENGINEER -> new Engineer(
          turns, "Cid", TEST_HP, TEST_DP);
      case KNIGHT -> new Knight(
          turns, "Adelbert", TEST_HP, TEST_DP);
      case THIEF -> new Thief(
          turns, "Zidane", TEST_HP, TEST_DP);
      case BLACK_MAGE -> new BlackMage(
          turns, "Vivi", TEST_HP, TEST_DP,
          20);
      case WHITE_MAGE -> new WhiteMage(
          turns, "Eiko", TEST_HP, TEST_DP,
          20);
    };
  }


  @Test
  protected abstract void toStringTest();
}
