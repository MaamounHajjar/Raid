package raid.item;

import raid.managers.AssetManager;

public class Bow extends Weapon {
    public Bow(AssetManager assets) {
        // change sword damage the same way
        // change bow damage ----------------->
        super("Bow", assets.getWeapon("Bow"), 4);
    }
}
