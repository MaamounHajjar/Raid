package raid.managers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import raid.exception.AssetNotFoundException;



public class AssetManager {
    private final BufferedImage steve;
    private final BufferedImage alex;

    private final Map<String, BufferedImage> blocks;
    
    private final BufferedImage illager;
    private final BufferedImage ravager;
    
    private final BufferedImage heart;
    private final BufferedImage emerald;
    
    private final BufferedImage sword;
    private final BufferedImage bow;
    private final BufferedImage arrow;
    
    private final BufferedImage villager;
    
    private final BufferedImage healingPotion;
    private final BufferedImage strengthPotion;
    private final BufferedImage menuImage;
    
    public AssetManager() {
        blocks = new HashMap<>();
        steve = loadImage("resources/images/entities/player/stevehead.jpg");
        alex = loadImage("resources/images/entities/player/alexhead.jpg");

        blocks.put("grass", loadImage("resources/images/blocks/grass.jpg"));
        blocks.put("water", loadImage("resources/images/blocks/water.jpg"));
        blocks.put("cobblestone", loadImage("resources/images/blocks/cobblestone.png"));
        blocks.put("plank", loadImage("resources/images/blocks/plank.png"));
        blocks.put("lava", loadImage("resources/images/blocks/lava.jpg"));
        blocks.put("door", loadImage("resources/images/blocks/door.png"));
        blocks.put("leaf", loadImage("resources/images/blocks/leaf.png"));
        blocks.put("oak", loadImage("resources/images/blocks/oak.jpg"));

        illager = loadImage("resources/images/entities/enemies/illager.png");
        ravager = loadImage("resources/images/entities/enemies/ravager.png");

        heart = loadImage("resources/images/ui/heart.png");
        emerald = loadImage("resources/images/ui/emerald.png");

        sword = loadImage("resources/images/items/weapons/sword.png");
        bow = loadImage("resources/images/items/weapons/bow.png");
        arrow = loadImage("resources/images/items/weapons/arrow.png");

        villager = loadImage("resources/images/entities/villagers/villager.jpg");

        healingPotion = loadImage("resources/images/items/potions/healing.png");
        strengthPotion = loadImage("resources/images/items/potions/strength.png");
        
        menuImage = loadImage("resources/images/ui/menu.jpg");
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new AssetNotFoundException("Le resource n'existe pas: " + path, e);
        }
    }

    
    public BufferedImage getSkin(String skin) {
        if (skin == "Steve") return steve;
        else return alex;
    }
    
    public BufferedImage getBlock(String blockName) {
        return blocks.get(blockName);
    }
    
    public BufferedImage getEnemy(String enemyName) {
        if (enemyName == "Illager") return illager;
        else return ravager;
    }
    
    public BufferedImage getHeart() {
        return heart;
    }

    public BufferedImage getEmerald() {
        return emerald;
    }
    
    public BufferedImage getWeapon(String name) {
        if (name == "Sword") {
            return sword;
        }
        return bow;
    }
    
    public BufferedImage getArrow() {
        return arrow;
    }
    
    public BufferedImage getVillager() {
        return villager;
    }
    
    public BufferedImage getHealingPotion() {
        return healingPotion;
    }
    
    public BufferedImage getPotion(String name) {
        if (name == "Healing") {return healingPotion;}
        if (name == "Strength") {return strengthPotion;}
        if (name == "Speed") {return healingPotion;}
        return null;
    }
    
    public BufferedImage getMenuImage (){
        return menuImage;
    }
}
