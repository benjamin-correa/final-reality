package com.github.correa.finalreality.model.spell;

import java.util.Objects;

/**
 * A class that holds all the information of a spell.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */

public class Spell {

  private final String name;
  private final SpellType type;
  private final int manaCost;

  /**
   * Creates a new spell.
   * @param name
   *  the spell's name.
   * @param type
   *  the spell's type.
   * @param manaCost
   *  the spell's mana cost.
   */
  public Spell(String name, SpellType type, int manaCost) {

    this.name = name;
    this.type = type;
    this.manaCost = manaCost;
  }

  /**
   * Returns this spell's name.
   */
  public String getName() { return name; }

  /**
   * Returns this spell's type.
   */
  public SpellType getType() { return type; }

  /**
   * Returns this spell's Mana cost.
   */
  public int getManaCost() { return manaCost; }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Spell)) {
      return false;
    }
    final Spell spell = (Spell) o;
    return getType() == spell.getType() &&
        getName().equals(spell.getName());
  }

  @Override
  public int hashCode() { return Objects.hash(getName(), getType()); }
}
