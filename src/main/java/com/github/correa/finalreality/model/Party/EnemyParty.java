package com.github.correa.finalreality.model.Party;

import com.github.correa.finalreality.model.character.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnemyParty {

    private final List<Enemy> enemyParty = new ArrayList<>();

    /**
     *Adds a Enemy to the EnemyParty
     */
    public void addEnemy(Enemy enemy) {
        enemyParty.add(enemy);
    }

    /**
     * Return's the actual EnemyParty
     */
    public List<Enemy> getEnemyParty() {
        return List.copyOf(enemyParty);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(EnemyParty.class);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EnemyParty)) {
            return false;
        }
        final EnemyParty enemyParty = (EnemyParty) o;
        return getEnemyParty().equals(enemyParty.getEnemyParty());
    }
}
