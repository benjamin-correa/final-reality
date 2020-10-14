package com.github.correa.finalreality.model.party;

import com.github.correa.finalreality.model.character.player.AbstractPlayerCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class that has all the player characters in the party.
 *
 * @author Ignacio Slater Muñoz.
 * @author Benjamín Correa Karstulovic.
 */

public class PlayerParty {

    private final List<AbstractPlayerCharacter> playerParty = new ArrayList<>();

    /**
     *Adds a PlayerCharacter to the PlayerParty.
     */
    public void addPlayerCharacter(AbstractPlayerCharacter playerCharacter) {
        if (playerParty.size() < 4){
            playerParty.add(playerCharacter);
        }
    }

    /**
     * Return's the actual PlayerParty.
     */
    public List<AbstractPlayerCharacter> getPlayerParty() {
        return List.copyOf(playerParty);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(PlayerParty.class);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerParty)) {
            return false;
        }
        final PlayerParty playerParty = (PlayerParty) o;
        return getPlayerParty().equals(playerParty.getPlayerParty());
    }
}
