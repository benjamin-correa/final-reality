package com.github.correa.finalreality.model.character;

import com.github.correa.finalreality.model.character.player.CharacterClass;
import com.github.correa.finalreality.model.character.player.PlayerCharacter;
import com.github.correa.finalreality.model.weapon.Weapon;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;


import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Benjamín Correa>
 */
public abstract class AbstractCharacter implements ICharacter {

  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  private int healthPoints;
  private int defensePoints;
  private ScheduledExecutorService scheduledExecutor;

  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, int healthPoints, int defensePoints) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.healthPoints = healthPoints;
    this.defensePoints = defensePoints;
  }

  @Override
  public void waitTurn() { }

  @Override
  public void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  @Override
  public String getName() { return name; }

  @Override
  public int getHealthPoints() { return healthPoints; }

  @Override
  public int getDefensePoints() { return defensePoints; }
}
