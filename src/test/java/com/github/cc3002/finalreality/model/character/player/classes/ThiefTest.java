package com.github.cc3002.finalreality.model.character.player.classes;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.correa.finalreality.enums.CharacterType;
import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.enums.WeaponType;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.enemy.Enemy;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Set of tests for the Thief class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class ThiefTest extends AbstractPlayerCharacterTest {

  private static final String THIEF_NAME = "Zidane";
  private static final int BASE_TEST_HP = 10;
  private static final int BASE_TEST_DP = 5;

  private Thief testThief;

  /**
   * Setup method.
   * Creates a new thief character.
   */
  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testThief = new Thief(turns, THIEF_NAME,
        BASE_TEST_HP, BASE_TEST_DP);
    testThief.equip(getWeapon(WeaponType.BOW));
    testCharacter = testThief;
  }

  @Override
  @Test
  public void waitTurnTest() {
    checkWaitTurn(testThief);
  }

  @Override
  @Test
  public void constructorTest(){
    var testEqualThief = testThief;
    checkCharacterConstruction(testThief,
        testEqualThief,
        new Thief(turns, "Test", BASE_TEST_HP,
            BASE_TEST_DP),
        new Enemy(THIEF_NAME, BASE_TEST_HP, 10,
            5, turns, 5));
  }

  @Override
  @Test
  public void checkEquipTest() {
    var testEquipThief = new Thief(turns, THIEF_NAME,
        BASE_TEST_HP, BASE_TEST_DP);
    var expectedWeapon = getWeapon(WeaponType.BOW);
    var unexpectedWeapon = getWeapon(WeaponType.STAFF);
    testEquipThief.equip(getWeapon(WeaponType.KNIFE));
    testEquipThief.equip(getWeapon(WeaponType.SWORD));
    testEquipThief.equip(expectedWeapon);
    assertEquals(expectedWeapon, testEquipThief.getEquippedWeapon());
    testEquipThief.equip(unexpectedWeapon);
    assertNotEquals(unexpectedWeapon, testEquipThief.getEquippedWeapon());
    testEquipThief.unequip();
    assertEquals(null, testEquipThief.getEquippedWeapon());
    testEquipThief.setHitPoints(0);
    testEquipThief.equip(expectedWeapon);
    assertEquals(null, testEquipThief.getEquippedWeapon());
  }


  @Override
  @RepeatedTest(500)
  protected void attackTest() {
    assertEquals(testCharacter, getCharacter(CharacterType.THIEF));
    checkAttack(getCharacter(CharacterType.ENEMY));
    checkAttack(getCharacter(CharacterType.WHITE_MAGE));
    checkAttack(getCharacter(CharacterType.ENGINEER));
    checkAttack(getCharacter(CharacterType.KNIGHT));
    checkAttack(getCharacter(CharacterType.BLACK_MAGE));
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
    var testInfo = character.getInfo();
    assertEquals(
        CharacterType.THIEF,
        CharacterType.valueOf(
            testInfo.get(Stats.CHARACTER_TYPE)));
  }

  void checkToString(ICharacter character) {
    var string = character.toString();
    var expectedString = "Name: " +
        character.getInfo().get(Stats.NAME) +
        ", " + CharacterType.THIEF;
    assertEquals(expectedString, string);
  }

  @Override
  @Test
  protected void toStringTest() {
    checkToString(testCharacter);
  }
}
