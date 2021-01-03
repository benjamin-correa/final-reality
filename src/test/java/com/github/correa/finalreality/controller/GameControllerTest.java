package com.github.correa.finalreality.controller;

import com.github.correa.finalreality.controller.states.BeginTurnState;
import com.github.correa.finalreality.controller.states.EndTurnState;
import com.github.correa.finalreality.controller.states.OnTurnState;
import com.github.correa.finalreality.controller.states.SelectingTargetState;
import com.github.correa.finalreality.enums.CharacterType;
import com.github.correa.finalreality.enums.States;
import com.github.correa.finalreality.enums.Stats;
import com.github.correa.finalreality.enums.WeaponType;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.enemy.Enemy;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Engineer;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Knight;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Thief;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.BlackMage;
import com.github.correa.finalreality.model.character.player.classes.mageclasses.WhiteMage;
import com.github.correa.finalreality.model.weapon.commonweapons.Axe;
import com.github.correa.finalreality.model.weapon.commonweapons.Bow;
import com.github.correa.finalreality.model.weapon.commonweapons.Knife;
import com.github.correa.finalreality.model.weapon.commonweapons.Sword;
import com.github.correa.finalreality.model.weapon.magicweapons.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the test for the Game Controller and the States.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 * @see GameController
 */
class GameControllerTest {
  protected GameController controller;
  private final BlockingQueue<ICharacter> turns =
      new LinkedBlockingQueue<>();
  protected long seed;
  protected Random rng;
  protected int HP;
  protected int DEF;
  protected int MANA;
  protected int DMG;
  protected int WEIGHT;
  protected BlackMage testBlackMage;
  protected WhiteMage testWhiteMage;
  protected Engineer testEngineer;
  protected Thief testThief;
  protected Knight testKnight;
  protected Enemy testEnemy;
  protected String blackMageName;
  protected String whiteMageName;
  protected String engineerName;
  protected String thiefName;
  protected String knightName;
  protected String enemyName;
  protected String weaponName;
  protected Axe testAxe;
  protected Bow testBow;
  protected Sword testSword;
  protected Knife testKnife;
  protected Staff testStaff;

  /**
   * Basic Set up.
   */
  @BeforeEach
  void setUp() {
    controller = new GameController();
    randomize();
    blackMageName = "TEST_MAGE";
    whiteMageName = "TEST_MAGE2";
    engineerName = "TEST_ENGINEER";
    thiefName = "TEST_THIEF";
    knightName = "TEST_KNIGHT";
    enemyName = "TEST_GOBLIN";
    weaponName = "TEST_WEAPON";
    testBlackMage = new BlackMage(
        turns, blackMageName, HP, DEF, MANA);
    testWhiteMage = new WhiteMage(
        turns, whiteMageName, HP, DEF, MANA);
    testEngineer = new Engineer(
        turns, engineerName, HP, DEF);
    testThief = new Thief(
        turns, thiefName, HP, DEF);
    testKnight = new Knight(
        turns, knightName, HP, DEF);
    testEnemy = new Enemy(
        enemyName, HP, DMG,DEF, turns, WEIGHT);
    testAxe = new Axe(
        weaponName, DMG, WEIGHT);
    testBow = new Bow(
        weaponName, DMG, WEIGHT);
    testKnife = new Knife(
        weaponName, DMG, WEIGHT);
    testSword = new Sword(
        weaponName, DMG, WEIGHT);
    testStaff = new Staff(
        weaponName, DMG, WEIGHT, DMG);

  }

  /**
   * Randomize all the stats
   */
  void randomize() {
    seed = new Random().nextLong();
    rng = new Random(seed);
    var stats = controller.getRandomStats();
    HP = stats.get(Stats.MAX_HP);
    DEF = stats.get(Stats.DEF);
    MANA = stats.get(Stats.MAX_MP);
    DMG = stats.get(Stats.DMG);
    WEIGHT = stats.get(Stats.WEIGHT)/5;
  }

  /**
   * Checks that the create methods works properly.
   */
  @RepeatedTest(20)
  void checkCreatePlayerCharacterTest() {
    controller.setPartySize(5);
    createPlayerCharacters();
    var testPlayerList =
        controller.getPlayerCharacters();
    assertTrue(
        testPlayerList.contains(testBlackMage));
    assertTrue(
        testPlayerList.contains(testEngineer));
    assertTrue(
        testPlayerList.contains(testKnight));
    assertTrue(
        testPlayerList.contains(testThief));
    assertTrue(
        testPlayerList.contains(testWhiteMage));
  }

