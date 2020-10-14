package com.github.cc3002.finalreality.model.party;

import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Engineer;
import com.github.correa.finalreality.model.party.PlayerParty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Set of tests for the PlayerParty class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class PlayerPartyTest {
  private PlayerParty testParty;
  protected BlockingQueue<ICharacter> turns;

  @BeforeEach
  void setUp() {
    testParty = new PlayerParty();
  }

  /**
   * Checks that the player party class constructor and equals method works properly.
   */
  @Test
  void constructorTest() {
    turns = new LinkedBlockingQueue<>();
    var expectedPlayerParty = new PlayerParty();
    var samePlayerParty = testParty;
    assertEquals(expectedPlayerParty, testParty);
    assertEquals(expectedPlayerParty.hashCode(), testParty.hashCode());
    assertNotEquals(testParty, new Object());
    assertEquals(samePlayerParty, testParty);
  }

  /**
   * Checks that the inventory works properly.
   */
  @Test
  void PlayerPartySizeTest() {
    assertTrue(testParty.getPlayerParty().isEmpty());
    for (int i = 0; i < 5; i++) {
      testParty.addPlayerCharacter(
          new Engineer(turns, "Test 1", 10, 4));
    }
    testParty.addPlayerCharacter(
        new Engineer(turns, "Test 1", 10, 4));
    assertEquals(4, testParty.getPlayerParty().size());
  }
}
