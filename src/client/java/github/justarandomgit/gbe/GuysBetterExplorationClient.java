package github.justarandomgit.gbe;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static github.justarandomgit.gbe.init.ComponentInit.EXPERIENCE_STORED;
import static github.justarandomgit.gbe.init.ComponentInit.MAX_EXPERIENCE;
import static github.justarandomgit.gbe.init.ItemInit.SCULK_VIAL;

public class GuysBetterExplorationClient implements ClientModInitializer {

	public static void registerModelPredicateProviders() {
		ModelPredicateProviderRegistry.register(SCULK_VIAL, Identifier.ofVanilla("experience_stored"), (ItemStack itemStack, ClientWorld clientWorld, LivingEntity livingEntity, int seed) -> {
			return (float)
					itemStack.getComponents().get(EXPERIENCE_STORED) / itemStack.getComponents().get(MAX_EXPERIENCE);
		});
	}

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		registerModelPredicateProviders();
	}
}