  /**
   * Creates player characters in the controller.
   */
  void createPlayerCharacters() {
    controller.createPlayerCharacter(
        CharacterType.BLACK_MAGE,
        blackMageName, HP, DEF, MANA);
    controller.createPlayerCharacter(
        CharacterType.WHITE_MAGE,
        whiteMageName, HP, DEF, MANA);
    controller.createPlayerCharacter(
        CharacterType.ENGINEER,
        engineerName, HP, DEF, MANA);
    controller.createPlayerCharacter(
        CharacterType.KNIGHT,
        knightName, HP, DEF, MANA);
    controller.createPlayerCharacter(
        CharacterType.THIEF,
        thiefName, HP, DEF, MANA);
    controller.createPlayerCharacter(
        CharacterType.ENEMY,
        thiefName, HP, DEF, MANA);
  }

  /**
   * Checks that the create methods works properly.
   */
  @RepeatedTest(20)
  void checkCreateEnemyCharacterTest() {
    createEnemies();
    var testEnemiesList =
        controller.getEnemies();
    assertTrue(
        testEnemiesList.contains(testEnemy));
  }

  /**
   * Creates enemies in the game controller.
   */
  void createEnemies() {
    controller.createEnemyCharacter(
        enemyName, HP, DMG, DEF, WEIGHT);
  }

  /**
   * Checks that the create methods works properly.
   */
  @RepeatedTest(20)
  void checkCreateWeaponTest() {
    createWeapons();
    var testWeaponsList =
        controller.getWeapons();
    assertEquals(testBow, testWeaponsList.get(4));
    assertTrue(
        testWeaponsList.contains(testAxe));
    assertTrue(
        testWeaponsList.contains(testKnife));
    assertTrue(
        testWeaponsList.contains(testStaff));
    assertTrue(
        testWeaponsList.contains(testSword));
  }

  /**
   * Creates weapons in the model.
   */
  void createWeapons() {
    controller.createWeapon(
        WeaponType.AXE, weaponName,
        DMG, WEIGHT, DMG);
    controller.createWeapon(
        WeaponType.STAFF, weaponName,
        DMG, WEIGHT, DMG);
    controller.createWeapon(
        WeaponType.SWORD, weaponName,
        DMG, WEIGHT, DMG);
    controller.createWeapon(
        WeaponType.KNIFE, weaponName,
        DMG, WEIGHT, DMG);
    controller.createWeapon(
        WeaponType.BOW, weaponName,
        DMG, WEIGHT, DMG);
  }

  /**
   * Checks that get info methods works properly.
   */
  @RepeatedTest(20)
  void getPlayerCharacterInfoTest() {
    createPlayerCharacters();
    var testInfo1 =
        controller.getPlayerCharacterInfo(testBlackMage);
    assertEquals(
        String.valueOf(HP),testInfo1.get(Stats.HP));
    var testInfoCache =
        controller.getPlayerCharacterInfo(testBlackMage);
    assertEquals(
        String.valueOf(HP),testInfoCache.get(Stats.HP));
  }

  /**
   * Checks that get info methods works properly.
   */
  @RepeatedTest(20)
  void getPlayerCharactersInfoTest() {
    createPlayerCharacters();
    var testInfo =
        controller.getPlayerCharactersInfo();
    for (var playerCharacter : testInfo.keySet()) {
      var expectedInfo =
          controller.getPlayerCharacterInfo(playerCharacter);
      var info =
          testInfo.get(playerCharacter);
      assertEquals(expectedInfo, info);
    }
  }

  /**
   * Checks that get info methods works properly.
   */
  @RepeatedTest(20)
  void getEnemyInfoTest() {
    createEnemies();
    var testInfo1 =
        controller.getEnemyInfo(testEnemy);
    assertEquals(
        String.valueOf(HP),testInfo1.get(Stats.HP));
    var testInfoCache =
        controller.getEnemyInfo(testEnemy);
    assertEquals(
        String.valueOf(HP),testInfoCache.get(Stats.HP));
  }

