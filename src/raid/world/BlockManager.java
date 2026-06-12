package raid.world;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import raid.exception.MapNotFoundException;
import raid.managers.AssetManager;

public final class BlockManager {
    private static final int MAX_ROWS = 30;
    private static final int MAX_COLS = 53;
    private static final int BLOCK_SIZE = 24;

    private final Block[] blocks;
    private final int[][] map;

    public BlockManager(AssetManager assets)  {
        blocks = new Block[10];
        map = new int[MAX_ROWS][MAX_COLS];

        blocks[0] = new Block(assets.getBlock("grass"), true);
        blocks[1] = new Block(assets.getBlock("cobblestone"), false);
        blocks[2] = new Block(assets.getBlock("water"), true);
        blocks[3] = new Block(assets.getBlock("plank"), false);
        blocks[4] = new Block(assets.getBlock("lava"), true);

        blocks[5] = new Block(assets.getBlock("door"), true);
        blocks[6] = new Block(assets.getBlock("leaf"), false);
        blocks[7] = new Block(assets.getBlock("oak"), false);

        loadMap("resources/maps/map02.txt");
    }

    public void loadMap(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));

            for (int row = 0; row < map.length; row++) {
                for (int col = 0; col < map[row].length; col++) {
                    map[row][col] = scanner.nextInt();
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new MapNotFoundException("Map n'existe pas: " + path);
        }
    }

    public void draw(Graphics g) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                int blockId = map[row][col];
                Block block = blocks[blockId];

                g.drawImage(block.getImage(), col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, null);
            }
        }
    }
    
    public boolean isWalkable(int x, int y, int width, int height) {
        int leftCol = x / BLOCK_SIZE;
        int rightCol = (x + width - 1) / BLOCK_SIZE;
        int topRow = y / BLOCK_SIZE;
        int bottomRow = (y + height - 1) / BLOCK_SIZE;

        if (!blocks[map[topRow][leftCol]].isWalkable()) return false;
        if (!blocks[map[topRow][rightCol]].isWalkable()) return false;
        if (!blocks[map[bottomRow][leftCol]].isWalkable()) return false;
        if (!blocks[map[bottomRow][rightCol]].isWalkable()) return false;

        return true;
    }
    
    public boolean isWater(int x, int y, int width, int height) {
        int leftCol = x / BLOCK_SIZE;
        int rightCol = (x + width - 1) / BLOCK_SIZE;
        int topRow = y / BLOCK_SIZE;
        int bottomRow = (y + height - 1) / BLOCK_SIZE;

        if (map[topRow][leftCol] == 2) return true;
        if (map[topRow][rightCol] == 2) return true;
        if (map[bottomRow][leftCol] == 2) return true;
        if (map[bottomRow][rightCol] == 2) return true;

        return false;
    }
    
    public boolean isLava(int x, int y, int width, int height) {
        int leftCol = x / BLOCK_SIZE;
        int rightCol = (x + width - 1) / BLOCK_SIZE;
        int topRow = y / BLOCK_SIZE;
        int bottomRow = (y + height - 1) / BLOCK_SIZE;

        if (map[topRow][leftCol] == 4) return true;
        if (map[topRow][rightCol] == 4) return true;
        if (map[bottomRow][leftCol] == 4) return true;
        if (map[bottomRow][rightCol] == 4) return true;

        return false;
    }
}
