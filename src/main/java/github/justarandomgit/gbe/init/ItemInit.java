package github.justarandomgit.gbe.init;

import github.justarandomgit.gbe.GuysBetterExploration;
import github.justarandomgit.gbe.items.SculkVial;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ItemInit {
    public static final Item SCULK_VIAL = register("sculk_vial", new SculkVial(
            new Item.Settings()
                    .component(ComponentInit.EXPERIENCE_STORED, 0)
                    .component(ComponentInit.MAX_EXPERIENCE, 1395)
                    .maxCount(1)
    ));


    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, GuysBetterExploration.id(name), item);
    }

    public static void init() { /* Used to register all the items when the mod's onInitialize it ran */ }
}
