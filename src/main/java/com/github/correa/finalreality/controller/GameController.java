package com.github.correa.finalreality.controller;

import com.github.correa.finalreality.controller.handlers.EnemyDeathHandler;
import com.github.correa.finalreality.controller.handlers.IEventHandler;
import com.github.correa.finalreality.controller.handlers.NonEmptyQueueHandler;
import com.github.correa.finalreality.controller.handlers.PlayerDeathHandler;
import com.github.correa.finalreality.controller.states.StartGameState;
import com.github.correa.finalreality.controller.states.State;
import com.github.correa.finalreality.controller.states.exceptions.InvalidMovementException;
import com.github.correa.finalreality.controller.states.exceptions.InvalidTransitionException;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A class that holds the basic methods of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */

public class GameController {
  private final ArrayList<IPlayerCharacter> playerCharacters;
  private final ArrayList<Enemy> enemies;
  private final ArrayList<IWeapon> weapons;
  private final HashMap<IPlayerCharacter,
      HashMap<Stats, String>> playerCache;
  private final HashMap<Enemy, HashMap<Stats, String>> enemyCache;
  private final HashMap<IWeapon, HashMap<Stats, String>> weaponCache;
  private final BlockingQueue<ICharacter> queue;
  private final IEventHandler playerDeathHandler =
      new PlayerDeathHandler(this);
  private final IEventHandler enemyDeathHandler =
      new EnemyDeathHandler(this);
  private final IEventHandler nonEmptyQueueHandler =
      new NonEmptyQueueHandler(this);
  private int enemiesKilled;
  private int playersKilled;
  private ICharacter characterPlaying;
  private State state;
  private int partySize;
  protected long seed;
  protected Random rng;
  private ICharacter objective;

  /**
   * Creates a new game controller.
   */
  public GameController() {
    this.playerCharacters = new ArrayList<>();
    this.enemies = new ArrayList<>();
    this.weapons = new ArrayList<>();
    this.playerCache = new HashMap<>();
    this.enemyCache  = new HashMap<>();
    this.weaponCache = new HashMap<>();
    this.queue  = new LinkedBlockingQueue<>();
    this.enemiesKilled = 0;
    this.playersKilled = 0;
    this.characterPlaying = null;
    this.state = new StartGameState(this);
    this.partySize = 0;
    this.objective = null;
  }



  /**
   * Creates a player character and and adds it to the player's party.
   */
  public void createPlayerCharacter (
      CharacterType characterType,
      @NotNull String name,
      int maxHitPoints, int defensePoints,
      int manaPoints) {
    if (getPlayerCharacters().size() < partySize) {
      if (characterType == CharacterType.ENGINEER) {
        var character = new Engineer(
            queue, name, maxHitPoints, defensePoints);
        character.addPlayerDeathListener(playerDeathHandler);
        character.addNonEmptyQueueListener(nonEmptyQueueHandler);
        playerCharacters.add(character);
      }
      else if (characterType == CharacterType.KNIGHT) {
        var character = new Knight(
            queue, name, maxHitPoints, defensePoints);
        character.addPlayerDeathListener(playerDeathHandler);
        character.addNonEmptyQueueListener(nonEmptyQueueHandler);
        playerCharacters.add(character);
      }
      else if (characterType == CharacterType.THIEF) {
        var character = new Thief(
            queue, name, maxHitPoints, defensePoints);
        character.addPlayerDeathListener(playerDeathHandler);
        character.addNonEmptyQueueListener(nonEmptyQueueHandler);
        playerCharacters.add(character);
      }
      else if (characterType == CharacterType.BLACK_MAGE) {
        var character = new BlackMage(
            queue, name, maxHitPoints, defensePoints, manaPoints);
        character.addPlayerDeathListener(playerDeathHandler);
        character.addNonEmptyQueueListener(nonEmptyQueueHandler);
        playerCharacters.add(character);
      }
      else if (characterType == CharacterType.WHITE_MAGE) {
        var character = new WhiteMage(
            queue, name, maxHitPoints, defensePoints, manaPoints);
        character.addPlayerDeathListener(playerDeathHandler);
        character.addNonEmptyQueueListener(nonEmptyQueueHandler);
        playerCharacters.add(character);
      }
    }
  }

