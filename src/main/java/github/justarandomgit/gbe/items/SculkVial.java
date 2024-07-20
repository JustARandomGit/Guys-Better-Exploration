package github.justarandomgit.gbe.items;

import github.justarandomgit.gbe.GuysBetterExploration;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

import static github.justarandomgit.gbe.init.ComponentInit.EXPERIENCE_STORED;
import static github.justarandomgit.gbe.init.ComponentInit.MAX_EXPERIENCE;
import static github.justarandomgit.gbe.utils.sounds.playSound;

public class SculkVial extends Item {
    // Default maximum experience, 1395 is 30 levels exactly in vanilla Minecraft
    public static final int max_experience=1395;

    public SculkVial(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.getOrDefault(MAX_EXPERIENCE, SculkVial.max_experience) - stack.getOrDefault(EXPERIENCE_STORED, 0) <= 0;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        int exp_stored = stack.getOrDefault(EXPERIENCE_STORED, 0);
        int max_exp = stack.getOrDefault(MAX_EXPERIENCE, SculkVial.max_experience);

        tooltip.add(Text.translatable("item.gbe.sculk_vial.info", exp_stored, max_exp)
                .formatted(Formatting.BLUE));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // Gets the ItemStack
        ItemStack stack = user.getStackInHand(hand);

        // Don't do anything on the client
        if (world.isClient()) {
            return TypedActionResult.success(stack);
        }

        int experience_stored = stack.getOrDefault(EXPERIENCE_STORED, 0);
        int max_experience = stack.getOrDefault(MAX_EXPERIENCE, SculkVial.max_experience);

        log_experience("Before usage", stack, user);

        // Checks if the vial isn't full and if the user has experience
        if (experience_stored < max_experience && user.totalExperience > 0) {
            /*
             Checks if the user has more experience than needed to fill the vial, if so will only take the experience
             needed
             */
            int experience_taken = Math.min(user.totalExperience, max_experience - experience_stored);

            experience_stored += experience_taken;
            stack.set(EXPERIENCE_STORED, experience_stored);
            user.addExperience(-experience_taken);

            log_experience("After input", stack, user);

            return TypedActionResult.success(stack);

        } else if (experience_stored >= max_experience) {
            user.addExperience(experience_stored);
            stack.set(EXPERIENCE_STORED, 0);
            playSound(world, null, user.getBlockPos(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS);

            log_experience("After output", stack, user);

            // Removes the item after usage
            stack.setCount(0);
            return TypedActionResult.success(stack);
        }

        return TypedActionResult.pass(stack);
    }

    private void log_experience(String status, ItemStack stack, PlayerEntity user) {
        GuysBetterExploration.LOGGER.debug(
                "{}: exp stored: {} max exp: {} player exp: {}",
                status,
                stack.get(EXPERIENCE_STORED),
                stack.get(MAX_EXPERIENCE),
                user.totalExperience);
    }
}
