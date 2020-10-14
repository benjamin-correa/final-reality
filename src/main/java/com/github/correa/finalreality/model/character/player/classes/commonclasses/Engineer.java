package com.github.correa.finalreality.model.character.player.classes.commonclasses;

import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.correa.finalreality.model.character.player.CharacterClass;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class Engineer extends AbstractPlayerCharacter {


  /**
   * Creates a new Engineer class character.
   *
   * @param turnsQueue
   *  the queue with the characters waiting for their turn.
   * @param name
   *  the Engineer's name.
   * @param hitPoints
   *  the Engineer's hit points.
   * @param defensePoints
   *  the Engineer's defense points.
   */
  public Engineer(@NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, int hitPoints, int defensePoints) {
    super(turnsQueue, name, hitPoints, defensePoints, CharacterClass.ENGINEER);
  }

  @Override
  public int hashCode() { return Objects.hash(getName()); }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Engineer)) {
      return false;
    }
    final Engineer that = (Engineer) o;
    return getName().equals(that.getName());
  }
}
