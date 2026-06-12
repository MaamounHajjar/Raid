package raid.item;

import raid.managers.AssetManager;

public class Sword extends Weapon {
    public Sword(AssetManager assets) {
        super("Sword", assets.getWeapon("Sword"), 7);
    }

}
