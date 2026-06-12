package raid.item;

import java.awt.image.BufferedImage;

public abstract class Item {
    private String name;
    private BufferedImage sprite;
    
    public Item(String name, BufferedImage sprite) {
        this.name = name;
        this.sprite = sprite;
    }
    
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public BufferedImage getSprite() {return sprite;}

    public void setSprite(BufferedImage sprite) {this.sprite = sprite;}
}
