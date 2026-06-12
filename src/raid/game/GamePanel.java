package raid.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import raid.entity.Player;
import raid.input.KeyHandler;

import raid.managers.AssetManager;
import raid.world.BlockManager;
import raid.entity.Villager;

import raid.managers.CombatManager;
import raid.managers.WaveManager;
import raid.managers.ArrowManager;
import raid.managers.TradeManager;
import raid.managers.EnemyManager;

import raid.ui.DeathScreen;
import raid.ui.Hud;
import raid.ui.PausedScreen;
import raid.ui.StartScreen;
import raid.ui.TradeScreen;
import raid.ui.VictoryScreen;

public class GamePanel extends JPanel implements Runnable {

    Thread gameThread;
    private final AssetManager assets;
    private final Player player;
    private final KeyHandler keyH;
    
    private final BlockManager blockManager;

    private final Hud hud;
    private final CombatManager combatManager;
    private final ArrowManager arrowManager;
    private final EnemyManager enemyManager;
    
    private GameState gameState = GameState.START;
    private final DeathScreen deathScreen;
    private final PausedScreen pausedScreen;
    private final StartScreen startScreen;
    private final TradeScreen tradeScreen;
    private final VictoryScreen victoryScreen;
    
    private final ArrayList<Villager> villagers = new ArrayList<>();
    
    private final TradeManager tradeManager;
    private final WaveManager waveManager;

    public GamePanel() {
        setPreferredSize(new Dimension(1272, 720));
        setBackground(Color.BLACK);
        
        assets = new AssetManager();
        player = new Player("Maamoun", assets.getSkin("Alex"), 100, 300, assets);
        
        keyH = new KeyHandler();
        addKeyListener(keyH);
        setFocusable(true);
        
        blockManager = new BlockManager(assets);
 
        //enemies.add(new Illager("Illager", assets.getEnemy("Illager"), 1000, 200));
        //enemies.add(new Ravager("Ravager", assets.getEnemy("Ravager"), 1000, 180));
        
        hud = new Hud(assets);
        combatManager = new CombatManager();
        arrowManager = new ArrowManager();
        enemyManager = new EnemyManager();
        tradeManager = new TradeManager();
        
        deathScreen = new DeathScreen();
        pausedScreen = new PausedScreen();
        startScreen = new StartScreen(assets);
        tradeScreen = new TradeScreen();
        victoryScreen = new VictoryScreen();
        
        
        villagers.add(new Villager("Villager", assets.getVillager(), 150, 100));
        villagers.add(new Villager("Villager", assets.getVillager(), 350, 400));
        villagers.add(new Villager("Villager", assets.getVillager(), 150, 500));
        villagers.add(new Villager("Villager", assets.getVillager(), 150, 140));
        villagers.add(new Villager("Villager", assets.getVillager(), 500, 120));
        villagers.add(new Villager("Villager", assets.getVillager(), 600, 650));
        
        
        waveManager = new WaveManager();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            handleGameStates();

            if (gameState == GameState.PLAYING) {
                gamePlay();
            }

            if (gameState == GameState.TRADING && getNearbyVillager() != null) {
                tradeManager.trade(player, getNearbyVillager(), keyH);
            }
            
            repaint();
            try {
                Thread.sleep(16);
            }
            catch (InterruptedException e) {
            }       
        }
    }
    
    private void gamePlay() {
        if (!player.isDead()) {
            player.update(keyH, blockManager);
            player.updateAttackCooldown();
        }
        
        enemyManager.update(player, villagers, blockManager, combatManager, keyH);

        for (Villager villager : villagers) {
            if (!villager.isDead()) {
                villager.update(enemyManager.getEnemies(), player, blockManager);
            }
        }

        arrowManager.update(blockManager, enemyManager.getEnemies(), combatManager, player, assets, keyH);

        villagers.removeIf(villager -> villager.isDead());
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGameState(g);
    }    

    private void drawGameState(Graphics g) {
        
        if (gameState == GameState.PLAYING || gameState == GameState.START || gameState == GameState.PAUSED || gameState == GameState.TRADING || gameState == GameState.GAME_OVER || gameState == GameState.VICTORY) {
            
            blockManager.draw(g);
            player.draw(g);
            
            enemyManager.draw(g);
            
            arrowManager.draw(g);

            for (Villager villager : villagers) {
                villager.draw(g);
            }
            
            hud.draw(g, player, combatManager);
        }
        
        if (gameState == GameState.START) startScreen.draw(g);
        
        if (gameState == GameState.GAME_OVER) deathScreen.draw(g);
        
        if (gameState == GameState.PAUSED) pausedScreen.draw(g);
        
        if (gameState == GameState.TRADING) tradeScreen.draw(g);
        
        if (gameState == GameState.VICTORY) victoryScreen.draw(g);
    }
    
    private Villager getNearbyVillager() {
        for (Villager villager : villagers) {
            if (villager.isNearPlayer(player)) {
                return villager;
            }
        }
        return null;
    }

    private void handleGameStates() {
        if (keyH.isSkipStartScreenPressed()) {
            gameState = GameState.PLAYING;
        }

        if (keyH.isPausePressed()) {
            gameState = GameState.PAUSED;
        }

        if (gameState == GameState.PLAYING && keyH.isTradePressed() && getNearbyVillager() != null) {
            gameState = GameState.TRADING;
        }

        if (player.isDead() || villagers.isEmpty()) {
            gameState = GameState.GAME_OVER;
            gameThread = null;
        }

        if (enemyManager.getEnemies().isEmpty()) {
            waveManager.startNextWave(enemyManager.getEnemies(), assets, player);
        }

        if (waveManager.wavesFinished() && enemyManager.getEnemies().isEmpty()) {
            gameState = GameState.VICTORY;
            gameThread = null;
        }
    }

}


