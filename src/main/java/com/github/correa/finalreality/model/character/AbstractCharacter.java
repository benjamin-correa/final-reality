package com.github.correa.finalreality.model.character;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public abstract class AbstractCharacter implements ICharacter {

  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  private final int hitPoints;
  private final int defensePoints;
  private ScheduledExecutorService scheduledExecutor;

  /**
   * Creates a new Character.
   * @param turnsQueue
   *  the queue with the characters waiting for their turn.
   * @param name
   *  the character's name.
   * @param hitPoints
   *  the character's hit points.
   * @param defensePoints
   *  the character's defense points.
   */
  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, int hitPoints, int defensePoints) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.hitPoints = hitPoints;
    this.defensePoints = defensePoints;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(this::addToQueue, this.getWeight() / 10, TimeUnit.SECONDS);
  }

  /**
   * Return this character's weight.
   */
  public abstract int getWeight();

  @Override
  public void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  @Override
  public String getName() { return name; }

  @Override
  public int getHitPoints() { return hitPoints; }

  @Override
  public int getDefensePoints() { return defensePoints; }

}
