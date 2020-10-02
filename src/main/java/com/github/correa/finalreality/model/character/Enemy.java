package com.github.correa.finalreality.model.character;

import com.github.correa.finalreality.model.character.player.CharacterClass;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author <Benjamín Correa>
 */
public class Enemy extends AbstractCharacter {

  private final int weight;
  private ScheduledExecutorService scheduledExecutor;

  /**
   * Creates a new character.
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   * @param name
   *     the enemy's name
   * @param healthPoints
   *     the enemy's healthPoints
   * @param defensePoints
   *     the enemy's defensePoints
   * @param weight
   *     the weight of this enemy
   */
  public Enemy(@NotNull final String name,
      int healthPoints, int defensePoints,
      @NotNull final BlockingQueue<ICharacter> turnsQueue,
      final int weight) {
    super(turnsQueue, name, healthPoints, defensePoints);
    this.weight = weight;
  }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

  @Override
  public void waitTurn(){
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    var enemy = (Enemy) this;
    scheduledExecutor.schedule(this::addToQueue, enemy.getWeight() / 10, TimeUnit.SECONDS);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return getWeight() == enemy.getWeight();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getWeight());
  }
}
