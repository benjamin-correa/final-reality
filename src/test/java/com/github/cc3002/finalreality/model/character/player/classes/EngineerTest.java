package com.github.cc3002.finalreality.model.character.player.classes;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.correa.finalreality.enums.CharacterType;
import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.enums.WeaponType;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.enemy.Enemy;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Engineer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Set of tests for the Engineer class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class EngineerTest extends AbstractPlayerCharacterTest {

  private static final String ENGINEER_NAME = "Cid";
  private static final int BASE_TEST_HP = 10;
  private static final int BASE_TEST_DP = 5;

  private Engineer testEngineer;


  /**
   * Setup method.
   * Creates a new engineer character.
   */
  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testEngineer = new Engineer(turns, ENGINEER_NAME,
        BASE_TEST_HP, BASE_TEST_DP);
    testEngineer.equip(getWeapon(WeaponType.AXE));
    testCharacter = testEngineer;
  }

  @Override
  @Test
  public void waitTurnTest() {
    checkWaitTurn(testEngineer);
  }

  @Override
  @Test
  public void constructorTest(){
    var testEqualEngineer = testEngineer;
    checkCharacterConstruction(testEngineer,
        testEqualEngineer,
        new Engineer(turns, "Test", BASE_TEST_HP,
            BASE_TEST_DP),
        new Enemy(ENGINEER_NAME, BASE_TEST_HP, 10,
            5, turns, 5));
  }

  @Override
  @Test
  public void checkEquipTest() {
    var testEquipEngineer = new Engineer(turns, ENGINEER_NAME,
        BASE_TEST_HP, BASE_TEST_DP);
    var expectedWeapon = getWeapon(WeaponType.AXE);
    var unexpectedWeapon = getWeapon(WeaponType.KNIFE);
    testEngineer.equip(getWeapon(WeaponType.BOW));
    testEquipEngineer.equip(expectedWeapon);
    assertEquals(expectedWeapon, testEquipEngineer.getEquippedWeapon());
    testEquipEngineer.equip(unexpectedWeapon);
    assertNotEquals(unexpectedWeapon, testEquipEngineer.getEquippedWeapon());
    testEquipEngineer.unequip();
    assertEquals(null, testEquipEngineer.getEquippedWeapon());
    testEquipEngineer.setHitPoints(0);
    testEquipEngineer.equip(expectedWeapon);
    assertEquals(null, testEquipEngineer.getEquippedWeapon());
  }


  @Override
  @RepeatedTest(500)
  protected void attackTest() {
    assertEquals(testCharacter, getCharacter(CharacterType.ENGINEER));
    checkAttack(getCharacter(CharacterType.ENEMY));
    checkAttack(getCharacter(CharacterType.WHITE_MAGE));
    checkAttack(getCharacter(CharacterType.BLACK_MAGE));
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
    var testInfo = character.getInfo();
    assertEquals(
        CharacterType.ENGINEER,
        CharacterType.valueOf(
            testInfo.get(Stats.CHARACTER_TYPE)));
  }
}
