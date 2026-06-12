package raid.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import raid.entity.Enemy;
import raid.entity.Player;
import raid.managers.AssetManager;
import raid.managers.CombatManager;

public class Hud {
    private final BufferedImage heart;
    private final BufferedImage emerald;
    private final BufferedImage healingPotion;
    private final BufferedImage strengthPotion;

    public Hud(AssetManager assets) {
        this.heart = assets.getHeart();
        this.emerald = assets.getEmerald();
        this.healingPotion = assets.getHealingPotion();
        this.strengthPotion = assets.getPotion("Strength");
    }

    public void draw(Graphics g, Player player, CombatManager combatManager) {
        g.drawImage(heart, 100, 665, 20, 20, null);
        g.drawImage(emerald, 150, 660, 28, 28, null);
        g.drawImage(healingPotion, 200, 660, 28, 28, null);
        g.drawImage(strengthPotion, 250, 660, 28, 28, null);


        if (player.getEquippedWeapon() != null) {
            g.drawImage(player.getEquippedWeapon().getSprite(), 0, 0, 32, 32, null);
        }
        if (player.getSelectedPotion() != null) {
            g.drawImage(player.getSelectedPotion().getSprite(), 0, 50, 32, 32, null);     
        }
        

        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(player.getHp()), 125, 680);
        g.drawString(String.valueOf(player.getEmeralds()), 175, 680);
        g.drawString(String.valueOf(player.getHealingPotionsCount()), 225, 680);
        g.drawString(String.valueOf(player.getStrengthPotionsCount()), 275, 680);
        g.drawString("Player Damage: " + String.valueOf(player.getDamage()), 350, 680);
        
        g.drawString("Enemies spawned: " + String.valueOf(Enemy.getTotalSpawned()), 600, 680);



        g.setFont(new Font("Arial", Font.BOLD, 26));
        g.drawString(combatManager.getActivityLog(), 420, 655);

    }

    
}
