package td.entities;

import td.entities.enemy.Enemy;

/**
 * Created by andrew_sayegh on 6/1/17.
 */

public class WaveManager {

    private float timeSinceLastWave, timeBeweenEnemies;
    private int waveNumber, enemiesPerWave;
    private Enemy[] enemyTypes;
    private Wave currentWave;

    public WaveManager(Enemy[] enemyTypes, float timeBeweenEnemies, int enemiesPerWave) {
        this.enemyTypes = enemyTypes;
        this.enemiesPerWave = enemiesPerWave;
        this.timeBeweenEnemies = timeBeweenEnemies;
        this.timeSinceLastWave = 0;
        this.waveNumber = 0;
        this.currentWave = null;
        newWave();
    }

    public void update() {
        if (!currentWave.isCompleted()) {
            currentWave.update();
        } else {
            newWave();
        }
    }

    private void newWave() {
        currentWave = new Wave(enemyTypes, timeBeweenEnemies, enemiesPerWave);
        waveNumber++;
    }

    public Wave getCurrentWave() {
        return currentWave;
    }

    public int getWaveNumber() {
        return waveNumber;
    }
}
