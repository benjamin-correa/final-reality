package com.github.cc3002.finalreality.model.character.player.classes;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.correa.finalreality.enums.CharacterType;
import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.enums.WeaponType;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.enemy.Enemy;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Set of tests for the White Mage class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class WhiteMageTest extends AbstractPlayerCharacterTest {

  private static final String WHITE_MAGE_NAME = "Eiko";
  private static final int BASE_TEST_HP = 10;
  private static final int BASE_TEST_DP = 5;
  private static final int BASE_TEST_MP = 20;

  private WhiteMage testWhiteMage;

  /**
   * Setup method.
   * Creates a new knight character.
   */
  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testWhiteMage = new WhiteMage(
        turns, WHITE_MAGE_NAME, BASE_TEST_HP,
        BASE_TEST_DP, BASE_TEST_MP);
    testWhiteMage.equip(getWeapon(WeaponType.STAFF));
    testCharacter = testWhiteMage;
  }

  @Override
  @Test
  public void waitTurnTest() {
    checkWaitTurn(testWhiteMage);
  }

  @Override
  @Test
  public void constructorTest(){
    var testEqualWhiteMage = testWhiteMage;
    checkCharacterConstruction(testWhiteMage,
        testEqualWhiteMage,
        new WhiteMage(
            turns, "Test", BASE_TEST_HP,
            BASE_TEST_DP, BASE_TEST_MP),
        new Enemy(WHITE_MAGE_NAME, BASE_TEST_HP, 10,
            5, turns, 5));
  }

  /**
   * Checks if the mana points works properly.
   */
  @Test
  protected void checkManaPoints() {
    var expectedMP = testWhiteMage.getManaPoints();
    assertEquals(expectedMP, testWhiteMage.getManaPoints());
    testWhiteMage.setManaPoints(expectedMP + expectedMP);
    assertEquals(expectedMP, testWhiteMage.getManaPoints());
    testWhiteMage.setManaPoints(1);
    assertEquals(1, testWhiteMage.getManaPoints());
    testWhiteMage.setManaPoints(0);
    assertEquals(0, testWhiteMage.getManaPoints());
  }

  @Override
  @Test
  public void checkEquipTest() {
    var testEquipWhiteMage = new WhiteMage(
        turns, WHITE_MAGE_NAME, BASE_TEST_HP,
        BASE_TEST_DP, BASE_TEST_MP);
    var expectedWeapon = getWeapon(WeaponType.STAFF);
    var unexpectedWeapon = getWeapon(WeaponType.AXE);
    testEquipWhiteMage.equip(expectedWeapon);
    assertEquals(expectedWeapon, testEquipWhiteMage.getEquippedWeapon());
    testEquipWhiteMage.equip(unexpectedWeapon);
    assertNotEquals(unexpectedWeapon, testEquipWhiteMage.getEquippedWeapon());
    testEquipWhiteMage.unequip();
    assertEquals(null, testEquipWhiteMage.getEquippedWeapon());
    testEquipWhiteMage.setHitPoints(0);
    testEquipWhiteMage.equip(expectedWeapon);
    assertEquals(null, testEquipWhiteMage.getEquippedWeapon());
  }

  @Override
  @RepeatedTest(500)
  protected void attackTest() {
    assertEquals(testCharacter, getCharacter(CharacterType.WHITE_MAGE));
    checkAttack(getCharacter(CharacterType.ENEMY));
    checkAttack(getCharacter(CharacterType.BLACK_MAGE));
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
    var testCharacterInfo = (WhiteMage) (character);
    var testInfo = character.getInfo();
    assertEquals(
        testCharacterInfo.getMaxManaPoints(),
        Integer.parseInt(testInfo.get(Stats.MAX_MP)));
    assertEquals(
        testCharacterInfo.getMaxManaPoints(),
        Integer.parseInt(testInfo.get(Stats.MP)));
    assertEquals(
        testCharacterInfo.getMagicDamage(),
        Integer.parseInt(testInfo.get(Stats.MAGIC_DMG)));
    assertEquals(
        CharacterType.WHITE_MAGE,
        CharacterType.valueOf(
            testInfo.get(Stats.CHARACTER_TYPE)));
  }

  /**
   * Check's the mana points.
   */
  @Test
  public void checkGetManaPoints(){
    var expectedManaPoints = testWhiteMage.getManaPoints();
    assertEquals(
        expectedManaPoints,
        testWhiteMage.getManaPoints());
    assertEquals(
        expectedManaPoints,
        testWhiteMage.getMaxManaPoints());
  }

  /**
   * Checks get magic damage.
   */
  @Test
  public void checkGetMagicDamage() {
    var expectedMagicDamage = testWhiteMage.getMagicDamage();
    assertEquals(
        expectedMagicDamage, testWhiteMage.getMagicDamage());
    testWhiteMage.unequip();
    assertEquals(0, testWhiteMage.getMagicDamage());
  }
}

