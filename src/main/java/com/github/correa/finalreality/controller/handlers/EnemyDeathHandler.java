package com.github.correa.finalreality.controller.handlers;

import com.github.correa.finalreality.controller.GameController;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.enemy.Enemy;

import java.beans.PropertyChangeEvent;

/**
 * A class that handle the notification when an enemy died.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class EnemyDeathHandler implements IEventHandler{
  private final GameController controller;

  /**
   * Creates a new enemy death handler.
   * @param controller
   *  the controller that will update the handler.
   */
  public EnemyDeathHandler(final GameController controller) {
    this.controller = controller;
  }

  /**
   * This method gets called when a bound property is changed.
   *
   * @param evt
   *     A PropertyChangeEvent object describing the event source
   *     and the property that has changed.
   */
  @Override
  public void propertyChange(final PropertyChangeEvent evt) {
    controller.onEnemyDeath((Enemy) evt.getNewValue());
    controller.removeFromQueue((ICharacter) evt.getNewValue());

  }
}
