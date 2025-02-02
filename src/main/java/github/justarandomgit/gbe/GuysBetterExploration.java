package github.justarandomgit.gbe;

import github.justarandomgit.gbe.init.ComponentInit;
import github.justarandomgit.gbe.init.ItemInit;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuysBetterExploration implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("gbe");
	public static final String MOD_ID = "gbe";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Loading fabric mod...");
		ComponentInit.init();
		ItemInit.init();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}