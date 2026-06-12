package raid.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class LivingEntity extends Entity {
    private static final int ATTACK_COOLDOWN = 60;

    private double hp;
    private double maxHp;
    private int speed;
    private int attackCooldown = 0;
   
    
    public LivingEntity(String name, BufferedImage sprite, int x, int y, int width, int height, double hp, double maxHp, int speed) {
        super(name, sprite, x, y, width, height);
        this.hp = hp;
        this.maxHp = maxHp;
        this.speed = speed;
        
    }

    public double getHp() {return hp;}

    public double getMaxHp() {return maxHp;}

    public int getSpeed() {return speed;}
    
    public void takeDamage(double amount) {
        hp -= amount;
        if (hp < 0) hp = 0;
    }
    
    public int getAttackCooldown() {return attackCooldown;}

    public void setHp(double amount) {hp = amount;}

    public void setMaxHp(double amount) {maxHp = amount;}

    public void setSpeed(int amount) {speed = amount;}
    
    public boolean isDead() {return hp <= 0;}

    public void updateAttackCooldown() {
        if (attackCooldown > 0) attackCooldown--;
    }

    public void resetAttackCooldown() {attackCooldown = ATTACK_COOLDOWN;}

    public void drawHealthBar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getX(), getY() - 8, getWidth(), 5);

        g.setColor(Color.GREEN);
        g.fillRect(getX(), getY() - 8, (int)((double) getHp() / getMaxHp() * getWidth()), 5);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        drawHealthBar(g);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString(String.valueOf(hp), getX(), getY() - 5);
        
    }
    
    


}
