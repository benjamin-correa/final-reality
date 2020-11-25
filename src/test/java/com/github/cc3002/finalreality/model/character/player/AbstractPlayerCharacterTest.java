package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.correa.finalreality.model.character.player.IPlayerCharacter;
import com.github.correa.finalreality.model.weapon.IWeapon;
import com.github.correa.finalreality.model.weapon.commonweapons.Axe;
import com.github.correa.finalreality.model.weapon.commonweapons.Bow;
import com.github.correa.finalreality.model.weapon.commonweapons.Knife;
import com.github.correa.finalreality.model.weapon.commonweapons.Sword;
import com.github.correa.finalreality.model.weapon.magicweapons.Staff;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Abstract class containing the common tests for all the classes of player characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 * @see IPlayerCharacter
 */
public abstract class AbstractPlayerCharacterTest extends AbstractCharacterTest {
  protected List<IWeapon> testWeapon;

  public void tryToEquip(IPlayerCharacter character) {
    for (var weapon : testWeapon) {
      character.equip(weapon);
    }
  }

  /**
   * Checks if the equip method works properly
   */
  @Test
  public abstract void checkEquipTest();

  public void basicSetUp(){
    super.basicSetUp();
    testWeapon = new ArrayList<>();
    testWeapon.add(new Axe("Test", 6, 10));
    testWeapon.add(new Knife("Test", 6, 10));
    testWeapon.add(new Bow("Test", 6, 10));
    testWeapon.add(new Sword("Test", 6, 10));
    testWeapon.add(new Staff("Test", 6, 15, 10));
  }

}