  /**
   * Checks that get info methods works properly.
   */
  @RepeatedTest(20)
  void getEnemiesInfoTest() {
    createEnemies();
    var testInfo =
        controller.getEnemiesInfo();
    for (var enemy : testInfo.keySet()) {
      var expectedInfo =
          controller.getEnemyInfo(enemy);
      var info =
          testInfo.get(enemy);
      assertEquals(expectedInfo, info);
    }
  }

  /**
   * Checks that get info methods works properly.
   */
  @RepeatedTest(20)
  void getWeaponInfoTest() {
    createWeapons();
    var testInfo1 =
        controller.getWeaponInfo(testAxe);
    assertEquals(
        String.valueOf(DMG),testInfo1.get(Stats.DMG));
    var testInfoCache =
        controller.getWeaponInfo(testAxe);
    assertEquals(
        String.valueOf(DMG),testInfoCache.get(Stats.DMG));
  }

  /**
   * Checks that get info methods works properly.
   */
  @RepeatedTest(20)
  void getWeaponsInfoTest() {
    createWeapons();
    var testInfo =
        controller.getWeaponsInfo();
    for (var weapon : testInfo.keySet()) {
      var expectedInfo =
          controller.getWeaponInfo(weapon);
      var info =
          testInfo.get(weapon);
      assertEquals(expectedInfo, info);
    }
  }

  /**
   * Checks that the equip method works properly.
   */
  @RepeatedTest(20)
  void equipTest() {
    controller.createPlayerCharacter(
        CharacterType.ENGINEER,
        engineerName, HP, DEF, MANA);
    controller.createWeapon(
        WeaponType.AXE, weaponName,
        DMG, WEIGHT, DMG);
    controller.equip(
        testEngineer, testAxe);
    assertEquals(
        testEngineer.getEquippedWeapon(), testAxe);
    assertEquals(
        String.valueOf(DMG),
        controller.getPlayerCharacterInfo(
            testEngineer).get(Stats.DMG));
  }


  /**
   * Checks that the attack method works properly.
   */
  @RepeatedTest(500)
  void attackTest() {
    var baseHP = testEnemy.getHitPoints();
    controller.attack(
        testEnemy, testEnemy);
    assertTrue(
        testEnemy.getHitPoints()
            < baseHP);

  }

