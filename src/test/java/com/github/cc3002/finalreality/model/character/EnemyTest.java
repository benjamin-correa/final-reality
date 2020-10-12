package com.github.cc3002.finalreality.model.character;

import com.github.correa.finalreality.model.character.Enemy;
import com.github.correa.finalreality.model.character.player.CharacterClass;
import com.github.correa.finalreality.model.character.player.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";

  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testEnemy.add(new Enemy(ENEMY_NAME, 10, 5,  turns, 10));
    testPlayerCharacters.add(new PlayerCharacter(turns, "Test", 10, 4, CharacterClass.WHITE_MAGE));
  }

  @Test
  void constructorTest() {
    checkEnemyConstruction(new Enemy(ENEMY_NAME, 10, 5, turns, 10),
        testEnemy.get(0),
        new Enemy(ENEMY_NAME, 10, 5, turns, 5),
        new Enemy(ENEMY_NAME, 5, 5, turns, 10),
        new Enemy(ENEMY_NAME, 10, 3, turns, 10),
        new PlayerCharacter(turns, ENEMY_NAME, 10, 5, CharacterClass.WHITE_MAGE));
  }
  
}