package com.github.correa.finalreality.model.character.player;

import com.github.correa.finalreality.controller.handlers.IEventHandler;
import com.github.correa.finalreality.model.character.AbstractCharacter;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeSupport;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {

  private IWeapon equippedWeapon;
  private final PropertyChangeSupport playerDeathNotification =
      new PropertyChangeSupport(this);

  /**
   * Creates a new player character.
   * @param turnsQueue
   *  the queue with the characters waiting for their turn.
   * @param name
   * the player character's name.
   * @param maxHitPoints
   *  the player character's maximum hit points.
   * @param defensePoints
   *  the player character's defense points.
   */
  public AbstractPlayerCharacter(
      @NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name,
      int maxHitPoints, int defensePoints) {
    super(turnsQueue, name, maxHitPoints, defensePoints);
    this.equippedWeapon = null;
  }

  @Override
  public int getAttack() {
    if (equippedWeapon == null) {
      return 0;
    }
    else {
      return this.getEquippedWeapon().getDamage();
    }
  }

  @Override
  public abstract void equip(IWeapon weapon);

  @Override
  public void unequip(){
    this.equippedWeapon = null;
  }

  /**
   * Equips the weapon if the character is alive.
   */
  public void equippedBy (IWeapon weapon) {
    if (this.isAlive()){
      this.equippedWeapon = weapon;
    }
  }

  @Override
  public IWeapon getEquippedWeapon() {
    return equippedWeapon;
  }

  @Override
  public int getWeight() {
    if (getEquippedWeapon() != null) {
      return this.equippedWeapon.getWeight();
    }
    return 0;
  }

  @Override
  public void addPlayerDeathListener(IEventHandler playerDeathHandler) {
    playerDeathNotification.addPropertyChangeListener(
        playerDeathHandler);
  }

  @Override
  public void setHitPoints(int hitPoints) {
    super.setHitPoints(hitPoints);
    if (hitPoints <= 0) {
      playerDeathNotification.firePropertyChange(
          "PLAYER_DEATH", null, this);
    }
  }

}
