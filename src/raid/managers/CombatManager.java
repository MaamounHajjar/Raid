package raid.managers;

import raid.entity.Arrow;
import raid.entity.Enemy;
import raid.entity.LivingEntity;
import raid.entity.Player;
import raid.entity.Villager;
import raid.input.KeyHandler;

public class CombatManager {
    private String activityLog = "";
    
    public void enemyAttackTarget(Enemy enemy, LivingEntity target) {
        if (target != null && enemy.hasCollidedWithHuman(target) && enemy.getAttackCooldown() == 0) {
            target.takeDamage(enemy.getDamage());
            activityLog = target.getName() + " have been hit by a " + enemy.getName();
            enemy.resetAttackCooldown();
        }
        if (target != null && target.isDead() && enemy.hasCollidedWithHuman(target)) {
            activityLog = target.getName() +  " killed by a " + enemy.getName();
        }
    }
    
    public void playerAttackEnemy(Player player, Enemy enemy, KeyHandler keyH) {
        if (player.hasCollidedWithEnemy(enemy) && keyH.isMeleeAttackPressed() && player.equippedSword() && player.getAttackCooldown() == 0) {
            boolean wasAlive = !enemy.isDead();
            enemy.takeDamage(player.getDamage());
            
            activityLog = "You have hit a " + enemy.getName() + " with a sword";
            player.resetAttackCooldown();
            
            if (wasAlive && enemy.isDead()) {
                enemy.rewardPlayer(player);
                activityLog = "You killed a " + enemy.getName() + " with a sword";
            }
        }
    }

    public void arrowHitEnemy(Arrow arrow, Enemy enemy, Player player) {
        if (arrow.hitEnemy(enemy)) {
            enemy.takeDamage(arrow.getDamage());
            arrow.deleteArrow();
            activityLog = "You have hit a " + enemy.getName() + " with a bow";
        }

        if (enemy.isDead()) {
            enemy.rewardPlayer(player);
            activityLog = "You killed a " + enemy.getName() + " with a bow";
        }
    }

    public LivingEntity chooseTarget(Villager villager, Player player, Enemy enemy) {
        if (villager != null) {
            int enemyDistanceToPlayer = Math.max(Math.abs(player.getX() - enemy.getX()), Math.abs(player.getY() - enemy.getY()));
            int enemyDistanceToVillager = Math.max(Math.abs(villager.getX() - enemy.getX()), Math.abs(villager.getY() - enemy.getY()));
            if (enemyDistanceToPlayer > enemyDistanceToVillager) {
                return villager;
            }
        
            return player;
        }
        return player;
    }
    
    public String getActivityLog() {
        return activityLog;
    }
}
