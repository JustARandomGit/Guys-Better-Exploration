package github.justarandomgit.gbe.init;

import com.mojang.serialization.Codec;
import github.justarandomgit.gbe.GuysBetterExploration;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ComponentInit {
    public static final ComponentType<Integer> EXPERIENCE_STORED =
            ComponentInit.register(
                    "experience_stored",
                    ComponentType.<Integer>builder().codec(Codec.INT).build()
            );

    public static final ComponentType<Integer> MAX_EXPERIENCE =
            ComponentInit.register(
                    "max_experience",
                    ComponentType.<Integer>builder().codec(Codec.INT).build()
            );


    public static <T extends ComponentType<?>> T register(String name, T component) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, GuysBetterExploration.id(name), component);
    }

    public static void init() { /* Used to register all the components when the mod's onInitialize it ran */ }
}
