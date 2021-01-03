package com.github.correa.finalreality.controller.handlers;

import com.github.correa.finalreality.controller.GameController;
import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.IPlayerCharacter;

import java.beans.PropertyChangeEvent;

/**
 * A class that handle the notification when a player died.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class PlayerDeathHandler implements IEventHandler{
  private final GameController controller;

  /**
   * Creates a new player death handler.
   * @param controller
   *  the controller that will update the handler.
   */
  public PlayerDeathHandler(final GameController controller) {
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
    controller.onPlayerDeath((IPlayerCharacter) evt.getNewValue());
    controller.removeFromQueue((ICharacter) evt.getNewValue());
  }
}
