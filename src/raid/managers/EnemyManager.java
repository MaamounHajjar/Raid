package raid.managers;

import java.awt.Graphics;
import java.util.ArrayList;
import raid.entity.Enemy;
import raid.entity.LivingEntity;
import raid.entity.Player;
import raid.entity.Villager;
import raid.input.KeyHandler;
import raid.world.BlockManager;

public class EnemyManager {
    private final ArrayList<Enemy> enemies = new ArrayList<>();

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void update(Player player, ArrayList<Villager> villagers, BlockManager blockManager, CombatManager combatManager, KeyHandler keyH) {
        for (Enemy enemy : enemies) {
            if (!enemy.isDead()) {
                Villager closestVillager = getClosestVillager(enemy, villagers);
                LivingEntity target = combatManager.chooseTarget(closestVillager, player, enemy);

                combatManager.playerAttackEnemy(player, enemy, keyH);
                combatManager.enemyAttackTarget(enemy, target);

                enemy.update(target, blockManager);
                enemy.updateAttackCooldown();
            }
        }

        separateEnemies(blockManager);
        enemies.removeIf(Enemy::isDead);
    }

    public void draw(Graphics g) {
        for (Enemy enemy : enemies) {
            if (!enemy.isDead()) {
                enemy.draw(g);
            }
        }
    }

    private void separateEnemies(BlockManager blockManager) {
        for (Enemy enemy : enemies) {
            if (!enemy.isDead()) {
                for (Enemy other : enemies) {
                    if (!other.isDead()) {
                        enemy.separateFrom(other, blockManager);
                    }
                }
            }
        }
    }

    private Villager getClosestVillager(Enemy enemy, ArrayList<Villager> villagers) {
        Villager closest = null;
        double minDistance = Double.MAX_VALUE;

        for (Villager villager : villagers) {
            if (!villager.isDead()) {
                double dx = villager.getX() - enemy.getX();
                double dy = villager.getY() - enemy.getY();
                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < minDistance) {
                    minDistance = distance;
                    closest = villager;
                }
            }
        }

        return closest;
    }
}
