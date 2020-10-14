package com.github.cc3002.finalreality.model.character;

import com.github.correa.finalreality.model.character.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the Enemy class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";

  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testEnemy.add(new Enemy(ENEMY_NAME, 10, 5,  turns, 10));
  }

  /**
   * Checks that the enemy class constructor and equals method works properly.
   */
  @Test
  void constructorTest() {
    checkEnemyConstruction(new Enemy(ENEMY_NAME, 10, 5, turns, 10),
        testEnemy.get(0),
        new Enemy(ENEMY_NAME, 10, 5, turns, 5),
        new Enemy(ENEMY_NAME, 10, 3, turns, 10),
        new Enemy("Test", 10, 5, turns, 10));
  }
  
}