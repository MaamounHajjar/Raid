package raid.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class VictoryScreen {
    public void draw(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 64));
        g.setColor(Color.WHITE);
        g.drawString("VICTORY", 460, 360);

    }
}