  /**
   * Checks that is queue empty method works properly.
   */
  @Test
  void isQueueEmptyTest() {
    createEnemies();
    try {
      assertTrue(
          controller.isQueueEmpty());
      controller.waitTurn(
          controller.getEnemies().get(0));
      Thread.sleep((testEnemy.getWeight()* 100L)+50);
      assertFalse(
          controller.isQueueEmpty());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * Checks that remove from queue method works properly.
   */
  @Test
  void removeFromQueueTest() {
    createEnemies();
    controller.startGame();
    assertEquals(
        testEnemy, controller.getQueue().peek());
    controller.removeFromQueue(
        controller.getEnemies().get(0));
    assertTrue(
        controller.isQueueEmpty());

  }

  /**
   * Checks that the equip method works properly.
   */
  @Test
  void turnTest() {
    createEnemies();
    controller.waitTurn(
        controller.getEnemies().get(0));
    try {
      Thread.sleep((testEnemy.getWeight() * 100L) + 50);
      controller.beginTurn();
      var ch = controller.getCharacterPlaying();
      assertEquals(testEnemy, ch);
      controller.endTurn();
      assertNull(controller.getCharacterPlaying());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks that the death listener method works properly.
   */
  @Test
  void deathListenerTest() {
    controller.setState(new SelectingTargetState(controller));
    controller.setPartySize(2);
    controller.createPlayerCharacter(
        CharacterType.BLACK_MAGE,
        blackMageName, HP, DEF, MANA);
    controller.createPlayerCharacter(
        CharacterType.WHITE_MAGE,
        whiteMageName, HP, DEF, MANA);
    var player1 =
        controller.getPlayerCharacters().get(0);
    var player2 =
        controller.getPlayerCharacters().get(1);
    controller.createEnemyCharacter(
        "Killer", 1000,
        1000, 0, 10);
    createEnemies();
    var enemy = controller.getEnemies().get(1);
    var killer = controller.getEnemies().get(0);
    controller.attack(killer, player1);
    assertEquals(
        1, controller.getPlayersKilled());
    controller.attack(killer, enemy);
    assertEquals(
        1, controller.getEnemiesKilled());
    controller.attack(killer, player2);
    assertEquals(
        2, controller.getPlayersKilled());
    assertEquals(
        String.valueOf(
            States.END_GAME_STATE),
        controller.getState());
    controller.setState(new SelectingTargetState(controller));
    controller.attack(killer,killer);
    assertEquals(
        2, controller.getEnemiesKilled());
  }

  /**
   * Checks that the start game method works properly.
   */
  @Test
  void startGameTest() {
    var firstQueue =
        controller.getQueue();
    createEnemies();
    createPlayerCharacters();
    controller.startGame();
    assertNotEquals(
        firstQueue, controller.getQueue());
  }

  /**
   * Checks that the party size method works properly.
   */
  @Test
  void partySizeTest(){
    controller.setPartySize(3);
    assertEquals(3, controller.getPartySize());
  }

  /**
   * Checks that the generate random method works properly.
   */
  @Test
  void generateRandomTest(){
    seed = new Random().nextLong();
    rng = new Random(seed);
    var number1 = rng.nextInt(100);
    seed = new Random().nextLong();
    rng = new Random(seed);
    var number2 = rng.nextInt(100);

    if (number1 >= number2){
      var random = controller.generateRandom(number2, number1);
      assertTrue(random >= number2 & random <= number1);
    }
    else {
      var random = controller.generateRandom(number1, number2);
      assertTrue(random >= number1 & random <= number2);
    }
  }

  /**
   * Checks that the clean enemy party method works properly.
   */
  @Test
  void clearEnemyPartyTest(){
    createEnemies();
    assertFalse(controller.getEnemies().isEmpty());
    controller.cleanEnemyParty();
    assertTrue(controller.getEnemies().isEmpty());
  }

  /**
   * Checks that the try to end turn method works properly.
   */
  @Test
  void tryToEndTurnTest(){
    createEnemies();
    controller.startGame();
    controller.beginTurn();
    controller.setState(new EndTurnState(controller));
    assertEquals(String.valueOf(States.END_TURN_STATE), controller.getState());
    controller.tryToEndTurn();
    assertEquals(
        String.valueOf(
            States.WAITING_STATE),
        controller.getState());
    try {
      Thread.sleep((testEnemy.getWeight() * 100L) + 50);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    assertEquals(String.valueOf(States.BEGIN_TURN_STATE), controller.getState());
  }

  /**
   * Checks that the try to attack method works properly.
   */
  @Test
  void tryToAttack(){
    createEnemies();
    createPlayerCharacters();
    controller.startGame();
    controller.beginTurn();

    controller.setState(new SelectingTargetState(controller));
    assertEquals(
        String.valueOf(
            States.SELECTING_TARGET_STATE),
        controller.getState());

    controller.tryToAttack(testEnemy, testThief);
  }

  /**
   * Checks that the try to start game method works properly.
   */
  @Test
  void tryToStartGame(){
    createEnemies();
    createPlayerCharacters();
    assertEquals(
        String.valueOf(
            States.START_GAME_STATE),
        controller.getState());
    controller.tryToStartGame();
    assertEquals(
        String.valueOf(
            States.BEGIN_TURN_STATE),
        controller.getState());
  }

  /**
   * Checks that the try to begin turn method works properly.
   */
  @Test
  void tryToBeginTurn(){
    createEnemies();
    controller.startGame();
    controller.setState(new BeginTurnState(controller));
    assertEquals(
        String.valueOf(
            States.BEGIN_TURN_STATE),
        controller.getState());
    controller.tryToBeginTurn();
    assertEquals(
        String.valueOf(
            States.ON_TURN_STATE),
        controller.getState());
  }

  /**
   * Checks that the try to equip method works properly.
   */
  @Test
  void tryEquipTest(){
    createPlayerCharacters();
    createWeapons();
    controller.startGame();
    controller.beginTurn();
    controller.setState(new OnTurnState(controller));
    assertEquals(
        String.valueOf(
            States.ON_TURN_STATE),
        controller.getState());
    controller.tryEquip(testKnight, testStaff);
    assertEquals(
        String.valueOf(
            States.ON_TURN_STATE),
        controller.getState());
    controller.tryEquip(testKnight, testAxe);
    assertEquals(
        String.valueOf(
            States.SELECTING_TARGET_STATE),
        controller.getState());
  }

  /**
   * Checks that the set objective method works properly.
   */
  @Test
  void setObjectiveTest(){
    var expectedObjective = testEnemy;
    controller.setObjective(testEnemy);
    assertEquals(expectedObjective, controller.getObjective());
  }

  /**
   * Checks that the try to set objective method works properly.
   */
  @Test
  void tryToSetObjectiveTest() {
    createEnemies();
    controller.startGame();
    controller.beginTurn();
    controller.setState(new SelectingTargetState(controller));
    assertEquals(
        String.valueOf(
            States.SELECTING_TARGET_STATE),
        controller.getState());
    controller.tryToSetObjective(testEnemy);
    assertEquals(
        String.valueOf(
            States.SELECTING_TARGET_STATE),
        controller.getState());
    assertEquals(testEnemy, controller.getObjective());
  }

  /**
   * Checks that the try to go to end turn method works properly.
   */
  @Test
  void tryToGoToEndTurnTest(){
    createEnemies();
    controller.startGame();
    controller.beginTurn();
    controller.setState(new SelectingTargetState(controller));
    assertEquals(
        String.valueOf(
            States.SELECTING_TARGET_STATE),
        controller.getState());
    controller.tryToGoToEndTurn();
    assertEquals(
        String.valueOf(
            States.END_TURN_STATE),
        controller.getState());
  }

  /**
   * Checks that the try to go to selecting target method works properly.
   */
  @Test
  void tryToGoToSelectingTargetTest(){
    createEnemies();
    controller.startGame();
    controller.beginTurn();
    controller.setState(new OnTurnState(controller));
    assertEquals(
        String.valueOf(
            States.ON_TURN_STATE),
        controller.getState());
    controller.tryToGoToSelectingTarget();
    assertEquals(
        String.valueOf(
            States.SELECTING_TARGET_STATE),
        controller.getState());
  }

  /**
   * Checks that the get alive characters method works properly.
   */
  @Test
  void getAliveCharactersTest() {
    controller.setPartySize(5);
    createPlayerCharacters();
    createEnemies();
    controller.createEnemyCharacter(
        "Killer", 1000,
        1000, 0, 10);
    controller.attack(
        controller.getEnemies().get(1),
        controller.getEnemies().get(0));
    controller.attack(
        controller.getEnemies().get(0),
        controller.getPlayerCharacters().get(0));
    var aliveCharacters =
        controller.getAliveCharacters();
    assertFalse(aliveCharacters.contains(testBlackMage.toString()));
    assertTrue(aliveCharacters.contains(testEngineer.toString()));
    assertTrue(aliveCharacters.contains(testKnight.toString()));
    assertTrue(aliveCharacters.contains(testThief.toString()));
    assertTrue(aliveCharacters.contains(testWhiteMage.toString()));
    assertFalse(aliveCharacters.contains(testEnemy.toString()));
  }

  /**
   * Checks that the find character method works properly.
   */
  @Test
  void findCharacterTest(){
    controller.setPartySize(5);
    createEnemies();
    createPlayerCharacters();
    controller.startGame();
    var expectedCharacter = testEngineer;
    var character = controller.findCharacter(testEngineer.toString());
    assertEquals(expectedCharacter, character);
    var expectedEnemy = testEnemy;
    var enemy = controller.findCharacter(testEnemy.toString());
    assertEquals(expectedEnemy, enemy);
    assertNull(controller.findCharacter("null"));
  }

  /**
   * Checks that the find weapon method works properly.
   */
  @Test
  void findWeaponTest(){
    createWeapons();
    var expectedWeapon = testAxe;
    var weapon = controller.findWeapon(testAxe.toString());
    assertEquals(expectedWeapon, weapon);
    assertNull(controller.findWeapon("null"));
  }

  /**
   * Checks that the get weapon strings method works properly.
   */
  @Test
  void getWeaponsStrings() {
    createWeapons();
    var info = controller.getWeaponsString();
    assertTrue(info.contains(testAxe.toString()));
    assertTrue(info.contains(testBow.toString()));
    assertTrue(info.contains(testStaff.toString()));
    assertTrue(info.contains(testSword.toString()));
    assertTrue(info.contains(testKnife.toString()));
  }

}