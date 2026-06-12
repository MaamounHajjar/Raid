package raid.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Entity {
    private final String name;
    private final BufferedImage icon;
    private int x;
    private int y;
    private final int width;
    private final int height;

    public Entity(String name, BufferedImage icon, int x, int y, int width, int height) { 
        this.name = name;
        this.icon = icon;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {return x;}

    public int getY() {return y;}

    public int getWidth() {return width;}

    public int getHeight() {return height;}
    
    public String getName() {return name;}

    public BufferedImage getIcon() {return icon;}

    public void setX(int x) {this.x = x;}

    public void setY(int y) {this.y = y;}
    
    public boolean intersects(Entity other) {
        boolean xOverlap = x < other.getX() + other.getWidth() && x + width > other.getX();
        boolean yOverlap = y < other.getY() + other.getHeight() && y + height > other.getY();
        return xOverlap && yOverlap;
    }

    public void draw(Graphics g) {
        g.drawImage(icon, x, y, width, height, null);
    }

}
    
