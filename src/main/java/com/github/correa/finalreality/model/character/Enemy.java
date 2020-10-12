package com.github.correa.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Benjamín Correa Karstulovic.
 */
public class Enemy extends AbstractCharacter {

  private final int weight;

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

  @Override
  public int getWeight() {
    return weight;
  }

  @Override
  public int hashCode() {
    int hash = Objects.hash(getWeight(), getDefensePoints(), getHealthPoints());
    return hash;
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
    return getWeight() == enemy.getWeight()
        && getDefensePoints() == enemy.getDefensePoints()
        && getHealthPoints() == enemy.getHealthPoints();
  }

}
