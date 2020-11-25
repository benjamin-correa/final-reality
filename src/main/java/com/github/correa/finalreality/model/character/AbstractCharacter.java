package com.github.correa.finalreality.model.character;

import com.github.correa.finalreality.model.character.enemy.Enemy;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Engineer;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Knight;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Thief;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.BlackMage;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.WhiteMage;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
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
  private int maxHitPoints;
  private int hitPoints;
  private int defensePoints;
  private boolean alive;
  private ScheduledExecutorService scheduledExecutor;
  private Random rng;
  private long seed;

  /**
   * Creates a new Character.
   * @param turnsQueue
   *  the queue with the characters waiting for their turn.
   * @param name
   *  the character's name.
   * @param maxHitPoints
   *  the character's max hit points.
   * @param defensePoints
   *  the character's defense points.
   */
  protected AbstractCharacter(
      @NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, int maxHitPoints,
      int defensePoints) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.maxHitPoints = maxHitPoints;
    this.hitPoints = maxHitPoints;
    this.defensePoints = defensePoints;
    this.alive = true;
    this.rng = new Random();
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
  public long getSeed() {
    return seed;
  }

  @Override
  public void setSeed(final long seed) {
    this.seed = seed;
    this.rng = new Random(seed);
  }

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
  public void setHitPoints(int hitPoints) {
    if (hitPoints <= 0){
      this.alive = false;
      this.hitPoints = 0;
    }
    else {
      this.hitPoints = hitPoints;
    }
  }

  @Override
  public int getDefensePoints() { return defensePoints; }

  @Override
  public boolean isAlive() { return alive; }

  @Override
  public abstract void attack(final ICharacter opponent);

  /**
   * A character receive an attack.
   */
  private void attackedBy(int damage) {
    if ((damage - this.getDefensePoints()) >=
        this.getHitPoints()) {
      this.setHitPoints(0);
    }
    else if (damage <= this.getDefensePoints()) {
      this.setHitPoints(this.getHitPoints() - 1);
    }
    else {
      this.setHitPoints(
          this.getHitPoints() - (damage - this.getDefensePoints()));
    }
  }

  @Override
  public void attackedByEnemy(Enemy enemy) {
    if (enemy.isAlive() == true) {
      this.attackedBy(enemy.getAttack());
    }
  }

  @Override
  public void attackedByEngineer(Engineer engineer) {
    if (engineer.isAlive() == true) {
      this.attackedBy(engineer.getEquippedWeapon().getDamage());
    }
  }

  @Override
  public void attackedByKnight(Knight knight) {
    if (knight.isAlive() == true) {
      this.attackedBy(knight.getEquippedWeapon().getDamage());
    }
  }

  @Override
  public void attackedByThief(Thief thief) {
    if (thief.isAlive() == true) {
      this.attackedBy(thief.getEquippedWeapon().getDamage());
    }
  }

  @Override
  public void attackedByWhiteMage(WhiteMage whiteMage) {
    if (whiteMage.isAlive() == true) {
      this.attackedBy(whiteMage.getEquippedWeapon().getDamage());
    }
  }

  @Override
  public void attackedByBlackMage(BlackMage blackMage) {
    if (blackMage.isAlive() == true) {
      this.attackedBy(blackMage.getEquippedWeapon().getDamage());
    }
  }
}
