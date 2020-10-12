package com.github.correa.finalreality.model.Party;

import com.github.correa.finalreality.model.character.player.PlayerCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerParty {

    private final List<PlayerCharacter> playerParty = new ArrayList<>();

    /**
     *Adds a PlayerCharacter to the PlayerParty
     */
    public void addPlayerCharacter(PlayerCharacter playerCharacter) {
        if (playerParty.size() < 4){
            playerParty.add(playerCharacter);
        }
    }

    /**
     * Return's the actual PlayerParty
     */
    public List<PlayerCharacter> getPlayerParty() {
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