  /**
   * Creates an enemy character and and adds it to the enemies party.
   */
  public void createEnemyCharacter (
      @NotNull String name,
      int maxHitPoints, int damage,
      int defensePoints, int weight) {
    var character = new Enemy(
        name, maxHitPoints, damage, defensePoints, queue, weight);
    character.addEnemyDeathListener(enemyDeathHandler);
    character.addNonEmptyQueueListener(nonEmptyQueueHandler);
    enemies.add(character);
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
    return new ArrayList<>(weapons);
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
    playerCache.clear();
    for (var playerCharacter : playerCharacters) {
      playerCache.put(
          playerCharacter,
          getPlayerCharacterInfo(playerCharacter));
    }
    return playerCache;
  }

  /**
   * Returns the character current status.
   */
  public HashMap<Stats, String> getPlayerCharacterInfo(
      IPlayerCharacter character) {
    var info = character.getInfo();
    return info;
  }

  /**
   * Returns the enemies and their current status.
   */
  public HashMap<Enemy,
      HashMap<Stats, String>> getEnemiesInfo() {
    enemyCache.clear();
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
   * Tries to equip the weapon to te player character.
   */
  public void equip(
      IPlayerCharacter playerCharacter,
      IWeapon weapon) {
      playerCharacter.equip(weapon);
  }

  /**
   * A character attacks another one.
   */
  public void attack(
      ICharacter attacker,
      ICharacter defender) {
    attacker.attack(defender);
  }

  public BlockingQueue<ICharacter> getQueue() {
    return new LinkedBlockingQueue<>(queue);
  }

  /**
   * Gets the first character from the queue.
   */
  public ICharacter getFirstFromQueue() {
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
   * Returns the character that is playing.
   */
  public ICharacter getCharacterPlaying() {
    return characterPlaying;
  }

  /**
   * Begins a character turn.
   */
  public void beginTurn() {
    characterPlaying = getFirstFromQueue();
  }

  /**
   * Ends a character turn.
   */
  public void endTurn() {
    removeFromQueue(characterPlaying);
    waitTurn(characterPlaying);
    characterPlaying.unequip();
    characterPlaying = null;
  }

  /**
   * Returns the death enemies count
   */
  public int getEnemiesKilled() {
    return enemiesKilled;
  }

  /**
   * Returns the death players count
   */
  public int getPlayersKilled() {
    return playersKilled;
  }

  /**
   * Adds a player to the death count and checks if the enemies have won.
   */
  public void onPlayerDeath(IPlayerCharacter character) {
    playersKilled += 1;
    playerCharacters.remove(character);
    if (playerCharacters.isEmpty()){
      tryToEndGame();
    }
  }

  /**
   * Adds a enemy to the death count and checks if the players have won.
   */
  public void onEnemyDeath(Enemy enemy) {
    enemiesKilled += 1;
    enemies.remove(enemy);
    if (enemies.isEmpty()) {
      tryToEndGame();
    }
  }

  /**
   * Shuffles and adds the characters to the queue.
   */
  public void startGame() {
    var list = new ArrayList<ICharacter>();
    list.addAll(getEnemies());
    list.addAll(getPlayerCharacters());
    Collections.shuffle(list);
    Collections.shuffle(list);
    queue.addAll(list);
  }

  /**
   * Sets the player party max size.
   */
  public void setPartySize(int partySize) {
    this.partySize = partySize;
  }

  /**
   * Returns the player party size.
   */
  public int getPartySize() {
    return this.partySize;
  }

  /**
   * Sets the actual state.
   */
  public void setState(final @NotNull State state) {
    this.state = state;
  }

  /**
   * Generates a random number.
   */
  public int generateRandom(int minValue, int maxValue) {
    seed = new Random().nextLong();
    rng = new Random(seed);
    return rng.nextInt(maxValue - minValue + 1) + minValue;
  }

  /**
   * Returns random Stats.
   */
  public HashMap<Stats, Integer> getRandomStats(){
    var stats = new HashMap<Stats, Integer>();
    stats.put(Stats.MAX_HP, generateRandom(50, 150));
    stats.put(Stats.DEF, generateRandom(0, 25));
    stats.put(Stats.DMG, generateRandom(20, 90));
    stats.put(Stats.WEIGHT, generateRandom(100, 200));
    stats.put(Stats.MAX_MP, generateRandom(10, 30));
    return stats;
  }

  /**
   * Cleans the enemy party.
   */
  public void cleanEnemyParty () {
    enemies.clear();
  }

  /**
   * Tries to end the turn.
   */
  public void tryToEndTurn(){
    try {
      state.endTurn();
    } catch (InvalidMovementException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tries to end the game.
   */
  public void tryToEndGame(){
    try {
      state.endGame();
    } catch (InvalidMovementException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tries to attack.
   */
  public void tryToAttack(
      ICharacter attacker, ICharacter defender) {
    try {
      state.attack(attacker, defender);
    } catch (InvalidMovementException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tries to start the game.
   */
  public void tryToStartGame() {
    try {
      state.startGame();
    } catch (InvalidMovementException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tries to begin the character's turn.
   */
  public void tryToBeginTurn(){
    try {
      state.beginTurn();
    } catch (InvalidMovementException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tries to equip a weapon to the character.
   */
  public void tryEquip(
      IPlayerCharacter character, IWeapon weapon) {
    try {
      state.equip(character, weapon);
    } catch (InvalidMovementException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sets the character's objective.
   */
  public void setObjective(ICharacter objective) {
    this.objective = objective;
  }

  /**
   * Tries to set the character's objective.
   */
  public void tryToSetObjective(ICharacter objective) {
    try {
      state.setObjective(objective);
    } catch (InvalidMovementException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tries to go to end turn.
   */
  public void tryToGoToEndTurn(){
    try {
      state.toEndTurnState();
    } catch (InvalidTransitionException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tries to go to selecting target.
   */
  public void tryToGoToSelectingTarget() {
    try {
      state.toSelectingTargetState();
    } catch (InvalidTransitionException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tries to go to begin turn state.
   */
  public void tryToGoToBeginTurnState(){
    try {
      state.toBeginTurnState();
    } catch (InvalidTransitionException e) {
      e.printStackTrace();
    }
  }

  /**
   * Return the current objective.
   */
  public ICharacter getObjective(){
    return objective;
  }

  /**
   * Returns the controller's actual state.
   */
  public String getState(){
    return state.toString();
  }

  /**
   * Returns the characters that are alive.
   */
  public ArrayList<String> getAliveCharacters(){
    var info = new ArrayList<String>();
    for (var character : getPlayerCharacters()) {
      info.add(character.toString());
    }
    for (var character : getEnemies()) {
      info.add(character.toString());
    }
    return info;
  }

  /**
   * Finds the character and returns it.
   */
  public ICharacter findCharacter(String info) {
    for (var character : getPlayerCharacters()) {
      if (character.toString().equals(info)){
        return character;
      }
    }
    for (var character : getEnemies()) {
      if (character.toString().equals(info)) {
        return character;
      }
    }
    return null;
  }

  /**
   * Finds the weapon and returns it.
   */
  public IWeapon findWeapon(String info) {
    for (var weapon : getWeapons()) {
      if (weapon.toString().equals(info)){
        return weapon;
      }
    }
    return null;
  }

  /**
   * Returns an array with the name of the weapons.
   */
  public ArrayList<String> getWeaponsString(){
    var info = new ArrayList<String>();
    for (var weapon : getWeapons()) {
      info.add(weapon.toString());
    }
    return info;
  }

}
