package raid.entity;

import java.awt.image.BufferedImage;
import raid.input.KeyHandler;
import raid.item.Bow;
import raid.item.HealingPotion;
import raid.item.Potion;
import raid.item.StrengthPotion;
import raid.item.Sword;
import raid.item.Weapon;
import raid.managers.AssetManager;
import raid.world.BlockManager;

public class Player extends LivingEntity implements DamageDealer {
    //modify how many emeralds player starts with for buying potions
    private static final int STARTING_EMERALDS = 10;
    // controls speed on water
    private static final int WATER_SPEED = 2;

    
    private double strengthEffect = 1.0;
    private double strengthEffectTimer;

    private int currentSpeed = getSpeed();
    private int emeralds = STARTING_EMERALDS;
    
    private final Sword sword;
    private final Bow bow;
    private final HealingPotion healingPotion;
    private final StrengthPotion strengthPotion;

    private String direction = "left";
    
    private int healingPotionsCount;
    private int strengthPotionsCount;
    
    private Weapon equippedWeapon;
    private Potion selectedPotion;

    public Player(String name, BufferedImage skin, int x, int y,AssetManager assets) { 
        super(name, skin, x, y, 20, 20, 20, 20, 4); //String name, int x, int y, int width, int height, int hp, int maxHp, int speed
        
        this.sword = new Sword(assets);
        this.bow = new Bow(assets);
        this.equippedWeapon = sword;
        
        this.healingPotion = new HealingPotion(assets);
        this.strengthPotion = new StrengthPotion(assets);
        this.selectedPotion = healingPotion;
    }
    
    public int getEmeralds() {
        return emeralds;
    }

    public void addEmeralds(int amount) {
        emeralds += amount;
    }

    public void spendEmeralds(int amount) {
        emeralds -= amount;
    }
    
    public boolean hasCollidedWithEnemy(Enemy enemy) {
        return intersects(enemy);
    }
    
    @Override
    public double getDamage() {
        return equippedWeapon.getDamage() * strengthEffect;
    }
    
    public void increaseDamageBy(double amount) {
        sword.buffDamage(amount);
        bow.buffDamage(amount);
    }

    
    public boolean equippedSword() {
        return equippedWeapon.getName() == "Sword";

    }
    public boolean equippedBow() {
        return equippedWeapon.getName() == "Bow";
    }
    
    private void setEquippedWeapon(KeyHandler keyH) {
        if (keyH.isSwordSelected()) equippedWeapon = sword;
        if (keyH.isBowSelected()) equippedWeapon = bow;
    }
    
    private void setSelectedPotion(KeyHandler keyH) {
        if (keyH.isHealingPotionSelected()) selectedPotion = healingPotion;
        if (keyH.isStrengthPotionSelected()) selectedPotion = strengthPotion;
    }
    
    public Weapon getEquippedWeapon() {
       return equippedWeapon;
    }
    
    public Potion getSelectedPotion() {
        return selectedPotion;
    }
    
    //////////////////// HEALING POTIONS

    public int getHealingPotionsCount() { // for buying
        return healingPotionsCount;
    }
    public Potion getHealingPotion() {
        return healingPotion;
    }
    
    ///////////////// STRENGTH POTIONS
    public int getStrengthPotionsCount() {
        return strengthPotionsCount;
    }
    public Potion getStrengthPotion() { // for buying
        return strengthPotion;
    }
     
    public void addPotion(Potion potion) {
        if (potion.getName() == "Healing") healingPotionsCount++;
        if (potion.getName() == "Strength") strengthPotionsCount++;
    }
    
     private void setDirection(KeyHandler keyH) {
        if (keyH.isLeftPressed()) direction = "left";
        if (keyH.isRightPressed()) direction = "right";
        if (keyH.isUpPressed()) direction = "up";
        if (keyH.isDownPressed()) direction = "down";
    }
    
    public String getDirection() {
        return direction;
    }
    
    private void move(KeyHandler keyH, BlockManager blockManager) {
        if (blockManager.isLava(getX(), getY(), getWidth(), getHeight())) {
            setHp(getHp() - 1);
        }
        if (blockManager.isWater(getX(), getY(), getWidth(), getHeight())) {
            currentSpeed = WATER_SPEED;
        }
        else {
            currentSpeed = getSpeed();
        }
        
        if (keyH.isUpPressed() && getY() > 0 && blockManager.isWalkable(getX(), getY() - currentSpeed, getWidth(), getHeight())) setY(getY() - currentSpeed);
        if (keyH.isDownPressed() && getY() < 720 - getHeight() && blockManager.isWalkable(getX(), getY() + currentSpeed, getWidth(), getHeight())) setY(getY() + currentSpeed);
        if (keyH.isLeftPressed() && getX() > 0 && blockManager.isWalkable(getX() - currentSpeed, getY(), getWidth(), getHeight())) setX(getX() - currentSpeed);
        if (keyH.isRightPressed() && getX() < 1280  && blockManager.isWalkable(getX() + currentSpeed, getY(), getWidth(), getHeight())) setX(getX() + currentSpeed);
    }

    
    public void update(KeyHandler keyH, BlockManager blockManager) {
        setDirection(keyH);
        move(keyH, blockManager);
        setEquippedWeapon(keyH);
        usePotion(keyH);
        updateStrengthEffect();

    }
    
    private void usePotion(KeyHandler keyH) {
        setSelectedPotion(keyH);

        if (keyH.isDrinkingPotionPressed()) {
            consumeSelectedPotion();
            keyH.resetDrinkingPotionPressed();
        }
    }

    private void updateStrengthEffect() {
        if (strengthEffectTimer > 0) {
            strengthEffectTimer--;
        } else {
            strengthEffect = 1.0;
        }
    }


    private void consumeSelectedPotion() {
        if (selectedPotion == healingPotion && healingPotionsCount > 0) {
            healingPotionsCount--;
            selectedPotion.consume(this);
        }

        if (selectedPotion == strengthPotion && strengthPotionsCount > 0) {
            strengthPotionsCount--;
            selectedPotion.consume(this);
            
        }
    }
    
    public void heal() {
        setHp(getHp() + 3);
    }
    
    public void heal(double amount) {
        setHp(getHp() + amount);
        if (getHp() > getMaxHp()) setHp(getMaxHp());
    }

    public void applyStrengthEffect(double multiplier, int duration) {
        strengthEffect = multiplier;
        strengthEffectTimer = duration;
    }

}



