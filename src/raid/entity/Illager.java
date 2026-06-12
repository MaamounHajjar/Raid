package raid.entity;

import java.awt.image.BufferedImage;
import raid.world.BlockManager;

public class Illager extends Enemy {
    private static final int WATER_SPEED = 1;
    private static final int DEATH_REWARD = 3;
    private int currentSpeed;
    
    public Illager(String name, BufferedImage sprite, int x, int y) {
        super(name, sprite, x, y, 20, 20, 15, 15, 2, 2, DEATH_REWARD); //nt width, int height, int hp, int maxHp, int speed, int damage
    }
   

    @Override
    public void move(LivingEntity other, BlockManager blockManager) {
        if (Math.abs(other.getX() - getX()) < 10 || Math.abs(other.getY() - getY()) < 10) currentSpeed = 1;
        else currentSpeed = getSpeed();
        
        if (blockManager.isWater(getX(), getY(), getWidth(), getHeight())) currentSpeed = WATER_SPEED;
        else currentSpeed = getSpeed();
        
        if (other.getX() < getX() && blockManager.isWalkable(getX() - currentSpeed, getY(), getWidth(), getHeight())) setX(getX() - currentSpeed);
        if (other.getX() > getX() && blockManager.isWalkable(getX() + currentSpeed, getY(), getWidth(), getHeight())) setX(getX() + currentSpeed);
        if (other.getY() < getY() && blockManager.isWalkable(getX(), getY() - currentSpeed, getWidth(), getHeight())) setY(getY() - currentSpeed);
        if (other.getY() > getY() && blockManager.isWalkable(getX(), getY() + currentSpeed, getWidth(), getHeight())) setY(getY() + currentSpeed);
    }
}
