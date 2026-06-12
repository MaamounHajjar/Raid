package raid.item;

import java.awt.image.BufferedImage;

public abstract class Weapon extends Item{
    private double damage;
    
    public Weapon(String name, BufferedImage sprite,int damage) {
        super(name, sprite);
        this.damage = damage;
    }
    
    @Override
    public void setName(String name) {
        if (damage > 0) name = "asdas";
        
    }
    
    public double getDamage() {return damage;}
    
    public void buffDamage(double amount) {damage += amount;}
    
}
