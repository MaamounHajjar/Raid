package raid.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import raid.managers.AssetManager;

public class PausedScreen {
    private final BufferedImage menuImage;

    public PausedScreen() {
        this.menuImage = null;
    }
 
    public PausedScreen(AssetManager assets) {
        this.menuImage = assets.getMenuImage();
    }
    
    public void draw(Graphics g) {
        if (menuImage != null) {g.drawImage(menuImage, 0, 0, 1272, 720, null);}
        g.setFont(new Font("Arial", Font.BOLD, 64));
        g.setColor(Color.WHITE);
        
        g.drawString("Game Paused", 430, 360);

    }
}
