package com.github.cc3002.finalreality.model.party;

import com.github.correa.finalreality.model.party.EnemyParty;
import com.github.correa.finalreality.model.character.Enemy;
import com.github.correa.finalreality.model.character.ICharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Set of tests for the EnemyParty class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class EnemyPartyTest {

  private EnemyParty testParty;
  protected BlockingQueue<ICharacter> turns;
  @BeforeEach
  void setUp() {
      testParty = new EnemyParty();
  }

  /**
   * Checks that the enemy party class constructor and equals method works properly.
   */
  @Test
  void constructorTest() {
    turns = new LinkedBlockingQueue<>();
    var expectedEnemyParty = new EnemyParty();
    var sameEnemyParty = testParty;
    assertEquals(expectedEnemyParty, testParty);
    assertEquals(expectedEnemyParty.hashCode(), testParty.hashCode());
    assertNotEquals(expectedEnemyParty, new Object());
    assertEquals(sameEnemyParty, testParty);
  }

  /**
   * Checks that the inventory works properly.
   */
  @Test
  void EnemyPartySizeTest() {
    assertTrue(testParty.getEnemyParty().isEmpty());
    for (int i = 0; i < 5; i++) {
        testParty.addEnemy(
                new Enemy("Test 1", 10, 4, turns, 10));
        assertEquals(i+1, testParty.getEnemyParty().size());
      }
  }
}
