package com.github.cc3002.finalreality.model.spell;

import com.github.correa.finalreality.model.spell.Spell;
import com.github.correa.finalreality.model.spell.SpellType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Set of tests for the Spell class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */
public class SpellTest {

  private static final String BLACK_SPELL_NAME = "Test Black Spell";
  private static final String WHITE_SPELL_NAME = "Test White Spell";
  private static final int BASIC_MANA_COST = 20;

  private Spell testBlackSpell;
  private Spell testWhiteSpell;
  private Spell testDifferentWhiteSpell;
  private Spell testDifferentTypes;
  private Spell testDifferentTypes2;
  private Spell testDifferentManaCost;

  @BeforeEach
  void setUp(){
    testBlackSpell = new Spell(BLACK_SPELL_NAME, SpellType.BLACK, BASIC_MANA_COST);
    testWhiteSpell = new Spell(WHITE_SPELL_NAME, SpellType.WHITE, BASIC_MANA_COST);

    testDifferentWhiteSpell = new Spell("Test", SpellType.WHITE, BASIC_MANA_COST);
    testDifferentTypes = new Spell(BLACK_SPELL_NAME, SpellType.WHITE, BASIC_MANA_COST);
    testDifferentTypes2 = new Spell("Test 2", SpellType.WHITE, BASIC_MANA_COST);
    testDifferentManaCost = new Spell(WHITE_SPELL_NAME, SpellType.WHITE, 10);
  }

  /**
   * Checks that the spell class constructor and equals method works properly.
   */
  @Test
  void constructorTest() {
    var testEqualBlackSpell = testBlackSpell;

    assertEquals(testBlackSpell, testEqualBlackSpell);
    assertNotEquals(testWhiteSpell, new Object());
    assertNotEquals(testBlackSpell, testDifferentTypes);
    assertEquals(testWhiteSpell, testDifferentManaCost);
    assertNotEquals(testDifferentTypes, testDifferentTypes2);
    assertEquals(testWhiteSpell.hashCode(), testWhiteSpell.hashCode());
    assertNotEquals(
        testWhiteSpell.getManaCost(),
        testDifferentManaCost.getManaCost());
  }

}
