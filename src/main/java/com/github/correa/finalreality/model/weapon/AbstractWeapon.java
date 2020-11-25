package com.github.correa.finalreality.model.weapon;

import com.github.correa.finalreality.model.character.player.classes.commonclasses.Engineer;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Knight;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Thief;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.BlackMage;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.WhiteMage;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public abstract class AbstractWeapon implements IWeapon {

  private final String name;
  private final int damage;
  private final int weight;

  /**
   * Creates a weapon with a name, a base damage, speed and it's type.
   *
   * @see IWeapon
   */
  public AbstractWeapon(final String name, final int damage, final int weight) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public int getWeight() {
    return weight;
  }

  @Override
  public void equippedByEngineer(Engineer engineer){
    return;
  }

  @Override
  public void equippedByKnight(Knight knight){
    return;
  }

  @Override
  public void equippedByThief(Thief thief){
    return;
  }

  @Override
  public void equippedByBlackMage(BlackMage blackMage){
    return;
  }

  @Override
  public void equippedByWhiteMage(WhiteMage whiteMage){
    return;
  }
}
