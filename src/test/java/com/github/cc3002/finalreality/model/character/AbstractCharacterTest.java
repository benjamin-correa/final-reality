package com.github.cc3002.finalreality.model.character;

import com.github.correa.finalreality.model.character.Enemy;
import com.github.correa.finalreality.model.character.ICharacter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Abstract class containing the common tests for all the types of characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 * @see ICharacter
 */
public abstract class AbstractCharacterTest {

  protected BlockingQueue<ICharacter> turns;
  protected List<Enemy> testEnemy;

  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */
  @Test
  void waitTurnTest() {
    Assertions.assertTrue(turns.isEmpty());
    testEnemy.get(0).waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertTrue(turns.contains(testEnemy.get(0)));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks that the enemy characters are build correctly.
   */
  protected void checkEnemyConstruction(final Enemy expectedEnemy,
      final Enemy testEqualEnemy,
      final Enemy sameWeightDifferentEnemy,
      final Enemy sameDefensePointsDifferentEnemy,
      final Enemy sameStatsDifferentEnemy) {
    assertEquals(expectedEnemy, testEqualEnemy);
    assertNotEquals(sameWeightDifferentEnemy, testEqualEnemy);
    assertNotEquals(sameDefensePointsDifferentEnemy, testEqualEnemy);
    assertEquals(expectedEnemy.hashCode(), testEqualEnemy.hashCode());
    assertNotEquals(testEqualEnemy, sameStatsDifferentEnemy);
    assertNotEquals(testEqualEnemy, new Object());
  }

  protected void basicSetUp() {
    turns = new LinkedBlockingQueue<>();
    testEnemy = new ArrayList<>();
  }
}
