package com.github.correa.finalreality.controller;

import com.github.correa.finalreality.enums.CharacterType;
import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.enums.WeaponType;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.enemy.Enemy;
import com.github.correa.finalreality.model.character.player.IPlayerCharacter;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Engineer;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Knight;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Thief;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.BlackMage;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.WhiteMage;
import com.github.correa.finalreality.model.weapon.IWeapon;
import com.github.correa.finalreality.model.weapon.commonweapons.Axe;
import com.github.correa.finalreality.model.weapon.commonweapons.Bow;
import com.github.correa.finalreality.model.weapon.commonweapons.Knife;
import com.github.correa.finalreality.model.weapon.commonweapons.Sword;
import com.github.correa.finalreality.model.weapon.magicweapons.Staff;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A class that holds the basic methods of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class GameController {
  private final ArrayList<IPlayerCharacter> playerCharacters =
      new ArrayList<>();
  private final ArrayList<Enemy> enemies =
      new ArrayList<>();
  private final ArrayList<IWeapon> weapons =
      new ArrayList<>();
  private final HashMap<IPlayerCharacter,
      HashMap<Stats, String>> playerCache =
      new HashMap<>();
  private final HashMap<Enemy, HashMap<Stats, String>> enemyCache =
      new HashMap<>();
  private final HashMap<IWeapon, HashMap<Stats, String>> weaponCache =
      new HashMap<>();
  private final BlockingQueue<ICharacter> queue =
      new LinkedBlockingQueue<>();
  private int enemiesKilled;
  private int playersKilled;

  public GameController() {
    this.enemiesKilled = 0;
    this.playersKilled = 0;
  }

  /**
   * Creates a player character and and adds it to the player's party.
   */
  public void createPlayerCharacter (
      CharacterType characterType,
      @NotNull String name,
      int maxHitPoints, int defensePoints,
      int manaPoints) {
    if (characterType == CharacterType.ENGINEER) {
      playerCharacters.add(
          new Engineer(
              queue, name, maxHitPoints,
              defensePoints));
    }
    else if (characterType == CharacterType.KNIGHT) {
      playerCharacters.add(
          new Knight(
              queue, name, maxHitPoints,
              defensePoints));
    }
    else if (characterType == CharacterType.THIEF) {
      playerCharacters.add(
          new Thief(
              queue, name, maxHitPoints,
              defensePoints));
    }
    else if (characterType == CharacterType.BLACK_MAGE) {
      playerCharacters.add(
          new BlackMage(
              queue, name, maxHitPoints,
              defensePoints, manaPoints));
    }
    else if (characterType == CharacterType.WHITE_MAGE) {
      playerCharacters.add(
          new WhiteMage(
              queue, name, maxHitPoints,
              defensePoints, manaPoints));
    }
  }

  /**
   * Creates an enemy character and and adds it to the enemies party.
   */
  public void createEnemyCharacter (
      @NotNull String name,
      int maxHitPoints, int damage,
      int defensePoints, int weight) {
      enemies.add(
          new Enemy(name, maxHitPoints, damage,
              defensePoints, queue, weight));
  }

  /**
   * Creates a weapon and adds it to the player's inventory.
   */
  public void createWeapon (
      WeaponType weaponType,
      @NotNull String name, int damage,
      int weight, int magicDamage) {
    if (weaponType == WeaponType.AXE) {
      weapons.add(
          new Axe(name, damage, weight));
    }
    else if (weaponType == WeaponType.KNIFE) {
      weapons.add(
          new Knife(name, damage, weight));
    }
    else if (weaponType == WeaponType.BOW) {
      weapons.add(
          new Bow(name, damage, weight));
    }
    else if (weaponType == WeaponType.SWORD) {
      weapons.add(
          new Sword(name, damage, weight));
    }
    else if (weaponType == WeaponType.STAFF) {
      weapons.add(
          new Staff(name, damage, weight,
              magicDamage));
    }
  }

  /**
   * Returns the player's inventory.
   */
  public ArrayList<IWeapon> getWeapons() {
    return weapons;
  }

  /**
   * Returns the player's character list.
   */
  public ArrayList<IPlayerCharacter> getPlayerCharacters() {
    return new ArrayList<>(playerCharacters);
  }

  /**
   * Returns the enemies list.
   */
  public ArrayList<Enemy> getEnemies() {
    return new ArrayList<>(enemies);
  }

  /**
   * Returns the player characters and their current status.
   */
  public HashMap<IPlayerCharacter,
      HashMap<Stats, String>> getPlayerCharactersInfo() {
    for (var playerCharacter : playerCharacters) {
      getPlayerCharacterInfo(playerCharacter);
    }
    return playerCache;
  }

  /**
   * Returns the character current status.
   */
  public HashMap<Stats, String> getPlayerCharacterInfo(
      IPlayerCharacter character) {
    if (playerCache.containsKey(character)) {
      return playerCache.get(character);
    }
    var info = character.getInfo();
    playerCache.put(character, info);
    return info;
  }

  /**
   * Returns the enemies and their current status.
   */
  public HashMap<Enemy,
      HashMap<Stats, String>> getEnemiesInfo() {
    for (var enemy : enemies) {
      getEnemyInfo(enemy);
    }
    return enemyCache;
  }

  /**
   * Returns the enemy current status.
   */
  public HashMap<Stats, String> getEnemyInfo(
      Enemy enemy) {
    if (enemyCache.containsKey(enemy)) {
      return enemyCache.get(enemy);
    }
    var info = enemy.getInfo();
    enemyCache.put(enemy, info);
    return info;
  }

  /**
   * Returns the weapons and their stats.
   */
  public HashMap<IWeapon,
      HashMap<Stats, String>> getWeaponsInfo() {
    for (var weapon : weapons) {
      getWeaponInfo(weapon);
    }
    return weaponCache;
  }

  /**
   * Returns the weapon stats.
   */
  public HashMap<Stats, String> getWeaponInfo(
      IWeapon weapon) {
    if (weaponCache.containsKey(weapon)) {
      return weaponCache.get(weapon);
    }
    var info = weapon.getInfo();
    weaponCache.put(weapon, info);
    return info;
  }

  /**
   * Updates the player characters cache.
   */
  public void updatePlayerCache(
      IPlayerCharacter playerCharacter) {
    playerCache.replace(
        playerCharacter, playerCharacter.getInfo());
  }

  /**
   * Updates the enemy cache.
   */
  public void updateEnemyCache(
      Enemy enemy) {
    enemyCache.replace(
        enemy, enemy.getInfo());
  }

  /**
   * Tries to equip the weapon to te player character.
   */
  public void equip(
      IWeapon weapon,
      IPlayerCharacter playerCharacter) {
      playerCharacter.equip(weapon);
  }

  /**
   * A character attacks another one.
   */
  public void attack(
      ICharacter characterAtq,
      ICharacter characterDef) {
    characterAtq.attack(characterDef);
  }

  public BlockingQueue<ICharacter> getQueue() {
    return queue;
  }

  /**
   * Gets the first character from the queue.
   */
  public ICharacter getFistFromQueue() {
    return queue.peek();
  }

  /**
   * Removes the character from the queue.
   */
  public void removeFromQueue(
      ICharacter character) {
    queue.remove(character);
  }

  /**
   * A character starts waiting for his turn.
   */
  public void waitTurn(
      ICharacter character) {
    character.waitTurn();

  }

  /**
   * Checks if the queue is empty.
   */
  public boolean isQueueEmpty() {
    return queue.isEmpty();
  }

  /**
   * Waits until the queue has at least one element.
   */
  public void waitQueue() {
    AtomicBoolean done = new AtomicBoolean(false);
    do {
      done.set(!queue.isEmpty());
    } while (!done.get());
  }
}
