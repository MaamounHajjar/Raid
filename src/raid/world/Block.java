package raid.world;

import java.awt.image.BufferedImage;

public class Block {
    private final BufferedImage image;
    private final boolean walkable;
    
    public Block(BufferedImage image, boolean walkable) {
        this.image = image;
        this.walkable = walkable;
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean isWalkable() {
        return walkable;
    }
}
