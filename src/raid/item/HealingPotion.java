package raid.item;

import raid.entity.Player;
import raid.managers.AssetManager;

public class HealingPotion extends Potion {
    public HealingPotion(AssetManager assets) {
        super("Healing", assets.getPotion("Healing"), 5, 3);
    }

    @Override
    public void consume(Player player) {
        player.heal(getPotionEffect());
    }

}
