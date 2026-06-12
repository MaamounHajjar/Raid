package raid.managers;

import java.util.ArrayList;
import raid.entity.Enemy;
import raid.entity.Illager;
import raid.entity.Player;
import raid.entity.Ravager;

public class WaveManager {
    //customize how many waves
    private static final int MAX_WAVES = 5;
    
    //customize player stats increase
    private static final int DAMAGE_INCREASE = 1;
    private static final int HP_INCREASE = 5;

    private int currentWave = 0;
   
    public int getCurrentWave() {
        return currentWave;
    }

    public boolean wavesFinished() {
        return currentWave == MAX_WAVES;
    }
    
    public void startNextWave(ArrayList<Enemy> enemies, AssetManager assets, Player player) {
        if (currentWave < MAX_WAVES) {
            for (int i = 0; i < currentWave + 1; i++) {
                enemies.add(new Illager("Illager", assets.getEnemy("Illager"), 1000, i * 50 + 40));
            }

            for (int i = 0; i < currentWave; i++) {
                enemies.add(new Ravager("Ravager", assets.getEnemy("Ravager"), 1000, 180 + i * 50));
            }
            
            if (currentWave != 0) {
                player.setMaxHp(player.getMaxHp() + HP_INCREASE);
                player.heal();
                player.increaseDamageBy(DAMAGE_INCREASE);
            }
            currentWave++;
        }
    }
}
