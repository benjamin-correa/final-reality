package com.github.correa.finalreality.controller;

import com.github.correa.finalreality.controller.states.State;
import com.github.correa.finalreality.controller.states.exceptions.InvalidMovementException;
import com.github.correa.finalreality.controller.states.exceptions.InvalidTransitionException;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.classes.commonclasses.Knight;
import com.github.correa.finalreality.model.weapon.commonweapons.Axe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class containing the throw test for the State.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 * @see State
 */
public class StateTest {
  private final GameController controller = new GameController();
  private final State state = new State(controller);
  protected Knight testKnight;
  protected String knightName;
  protected Axe testAxe;
  protected String weaponName;
  private final BlockingQueue<ICharacter> turns =
      new LinkedBlockingQueue<>();


  /**
   * Basic set up.
   */
  @BeforeEach
  void setUp(){
    knightName = "TEST_KNIGHT";
    weaponName = "TEST_WEAPON";
    testKnight = new Knight(
        turns, knightName, 100, 100);
    testAxe = new Axe(
        weaponName, 100, 100);
  }

  /**
   * Checks that the trow methods work properly.
   */
  @Test
  void testExceptions(){
    assertThrows(
        InvalidMovementException.class, state::startGame);
    assertThrows(
        InvalidTransitionException.class, state::toBeginTurnState);
    assertThrows(
        InvalidMovementException.class, state::beginTurn);
    assertThrows(
        InvalidTransitionException.class, state::toOnTurnState);
    assertThrows(
        InvalidMovementException.class, () ->{state.equip(testKnight, testAxe);});
    assertThrows(
        InvalidTransitionException.class, state::toSelectingTargetState);
    assertThrows(
        InvalidMovementException.class, () ->{state.setObjective(testKnight);});
    assertThrows(
        InvalidMovementException.class, () ->{state.attack(testKnight, testKnight);});
    assertThrows(
        InvalidTransitionException.class, state::toEndGameState);
    assertThrows(
        InvalidMovementException.class, state::endGame);
    assertThrows(
        InvalidTransitionException.class, state::toEndTurnState);
    assertThrows(
        InvalidMovementException.class, state::endTurn);
  }
}
