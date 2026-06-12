package raid.managers;

import java.awt.Graphics;
import java.util.ArrayList;
import raid.entity.Arrow;
import raid.entity.Enemy;
import raid.entity.Player;
import raid.input.KeyHandler;
import raid.world.BlockManager;

public class ArrowManager {
    private final ArrayList<Arrow> arrows = new ArrayList<>();

   /* public void spawnArrow(Player player, AssetManager assets, KeyHandler keyH) {
        if (keyH.isBowSelected() && keyH.isMeleeAttackPressed() && player.getAttackCooldown() == 0) {
            Arrow arrow = new Arrow("arrow", assets.getArrow(), player.getX(), player.getY(), 20, 15, 15, player.getDamage(), player);

            arrows.add(arrow);
            player.resetAttackCooldown();
        }
    }*/

    public void update(BlockManager blockManager, ArrayList<Enemy> enemies, CombatManager combatManager, Player player, AssetManager assets, KeyHandler keyH) {
        if (keyH.isBowSelected() && keyH.isMeleeAttackPressed() && player.getAttackCooldown() == 0) {
            Arrow arrow = new Arrow("arrow", assets.getArrow(), player.getX(), player.getY(), 20, 15, 15, player.getDamage(), player);

            arrows.add(arrow);
            player.resetAttackCooldown();
        }
        for (Arrow arrow : arrows) {
            arrow.update(blockManager);

            for (Enemy enemy : enemies) {
                if (!enemy.isDead()) {
                    combatManager.arrowHitEnemy(arrow, enemy, player);
                }
            }
        }

        arrows.removeIf(arrow -> !arrow.isAlive());
    }

    public void draw(Graphics g) {
        for (Arrow arrow : arrows) {
            arrow.draw(g);
        }
    }
}
