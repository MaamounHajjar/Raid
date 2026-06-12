package raid.entity;

import java.awt.image.BufferedImage;
import raid.world.BlockManager;

public class Arrow extends Entity {
    private final int speed;
    private final double damage;
    private final String direction;
    private boolean alive;

    public Arrow(String name, BufferedImage icon, int x, int y, int width, int height, int speed, double damage, Player player) {
            super(name, icon, x, y, width, height);
            this.speed = speed;
            this.damage = damage;
            this.alive = true;
            this.direction = player.getDirection();
}

    
    public double getDamage() {
        return damage;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public boolean isAlive() {
        return alive;
    }
    
    public void deleteArrow() {
        alive = false;
    }
    
    public boolean hitEnemy(Enemy enemy) {
        return this.intersects(enemy);
    }
    
    public void update(BlockManager blockManager) {
        int nextX = 0;
        int nextY = 0;
        
        if ("left".equals(direction)) {
            nextX = getX() - speed;
            nextY = getY();
        }
        if ("right".equals(direction)) {
            nextX = getX() + speed;
            nextY = getY();
        }
        if ("up".equals(direction)) {
            nextX = getX();
            nextY = getY() - speed;
        }
        if ("down".equals(direction)) {
            nextX = getX();
            nextY = getY() + speed;
        }
        
        if (blockManager.isWalkable(nextX, nextY, getWidth(), getHeight())) {
            setX(nextX);
            setY(nextY);
        } else {
            deleteArrow();
        }
}

    
}
