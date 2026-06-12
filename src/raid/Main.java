package raid;

import javax.swing.JOptionPane;
import raid.exception.AssetNotFoundException;
import raid.exception.MapNotFoundException;
import raid.game.GameWindow;

public class Main {
    public static void main(String[] args) {
        try {
            new GameWindow();
        } catch (AssetNotFoundException | MapNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Game Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
