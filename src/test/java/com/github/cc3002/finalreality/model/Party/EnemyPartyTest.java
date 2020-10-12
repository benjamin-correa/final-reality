package com.github.cc3002.finalreality.model.Party;

import com.github.correa.finalreality.model.Party.EnemyParty;
import com.github.correa.finalreality.model.character.Enemy;
import com.github.correa.finalreality.model.character.ICharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyPartyTest {
  private EnemyParty testParty;
  protected BlockingQueue<ICharacter> turns;
  @BeforeEach
  void setUp() {
      testParty = new EnemyParty();
  }
  @Test
  void basicTest() {
    turns = new LinkedBlockingQueue<>();
    var expectedEnemyParty = new EnemyParty();
    var sameEnemyParty = testParty;
    assertEquals(expectedEnemyParty, testParty);
    assertEquals(expectedEnemyParty.hashCode(), testParty.hashCode());
    assertNotEquals(expectedEnemyParty, new Object());
    assertEquals(sameEnemyParty, testParty);
  }
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
