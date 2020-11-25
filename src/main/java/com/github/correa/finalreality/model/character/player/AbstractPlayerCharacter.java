package com.github.correa.finalreality.model.character.player;

import com.github.correa.finalreality.model.character.AbstractCharacter;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {

  private IWeapon equippedWeapon;

  /**
   * Creates a new player character.
   * @param turnsQueue
   *  the queue with the characters waiting for their turn.
   * @param name
   * the player character's name.
   * @param hitPoints
   *  the player character's hit points.
   * @param defensePoints
   *  the player character's defense points.
   */
  public AbstractPlayerCharacter(
      @NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name,
      int hitPoints, int defensePoints) {
    super(turnsQueue, name, hitPoints, defensePoints);
    this.equippedWeapon = null;
  }

  @Override
  public int getAttack() {
    return this.getEquippedWeapon().getDamage();
  }

  @Override
  public abstract void equip(IWeapon weapon);

  public void equippedBy (IWeapon weapon) {
    if (this.isAlive() == true){
      this.equippedWeapon = weapon;
    }
  }

  @Override
  public IWeapon getEquippedWeapon() { return equippedWeapon; }

  @Override
  public int getWeight() {return this.equippedWeapon.getWeight();  }

}
