package raid.entity;

import java.awt.image.BufferedImage;
import raid.world.BlockManager;

public class Ravager extends Enemy {
    private static final int WATER_SPEED = 1;
    private static final int DEATH_REWARD = 5;
    
    // customizable attack different than illager
    private static final int CHARGING_DISTANCE = 50, CHARGING_SPEED = 5, CHARGE_COOLDOWN = 120;
    private int currentSpeed;
    private int chargeCooldown = CHARGE_COOLDOWN;

    public Ravager(String name, BufferedImage sprite, int x, int y) {
        super(name, sprite, x, y, 20, 20, 30, 30, 2, 5, DEATH_REWARD);
    }

    @Override
    public void move(LivingEntity other, BlockManager blockManager) {
        int currentDistance = Math.max(Math.abs(other.getX() - getX()), Math.abs(other.getY() - getY()));
        
        if (blockManager.isWater(getX(), getY(), getWidth(), getHeight())) currentSpeed = WATER_SPEED;
        else currentSpeed = getSpeed();
        
        if (currentDistance < CHARGING_DISTANCE && chargeCooldown == CHARGE_COOLDOWN) {currentSpeed = CHARGING_SPEED;}
        if (intersects(other)) {
            currentSpeed = 0;
            resetSpeed();
        }
        if (other.getX() < getX() && blockManager.isWalkable(getX() - currentSpeed, getY(), getWidth(), getHeight())) setX(getX() - currentSpeed);
        if (other.getX() > getX() && blockManager.isWalkable(getX() + currentSpeed, getY(), getWidth(), getHeight())) setX(getX() + currentSpeed);
        if (other.getY() < getY() && blockManager.isWalkable(getX(), getY() - currentSpeed, getWidth(), getHeight())) setY(getY() - currentSpeed);
        if (other.getY() > getY() && blockManager.isWalkable(getX(), getY() + currentSpeed, getWidth(), getHeight())) setY(getY() + currentSpeed);
    }
    
    private void resetSpeed() {
        chargeCooldown--;
        if (chargeCooldown == 0) {
            currentSpeed = getSpeed();
            chargeCooldown = CHARGE_COOLDOWN;
        }
    }
}
