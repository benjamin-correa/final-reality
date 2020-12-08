package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.enums.WeaponType;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.IPlayerCharacter;
import com.github.correa.finalreality.model.weapon.IWeapon;
import com.github.correa.finalreality.model.weapon.commonweapons.Axe;
import com.github.correa.finalreality.model.weapon.commonweapons.Bow;
import com.github.correa.finalreality.model.weapon.commonweapons.Knife;
import com.github.correa.finalreality.model.weapon.commonweapons.Sword;
import com.github.correa.finalreality.model.weapon.magicweapons.Staff;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Abstract class containing the common tests for all the classes of player characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 * @see IPlayerCharacter
 */
public abstract class AbstractPlayerCharacterTest
    extends AbstractCharacterTest {
  protected IPlayerCharacter testPlayerCharacter;

  /**
   * Checks if the equip method works properly
   */
  @Test
  public abstract void checkEquipTest();

  public void basicSetUp(){
    super.basicSetUp();
  }

  @Test
  public void checkAttack() {
    testPlayerCharacter = (IPlayerCharacter) (testCharacter);
    testPlayerCharacter.unequip();
    testPlayerCharacter.getAttack();
  }

  @Test
  public void checkWeight() {
    testPlayerCharacter = (IPlayerCharacter) (testCharacter);
    testPlayerCharacter.unequip();
    testPlayerCharacter.getWeight();
  }

  @Override
  protected void checkGetInfo(ICharacter character) {
    super.checkGetInfo(character);
    var testInfo = character.getInfo();
    assertEquals(
        Integer.parseInt(testInfo.get(Stats.MAX_HP)), character.getMaxHitPoints());
  }

  /**
   * Returns a weapon for testing.
   */
  protected IWeapon getWeapon(WeaponType weaponType) {
    int TEST_DMG = new Random(seed).nextInt(99) + 1;
    return switch (weaponType) {
      case AXE -> new Axe(
          "Test", TEST_DMG, 10);
      case KNIFE -> new Knife(
          "Test", TEST_DMG, 10);
      case BOW -> new Bow(
          "Test", TEST_DMG, 10);
      case SWORD -> new Sword(
          "Test", TEST_DMG, 10);
      case STAFF -> new Staff(
          "Test", TEST_DMG, 15, 10);
    };
  }


}
