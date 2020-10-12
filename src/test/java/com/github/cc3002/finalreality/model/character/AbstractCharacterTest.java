package com.github.cc3002.finalreality.model.character;

import com.github.correa.finalreality.model.character.Enemy;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.PlayerCharacter;
import com.github.correa.finalreality.model.weapon.Weapon;
import com.github.correa.finalreality.model.weapon.WeaponType;
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
  protected List<PlayerCharacter> testPlayerCharacters;
  protected Weapon testWeapon;
  protected List<Enemy> testEnemy;

  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */
  @Test
  void waitTurnTest() {
    Assertions.assertTrue(turns.isEmpty());
    tryToEquip(testPlayerCharacters.get(0));
    testPlayerCharacters.get(0).waitTurn();
    testEnemy.get(0).waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(2, turns.size());
      Assertions.assertTrue(turns.contains(testPlayerCharacters.get(0)));
      Assertions.assertTrue(turns.contains(testEnemy.get(0)));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void tryToEquip(PlayerCharacter character) {
    character.equip(testWeapon);
  }

  protected void checkPlayerConstruction(final PlayerCharacter expectedCharacter,
      final PlayerCharacter testEqualCharacter,
      final PlayerCharacter sameClassDifferentCharacter,
      final PlayerCharacter sameStatsDifferentClassCharacter) {
    assertEquals(expectedCharacter, testEqualCharacter);
    assertNotEquals(sameClassDifferentCharacter, testEqualCharacter);
    assertNotEquals(testEqualCharacter, sameStatsDifferentClassCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());;
    assertNotEquals(testEqualCharacter, new Object());
  }
  protected void checkEnemyConstruction(final Enemy expectedEnemy,
      final Enemy testEqualEnemy,
      final Enemy sameWeightDifferentEnemy,
      final Enemy sameHitPointsDifferentEnemy,
      final Enemy sameDefensePointsDifferentEnemy,
      final PlayerCharacter sameStatsDifferentClass) {
    assertEquals(expectedEnemy, testEqualEnemy);
    assertNotEquals(sameWeightDifferentEnemy, testEqualEnemy);
    assertNotEquals(sameHitPointsDifferentEnemy, testEqualEnemy);
    assertNotEquals(sameDefensePointsDifferentEnemy, testEqualEnemy);
    assertEquals(expectedEnemy.hashCode(), testEqualEnemy.hashCode());
    assertNotEquals(testEqualEnemy, new Object());
  }

  protected void basicSetUp() {
    turns = new LinkedBlockingQueue<>();
    testWeapon = new Weapon("Test", 15, 10, WeaponType.AXE);
    testPlayerCharacters = new ArrayList<>();
    testEnemy = new ArrayList<>();
  }
}
