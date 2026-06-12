package raid.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private boolean enterPressed = false;
    private boolean upPressed, downPressed, leftPressed, rightPressed, 
            meleeAttackPressed, drinkingPotionPressed,
            swordSelected, bowSelected,
            skipStartScreenPressed = false, pausePressed = false, tradePressed = false,
            buyHealingPotionPressed, healingPotionSelected,
            buyStrengthPotionPressed, strengthPotionSelected,
            buySwiftnessPotionPressed, swiftnessPotionSelected;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) upPressed = true;
        if (key == KeyEvent.VK_S) downPressed = true;
        if (key == KeyEvent.VK_A) leftPressed = true;
        if (key == KeyEvent.VK_D) rightPressed = true;
        if (key == KeyEvent.VK_F) meleeAttackPressed = true;
        if (key == KeyEvent.VK_1) {
            swordSelected = true;
            bowSelected = false;
        }
        if (key == KeyEvent.VK_2) {
            swordSelected = false;
            bowSelected = true;
        }
        
        if (key == KeyEvent.VK_ENTER) skipStartScreenPressed = true;
        
        if (key == KeyEvent.VK_ESCAPE)  pausePressed = !pausePressed;
        if (key == KeyEvent.VK_E) tradePressed = !tradePressed;
        
        if (key == KeyEvent.VK_H) buyHealingPotionPressed = true;
        if (key == KeyEvent.VK_C) buyStrengthPotionPressed = true;
        
        if (key == KeyEvent.VK_Y) drinkingPotionPressed = true;
        
        if (key == KeyEvent.VK_3) {
            healingPotionSelected = true;
            strengthPotionSelected = false;
            swiftnessPotionSelected = false;
        }
        if (key == KeyEvent.VK_4) {
            healingPotionSelected = false;
            strengthPotionSelected = true;
            swiftnessPotionSelected = false;
        }
        if (key == KeyEvent.VK_5) {
            healingPotionSelected = false;
            strengthPotionSelected = false;
            swiftnessPotionSelected = true;
        }
    }
        
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) upPressed = false;
        if (key == KeyEvent.VK_S) downPressed = false;
        if (key == KeyEvent.VK_A) leftPressed = false;
        if (key == KeyEvent.VK_D) rightPressed = false;
        if (key == KeyEvent.VK_F) meleeAttackPressed = false;
    }

    @Override public void keyTyped(KeyEvent e) {
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isMeleeAttackPressed() {
        return meleeAttackPressed;
    }

    public boolean isDrinkingPotionPressed() {
        return drinkingPotionPressed;
    }

    public boolean isSwordSelected() {
        return swordSelected;
    }

    public boolean isBowSelected() {
        return bowSelected;
    }

    public boolean isSkipStartScreenPressed() {
        return skipStartScreenPressed;
    }

    public boolean isPausePressed() {
        return pausePressed;
    }

    public boolean isTradePressed() {
        return tradePressed;
    }

    public boolean isBuyHealingPotionPressed() {
        return buyHealingPotionPressed;
    }

    public boolean isHealingPotionSelected() {
        return healingPotionSelected;
    }

    public boolean isBuyStrengthPotionPressed() {
        return buyStrengthPotionPressed;
    }

    public boolean isStrengthPotionSelected() {
        return strengthPotionSelected;
    }

    public boolean isBuySwiftnessPotionPressed() {
        return buySwiftnessPotionPressed;
    }

    public boolean isSwiftnessPotionSelected() {
        return swiftnessPotionSelected;
    }

    public void resetDrinkingPotionPressed() {
        drinkingPotionPressed = false;
    }

    public void resetBuyHealingPotionPressed() {
        buyHealingPotionPressed = false;
    }

    public void resetBuyStrengthPotionPressed() {
        buyStrengthPotionPressed = false;
    }
}
