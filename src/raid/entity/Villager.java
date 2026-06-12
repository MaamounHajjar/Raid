package raid.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import raid.managers.TradeManager;
import raid.world.BlockManager;

public class Villager extends LivingEntity {
    private static final int TRADING_RADIUS = 24;

    private final TradeManager tradeManager;
    private final Random random = new Random();

    public Villager(String name, BufferedImage icon, int x, int y) {
        super(name, icon, x, y, 20, 20, 10, 10, 1);
        this.tradeManager = new TradeManager();
    }

    public TradeManager getTradeManager() {
        return tradeManager;
    }
    
    public boolean isNearPlayer(Player player) {
        return Math.max(Math.abs(player.getX() - getX()), Math.abs(player.getY() - getY())) <= TRADING_RADIUS;
    }
    
    public void update(ArrayList<Enemy> enemies, Player player, BlockManager blockManager) {
        Enemy closest = getClosestEnemy(enemies);
        if (closest == null) return;

        int nextX = getX();
        int nextY = getY();

        if (closest.getX() < getX()) nextX += getSpeed();
        if (closest.getX() > getX()) nextX -= getSpeed();
        if (closest.getY() < getY()) nextY += getSpeed();
        if (closest.getY() > getY()) nextY -= getSpeed();

        if (blockManager.isWalkable(nextX, nextY, getWidth(), getHeight())) {
            setX(nextX);
            setY(nextY);
        } else if (blockManager.isWalkable(nextX, getY(), getWidth(), getHeight())) {
            setX(nextX);
        } else if (blockManager.isWalkable(getX(), nextY, getWidth(), getHeight())) {
            setY(nextY);
        } else {
            tryRandomMove(blockManager);
        }
    }

    private Enemy getClosestEnemy(ArrayList<Enemy> enemies) {
        Enemy closest = null;
        double minDistance = Double.MAX_VALUE;

        for (Enemy enemy : enemies) {
            if (!enemy.isDead()) {
                double dx = enemy.getX() - getX();
                double dy = enemy.getY() - getY();
                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < minDistance) {
                    minDistance = distance;
                    closest = enemy;
                }
            }
        }

        return closest;
    }

    private void tryRandomMove(BlockManager blockManager) {
        int direction = random.nextInt(4);

        if (direction == 0 && blockManager.isWalkable(getX() + getSpeed(), getY(), getWidth(), getHeight())) setX(getX() + getSpeed());
        if (direction == 1 && blockManager.isWalkable(getX() - getSpeed(), getY(), getWidth(), getHeight())) setX(getX() - getSpeed());
        if (direction == 2 && blockManager.isWalkable(getX(), getY() + getSpeed(), getWidth(), getHeight())) setY(getY() + getSpeed());
        if (direction == 3 && blockManager.isWalkable(getX(), getY() - getSpeed(), getWidth(), getHeight())) setY(getY() - getSpeed());
    }
}
