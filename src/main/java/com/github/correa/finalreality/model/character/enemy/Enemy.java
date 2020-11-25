package com.github.correa.finalreality.model.character.enemy;

import com.github.correa.finalreality.model.character.AbstractCharacter;
import com.github.correa.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Benjamín Correa Karstulovic.
 */
public class Enemy extends AbstractCharacter {

  private final int weight;
  private final int damage;

  /**
   * Creates a new enemy character.
   * @param turnsQueue
   *  the queue with the characters waiting for their turn.
   * @param name
   *  the enemy's name.
   * @param hitPoints
   *  the enemy's hitPoints.
   * @param defensePoints
   *  the enemy's defensePoints.
   * @param weight
   *   the weight of this enemy.
   */
  public Enemy(@NotNull final String name,
      int hitPoints,int damage, int defensePoints,
      @NotNull final BlockingQueue<ICharacter> turnsQueue,
      final int weight) {
    super(turnsQueue, name, hitPoints, defensePoints);
    this.weight = weight;
    this.damage = damage;
  }

  @Override
  public int getWeight() {
    return weight;
  }

  @Override
  public int getAttack() { return damage; }

  @Override
  public void attack(final @NotNull ICharacter opponent){
    opponent.attackedByEnemy(this);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getWeight(), getName());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy that = (Enemy) o;
    return getName().equals(that.getName())
        && getWeight() == that.getWeight();
  }

}
