package raid.item;

import java.awt.image.BufferedImage;

public abstract class Potion extends Item implements Consumable {
    private final double POTION_EFFECT;
    private final int cost;

    public Potion(String name, BufferedImage sprite, double POTION_EFFECT, int cost) {
        super(name, sprite);
        this.POTION_EFFECT = POTION_EFFECT;
        this.cost = cost;
    }

    public double getPotionEffect() {return POTION_EFFECT;}
    
    public int getCost() {return cost;}
    
}
