package com.github.cc3002.finalreality.model.character.player.classes;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.correa.finalreality.enums.CharacterType;
import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.enums.WeaponType;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.enemy.Enemy;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.BlackMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Set of tests for the Black Mage class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class BlackMageTest extends AbstractPlayerCharacterTest {

  private static final String BLACK_MAGE_NAME = "Vivi";
  private static final int BASE_TEST_HP = 10;
  private static final int BASE_TEST_DP = 5;
  private static final int BASE_TEST_MP = 20;

  private BlackMage testBlackMage;

  /**
   * Setup method.
   * Creates a new black mage character.
   */
  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testBlackMage = new BlackMage(turns, BLACK_MAGE_NAME, BASE_TEST_HP,
        BASE_TEST_DP, BASE_TEST_MP);
    testBlackMage.equip(getWeapon(WeaponType.STAFF));
    testCharacter = testBlackMage;
  }

  @Override
  @Test
  public void waitTurnTest() {
    checkWaitTurn(testBlackMage);
  }

  @Override
  @Test
  public void constructorTest(){
    var testEqualBlackMage = testBlackMage;
    checkCharacterConstruction(testBlackMage,
        testEqualBlackMage,
        new BlackMage(turns, "Test", BASE_TEST_HP,
            BASE_TEST_DP, BASE_TEST_MP),
        new Enemy(BLACK_MAGE_NAME, BASE_TEST_HP, 10,
            5, turns, 5));
  }

  /**
   * Checks if the mana points works properly.
   */
  @Test
  protected void checkManaPoints() {
    var expectedMP = testBlackMage.getManaPoints();
    assertEquals(expectedMP, testBlackMage.getManaPoints());
    testBlackMage.setManaPoints(expectedMP + expectedMP);
    assertEquals(expectedMP, testBlackMage.getManaPoints());
    testBlackMage.setManaPoints(1);
    assertEquals(1, testBlackMage.getManaPoints());
    testBlackMage.setManaPoints(0);
    assertEquals(0, testBlackMage.getManaPoints());
  }

  @Override
  @Test
  public void checkEquipTest() {
    var testEquipBlackMage = new BlackMage(turns, BLACK_MAGE_NAME, BASE_TEST_HP,
        BASE_TEST_DP, BASE_TEST_MP);
    var expectedWeapon = getWeapon(WeaponType.KNIFE);
    var unexpectedWeapon = getWeapon(WeaponType.SWORD);
    testEquipBlackMage.equip(expectedWeapon);
    assertEquals(expectedWeapon, testEquipBlackMage.getEquippedWeapon());
    testEquipBlackMage.equip(unexpectedWeapon);
    assertNotEquals(unexpectedWeapon, testEquipBlackMage.getEquippedWeapon());
    testEquipBlackMage.unequip();
    assertEquals(null, testEquipBlackMage.getEquippedWeapon());
    testEquipBlackMage.setHitPoints(0);
    testEquipBlackMage.equip(expectedWeapon);
    assertEquals(null, testEquipBlackMage.getEquippedWeapon());
  }

  @Override
  @RepeatedTest(500)
  protected void attackTest() {
    assertEquals(testCharacter, getCharacter(CharacterType.BLACK_MAGE));
    checkAttack(getCharacter(CharacterType.ENEMY));
    checkAttack(getCharacter(CharacterType.WHITE_MAGE));
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
    var testCharacterInfo = (BlackMage) (character);
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
        CharacterType.BLACK_MAGE,
        CharacterType.valueOf(
            testInfo.get(Stats.CHARACTER_TYPE)));
  }

  /**
   * Check's the mana points.
   */
  @Test
  public void checkGetManaPoints(){
    var expectedManaPoints = testBlackMage.getManaPoints();
    assertEquals(
        expectedManaPoints, testBlackMage.getManaPoints());
    assertEquals(
        expectedManaPoints, testBlackMage.getMaxManaPoints());
  }

  /**
   * Checks get magic damage.
   */
  @Test
  public void checkGetMagicDamage() {
    var expectedMagicDamage = testBlackMage.getMagicDamage();
    assertEquals(expectedMagicDamage, testBlackMage.getMagicDamage());
    testBlackMage.unequip();
    assertEquals(0, testBlackMage.getMagicDamage());
  }

  void checkToString(ICharacter character) {
    var string = character.toString();
    var expectedString = "Name: " + character.getInfo().get(Stats.NAME) + ", " + CharacterType.BLACK_MAGE;
    assertEquals(expectedString, string);
  }

  @Override
  @Test
  protected void toStringTest() {
    checkToString(testCharacter);
  }
}

