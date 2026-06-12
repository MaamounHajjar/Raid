package raid.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import raid.managers.AssetManager;

public class StartScreen {
    private final BufferedImage menuImage;

    public StartScreen(AssetManager assets) {
        this.menuImage = assets.getMenuImage();
    }

    
    public void draw(Graphics g) {
        g.drawImage(menuImage, 0, 0, 1272, 720, null);
        
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, 1270, 718);
        
        g.setColor(Color.WHITE);

        
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Use WASD to move", 340, 50);

        g.drawString("Press 1 to equip Sword/", 340, 100);
        g.drawString("Press 2 to equip Bow", 340, 150);
        g.drawString("Press F to attack / shoot", 340, 200);

        g.drawString("Press 3/4 to select Healing/Strength Potion", 340, 300);
        g.drawString("Press Y to consume selected potion", 340, 350);

        g.drawString("Press E near a villager to trade", 340, 450);
        g.drawString("Press H to buy Healing Potion", 340, 500);
        g.drawString("Press C to buy Strength Potion", 340, 550);

        g.drawString("Press ESC to pause the game", 340, 600);

        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Press ENTER to start", 340, 700);
        
        
    }
}
