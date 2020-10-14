package com.github.cc3002.finalreality.model.character.classes;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.IPlayerCharacter;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Engineer;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Knight;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Thief;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.BlackMage;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.WhiteMage;
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
 * Abstract class containing the common tests for all the classes of player characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 * @see IPlayerCharacter
 */
public abstract class AbstractPlayerCharacterTest extends AbstractCharacterTest {
  protected BlockingQueue<ICharacter> turns = new LinkedBlockingQueue<>();
  protected List<Engineer> testPlayerCharacters;
  protected Weapon testWeapon;

  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */
  @Test
  void waitTurnTest() {
    Assertions.assertTrue(turns.isEmpty());
    tryToEquip(testPlayerCharacters.get(0));
    testPlayerCharacters.get(0).waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertTrue(turns.contains(testPlayerCharacters.get(0)));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  private void tryToEquip(Engineer character) {
    character.equip(testWeapon);
  }

  /**
   * Checks that the engineer characters are build correctly.
   */
  protected void checkEngineerConstruction(final Engineer expectedEngineer,
      final Engineer testEqualEngineer,
      final Engineer sameClassDifferentEngineer) {
    assertEquals(expectedEngineer, testEqualEngineer);
    assertNotEquals(sameClassDifferentEngineer, testEqualEngineer);
    assertEquals(expectedEngineer.hashCode(), testEqualEngineer.hashCode());
    assertNotEquals(testEqualEngineer, new Object());
    assertEquals(testEqualEngineer.getHitPoints(), expectedEngineer.getHitPoints());
    assertEquals(expectedEngineer.getCharacterClass(), testEqualEngineer.getCharacterClass());
  }

  /**
   * Checks that the thief characters are build correctly.
   */
  protected void checkThiefConstruction(final Thief expectedThief,
      final Thief testEqualThief,
      final Thief sameClassDifferentThief) {
    assertEquals(expectedThief, testEqualThief);
    assertNotEquals(sameClassDifferentThief, testEqualThief);
    assertEquals(expectedThief.hashCode(), testEqualThief.hashCode());
    assertNotEquals(testEqualThief, new Object());
    assertEquals(testEqualThief.getHitPoints(), expectedThief.getHitPoints());
    assertEquals(expectedThief.getCharacterClass(), testEqualThief.getCharacterClass());
  }

  /**
   * Checks that the knight characters are build correctly.
   */
  protected void checkKnightConstruction(final Knight expectedKnight,
      final Knight testEqualKnight,
      final Knight sameClassDifferentKnight) {
    assertEquals(expectedKnight, testEqualKnight);
    assertNotEquals(sameClassDifferentKnight, testEqualKnight);
    assertEquals(expectedKnight.hashCode(), testEqualKnight.hashCode());
    assertNotEquals(testEqualKnight, new Object());
    assertEquals(testEqualKnight.getHitPoints(), expectedKnight.getHitPoints());
    assertEquals(expectedKnight.getCharacterClass(), testEqualKnight.getCharacterClass());
  }

  /**
   * Checks that the black mage characters are build correctly.
   */
  protected void checkBlackMageConstruction(final BlackMage expectedBlackMage,
      final BlackMage testEqualBlackMage,
      final BlackMage sameClassDifferentBlackMage) {
    assertEquals(expectedBlackMage, testEqualBlackMage);
    assertNotEquals(sameClassDifferentBlackMage, testEqualBlackMage);
    assertEquals(expectedBlackMage.hashCode(), testEqualBlackMage.hashCode());
    assertNotEquals(testEqualBlackMage, new Object());
    assertEquals(testEqualBlackMage.getHitPoints(), expectedBlackMage.getHitPoints());
    assertEquals(expectedBlackMage.getCharacterClass(), testEqualBlackMage.getCharacterClass());
    assertEquals(expectedBlackMage.getManaPoints(), testEqualBlackMage.getManaPoints());
  }

  /**
   * Checks that the white mage characters are build correctly.
   */
  protected void checkWhiteMageConstruction(final WhiteMage expectedWhiteMage,
      final WhiteMage testEqualWhiteMage,
      final WhiteMage sameClassDifferentWhiteMage) {
    assertEquals(expectedWhiteMage, testEqualWhiteMage);
    assertNotEquals(sameClassDifferentWhiteMage, testEqualWhiteMage);
    assertEquals(expectedWhiteMage.hashCode(), testEqualWhiteMage.hashCode());
    assertNotEquals(testEqualWhiteMage, new Object());
    assertEquals(testEqualWhiteMage.getHitPoints(), expectedWhiteMage.getHitPoints());
    assertEquals(expectedWhiteMage.getCharacterClass(), testEqualWhiteMage.getCharacterClass());
    assertEquals(expectedWhiteMage.getManaPoints(), testEqualWhiteMage.getManaPoints());
  }

  public void basicSetUp(){
    testWeapon = new Weapon("Test", 15, 10, WeaponType.AXE);
    testPlayerCharacters = new ArrayList<>();
  }

}
