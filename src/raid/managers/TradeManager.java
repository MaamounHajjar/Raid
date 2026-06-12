package raid.managers;

import raid.entity.Player;
import raid.entity.Villager;
import raid.input.KeyHandler;
import raid.item.Potion;

public class TradeManager {
    public void buyPotion(Player player, Potion potion) {
        if (player.getEmeralds() >= potion.getCost()) {
            player.spendEmeralds(potion.getCost());
            player.addPotion(potion);
        }
    }
    
    public void trade(Player player, Villager villager, KeyHandler keyH) {
        if (villager.isNearPlayer(player)) {
            // buying the appropriate potion...
            if (keyH.isBuyHealingPotionPressed()) {
                villager.getTradeManager().buyPotion(player, player.getHealingPotion());
                keyH.resetBuyHealingPotionPressed();
            }

            if (keyH.isBuyStrengthPotionPressed()) {
                villager.getTradeManager().buyPotion(player, player.getStrengthPotion());
                keyH.resetBuyStrengthPotionPressed();
            }
        }
    }
}
