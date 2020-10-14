package com.github.correa.finalreality.model.character.player.classes.mageclasses;

import com.github.correa.finalreality.model.character.ICharacter;
import com.github.correa.finalreality.model.character.player.CharacterClass;
import com.github.correa.finalreality.model.character.player.classes.Mage;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class WhiteMage extends Mage {

  /**
   * Creates a new White Mage class character.
   *
   * @param turnsQueue
   *  the queue with the characters waiting for their turn.
   * @param name
   *  the White Mage's name.
   * @param hitPoints
   *  the White Mage's hit points.
   * @param defensePoints
   *  the White Mage's defense points.
   */
  public WhiteMage(@NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, int hitPoints, int defensePoints, int manaPoints) {
    super(turnsQueue, name, hitPoints, defensePoints, manaPoints, CharacterClass.WHITE_MAGE);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof WhiteMage)) {
      return false;
    }
    final WhiteMage that = (WhiteMage) o;
    return getName().equals(that.getName());
  }
}
