package raid.entity;

import java.awt.image.BufferedImage;
import raid.world.BlockManager;

public abstract class Enemy extends LivingEntity implements DamageDealer {
    private static final int SEPERATE_DISTANCE = 2;
    private static int totalSpawned = 0;
    private final int deathReward;
    private final double damage;

    public Enemy(String name, BufferedImage sprite, int x, int y, 
                int width, int height, int hp, int maxHp, int speed, double damage, int deathReward) {
        super(name, sprite, x, y, width, height, hp, maxHp, speed );
        this.damage = damage;
        this.deathReward = deathReward;
        totalSpawned++;
    }
    
    @Override
    public double getDamage() {return damage;}

    public boolean hasCollidedWithHuman(LivingEntity other) {return intersects(other);}
    
    protected abstract void move(LivingEntity other, BlockManager blockManager);

    public void rewardPlayer(Player player) {player.addEmeralds(getReward());}
    
    public int getReward() {return deathReward;}
    
    public static int getTotalSpawned() {return totalSpawned;}
    
    public void separateFrom(Enemy other, BlockManager blockManager) {
        if ( this.intersects(other)) {
            if (getX() < other.getX() && blockManager.isWalkable(getX() - SEPERATE_DISTANCE, getY(), 24, 24)) setX(getX() - SEPERATE_DISTANCE);
            else if (getX() > other.getX() && blockManager.isWalkable(getX() + SEPERATE_DISTANCE, getY(), 24, 24)) setX(getX() + SEPERATE_DISTANCE);

            if (getY() < other.getY() && blockManager.isWalkable(getX(), getY() + SEPERATE_DISTANCE, 24, 24)) setY(getY() - SEPERATE_DISTANCE);
            else if (getY() > other.getY() && blockManager.isWalkable(getX(), getY() + SEPERATE_DISTANCE, 24, 24)) setY(getY() + SEPERATE_DISTANCE);
        }
    }

    public void update(LivingEntity other, BlockManager blockManager) {
        move(other, blockManager);
    }
}
