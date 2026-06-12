package raid.game;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

    public GameWindow() {
        GamePanel panel = new GamePanel();

        add(panel);
        pack();

        setTitle("Minecraft Raid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        panel.startGameThread();
    }
}
