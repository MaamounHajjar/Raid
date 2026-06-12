package raid.item;

import raid.entity.Player;
import raid.managers.AssetManager;

public class StrengthPotion extends Potion implements Consumable {
    private static final int MAX_DURATION = 300;
    private final int duration;

    public StrengthPotion(AssetManager assets) {
        super("Strength", assets.getPotion("Strength"), 1.5, 5);
        this.duration = MAX_DURATION;
    }

    public int getDuration() {return duration;}

    @Override
    public void consume(Player player) {
        if (duration == MAX_DURATION) {player.applyStrengthEffect(getPotionEffect(), duration);}
    }
}
