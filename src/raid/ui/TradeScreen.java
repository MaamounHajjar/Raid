package raid.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TradeScreen {
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 220));
        g.fillRect(430, 90, 400, 540);

        g.setColor(Color.WHITE);
        g.drawRect(430, 90, 400, 540);

        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Trading", 550, 150);

        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("H. Healing Potion - 3 Emeralds", 470, 240);
        g.drawString("C. Strength Potion - 3 Emeralds", 470, 340);
        g.drawString("Press E to close", 520, 560);
    }
}
