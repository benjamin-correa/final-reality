package com.github.correa.finalreality.controller;

import com.github.correa.finalreality.enums.CharacterType;
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

class GameControllerTest {
  protected GameController gameController;
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

  @BeforeEach
  void setUp() {
    gameController = new GameController();
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

  void randomize() {
    seed = new Random().nextLong();
    rng = new Random(seed);
    HP = rng.nextInt(99) + 1;
    DEF = rng.nextInt(50);
    MANA = rng.nextInt(100);
    DMG = rng.nextInt(99) + 1;
    WEIGHT = rng.nextInt(30) + 10;
  }

  @RepeatedTest(20)
  void checkCreatePlayerCharacterTest() {
    createPlayerCharacters();
    var testPlayerList =
        gameController.getPlayerCharacters();
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

  void createPlayerCharacters() {
    gameController.createPlayerCharacter(
        CharacterType.BLACK_MAGE,
        blackMageName, HP, DEF, MANA);
    gameController.createPlayerCharacter(
        CharacterType.WHITE_MAGE,
        whiteMageName, HP, DEF, MANA);
    gameController.createPlayerCharacter(
        CharacterType.ENGINEER,
        engineerName, HP, DEF, MANA);
    gameController.createPlayerCharacter(
        CharacterType.KNIGHT,
        knightName, HP, DEF, MANA);
    gameController.createPlayerCharacter(
        CharacterType.THIEF,
        thiefName, HP, DEF, MANA);
    gameController.createPlayerCharacter(
        CharacterType.ENEMY,
        thiefName, HP, DEF, MANA);
  }

  @RepeatedTest(20)
  void checkCreateEnemyCharacterTest() {
    createEnemies();
    var testEnemiesList =
        gameController.getEnemies();
    assertTrue(
        testEnemiesList.contains(testEnemy));
  }

  void createEnemies() {
    gameController.createEnemyCharacter(
        enemyName, HP, DMG, DEF, WEIGHT);
  }

  @RepeatedTest(20)
  void checkCreateWeaponTest() {
    createWeapons();
    var testWeaponsList =
        gameController.getWeapons();
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

  void createWeapons() {
    gameController.createWeapon(
        WeaponType.AXE, weaponName,
        DMG, WEIGHT, DMG);
    gameController.createWeapon(
        WeaponType.STAFF, weaponName,
        DMG, WEIGHT, DMG);
    gameController.createWeapon(
        WeaponType.SWORD, weaponName,
        DMG, WEIGHT, DMG);
    gameController.createWeapon(
        WeaponType.KNIFE, weaponName,
        DMG, WEIGHT, DMG);
    gameController.createWeapon(
        WeaponType.BOW, weaponName,
        DMG, WEIGHT, DMG);
  }

  @RepeatedTest(20)
  void getPlayerCharacterInfoTest() {
    createPlayerCharacters();
    var testInfo1 =
        gameController.getPlayerCharacterInfo(testBlackMage);
    assertEquals(
        String.valueOf(HP),testInfo1.get(Stats.HP));
    var testInfoCache =
        gameController.getPlayerCharacterInfo(testBlackMage);
    assertEquals(
        String.valueOf(HP),testInfoCache.get(Stats.HP));
  }

  @RepeatedTest(20)
  void getPlayerCharactersInfoTest() {
    createPlayerCharacters();
    var testInfo =
        gameController.getPlayerCharactersInfo();
    for (var playerCharacter : testInfo.keySet()) {
      var expectedInfo =
          gameController.getPlayerCharacterInfo(playerCharacter);
      var info =
          testInfo.get(playerCharacter);
      assertEquals(expectedInfo, info);
    }
  }

  @RepeatedTest(20)
  void getEnemyInfoTest() {
    createEnemies();
    var testInfo1 =
        gameController.getEnemyInfo(testEnemy);
    assertEquals(
        String.valueOf(HP),testInfo1.get(Stats.HP));
    var testInfoCache =
        gameController.getEnemyInfo(testEnemy);
    assertEquals(
        String.valueOf(HP),testInfoCache.get(Stats.HP));
  }

  @RepeatedTest(20)
  void getEnemiesInfoTest() {
    createEnemies();
    var testInfo =
        gameController.getEnemiesInfo();
    for (var enemy : testInfo.keySet()) {
      var expectedInfo =
          gameController.getEnemyInfo(enemy);
      var info =
          testInfo.get(enemy);
      assertEquals(expectedInfo, info);
    }
  }

  @RepeatedTest(20)
  void getWeaponInfoTest() {
    createWeapons();
    var testInfo1 =
        gameController.getWeaponInfo(testAxe);
    assertEquals(
        String.valueOf(DMG),testInfo1.get(Stats.DMG));
    var testInfoCache =
        gameController.getWeaponInfo(testAxe);
    assertEquals(
        String.valueOf(DMG),testInfoCache.get(Stats.DMG));
  }

  @RepeatedTest(20)
  void getWeaponsInfoTest() {
    createWeapons();
    var testInfo =
        gameController.getWeaponsInfo();
    for (var weapon : testInfo.keySet()) {
      var expectedInfo =
          gameController.getWeaponInfo(weapon);
      var info =
          testInfo.get(weapon);
      assertEquals(expectedInfo, info);
    }
  }

  @RepeatedTest(20)
  void equipTest() {
    gameController.createPlayerCharacter(
        CharacterType.ENGINEER,
        engineerName, HP, DEF, MANA);
    gameController.createWeapon(
        WeaponType.AXE, weaponName,
        DMG, WEIGHT, DMG);
    gameController.equip(
        testAxe, testEngineer);
    assertEquals(
        testEngineer.getEquippedWeapon(), testAxe);
    assertEquals(
        String.valueOf(DMG),
        gameController.getPlayerCharacterInfo(
            testEngineer).get(Stats.DMG));
  }

  @RepeatedTest(20)
  void updateEnemyCacheTest() {
    createEnemies();
    randomize();
    testEnemy.setHitPoints(HP);
    gameController.updateEnemyCache(
        testEnemy);
    var getInfo =
        gameController.getEnemyInfo(testEnemy);
    assertEquals(
        testEnemy.getInfo().get(Stats.HP),
        getInfo.get(Stats.HP));
  }

  @RepeatedTest(20)
  void updatePlayerCacheTest() {
    createPlayerCharacters();
    randomize();
    testEngineer.setHitPoints(HP);
    gameController.updatePlayerCache(
        testEngineer);
    var getInfo =
        gameController.getPlayerCharacterInfo(testEngineer);
    assertEquals(
        testEngineer.getInfo().get(Stats.HP),
        getInfo.get(Stats.HP));
  }

  @RepeatedTest(500)
  void attackTest() {
    var baseHP = testEnemy.getHitPoints();
    gameController.attack(
        testEnemy, testEnemy);
    assertTrue(
        testEnemy.getHitPoints()
            < baseHP);

  }

  @RepeatedTest(3)
  void waitQueueTest(){
    createEnemies();
    gameController.waitTurn(
        gameController.getEnemies().get(0));
    assertTrue(
        turns.isEmpty());
    gameController.waitQueue();
    assertFalse(
        gameController.isQueueEmpty());
    assertEquals(
        testEnemy,
        gameController.getFistFromQueue());
  }

  @Test
  void isQueueEmptyTest() {
    createEnemies();
    assertTrue(
        gameController.isQueueEmpty());
    gameController.waitTurn(
        gameController.getEnemies().get(0));
    gameController.waitQueue();
    assertFalse(
        gameController.isQueueEmpty());
  }

  @RepeatedTest(3)
  void waitTurnTest() {
    createEnemies();
    gameController.waitTurn(
        gameController.getEnemies().get(0));
    gameController.waitQueue();
    assertEquals(
        testEnemy, gameController.getQueue().peek());
  }

  @Test
  void removeFromQueueTest() {
    createEnemies();
    gameController.waitTurn(
        gameController.getEnemies().get(0));
    gameController.waitQueue();
    assertEquals(
        testEnemy, gameController.getQueue().peek());
    gameController.removeFromQueue(
        gameController.getEnemies().get(0));
    assertTrue(
        gameController.isQueueEmpty());
  }

  @Test
  void turnTest() {
    createEnemies();
    gameController.waitTurn(
        gameController.getEnemies().get(0));
    gameController.waitQueue();
    gameController.beginTurn();
    var ch = gameController.getCharacterPlaying();
    assertEquals(testEnemy, ch);
    gameController.endTurn();
    assertNull(gameController.getCharacterPlaying());
  }
}