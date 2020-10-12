package com.github.cc3002.finalreality.model.Party;

import com.github.correa.finalreality.model.Party.PlayerParty;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.CharacterClass;
import com.github.correa.finalreality.model.character.player.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerPartyTest {
  private PlayerParty testParty;
  protected BlockingQueue<ICharacter> turns;

  @BeforeEach
  void setUp() {
    testParty = new PlayerParty();
  }

  @Test
  void basicTest() {
    turns = new LinkedBlockingQueue<>();
    var expectedPlayerParty = new PlayerParty();
    var samePlayerParty = testParty;
    assertEquals(expectedPlayerParty, testParty);
    assertEquals(expectedPlayerParty.hashCode(), testParty.hashCode());
    assertNotEquals(testParty, new Object());
    assertEquals(samePlayerParty, testParty);
  }

  @Test
  void PlayerPartySizeTest() {
    assertTrue(testParty.getPlayerParty().isEmpty());
    for (int i = 0; i < 5; i++) {
      testParty.addPlayerCharacter(
          new PlayerCharacter(turns, "Test 1", 10, 4, CharacterClass.ENGINEER));
    }
    testParty.addPlayerCharacter(
        new PlayerCharacter(turns, "Test 1", 10, 4, CharacterClass.ENGINEER));
    assertEquals(4, testParty.getPlayerParty().size());
  }
}
