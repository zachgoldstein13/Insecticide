package td.entities;

import td.entities.enemy.Enemy;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static td.main.Clock.delta;
import static td.main.Window.TILE_SIZE;

/**
 * Created by andrew_sayegh on 5/31/17.
 */

public class Wave {

    private float timeSinceLastSpawn, spawnTime;
    private Enemy[] enemyTypes;
    private CopyOnWriteArrayList<Enemy> enemies;
    private int enemiesPerWave, enemiesSpawned;
    private boolean waveCompleted;

    public Wave(Enemy[] enemyTypes, float spawnTime, int enemiesPerWave) {
        this.enemyTypes = enemyTypes;
        this.spawnTime = spawnTime;
        this.enemiesPerWave = enemiesPerWave;
        this.timeSinceLastSpawn = 0;
        this.enemiesSpawned = 0;
        this.enemies = new CopyOnWriteArrayList<Enemy>();
        this.waveCompleted = false;


        spawn();
    }

    public void update() {
        boolean allEnemiesDead = true;
        //Assume all enemies are dead, until loop proves otherwise
        if (enemiesSpawned < enemiesPerWave) {
            timeSinceLastSpawn += delta();
            if (timeSinceLastSpawn > spawnTime) {
                spawn();
                timeSinceLastSpawn = 0;
            }
        }

        for (Enemy e : enemies) {
            if (e.isAlive()) {
                allEnemiesDead = false;
                e.update();
                e.draw();
            } else {
                enemies.remove(e);
            }
        }
        if (allEnemiesDead) {
            waveCompleted = true;
        }
    }

    public void spawn() {
        int enemyChosen = 0;
        Random random = new Random();
        enemyChosen = random.nextInt(enemyTypes.length);
        enemies.add(
                new Enemy(
                        enemyTypes[enemyChosen].getTexture(),
                        enemyTypes[enemyChosen].getStartTile(),
                        enemyTypes[enemyChosen].getGrid(),
                        TILE_SIZE,
                        TILE_SIZE,
                        enemyTypes[enemyChosen].getSpeed(),
                        enemyTypes[enemyChosen].getHealth()
                ));
        enemiesSpawned++;
    }

    public boolean isCompleted() {
        return waveCompleted;
    }

    public CopyOnWriteArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
