package github.justarandomgit.gbe.utils;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class sounds {
    public static void playSound(
            World world,
            @Nullable PlayerEntity source,
            BlockPos blockPos,
            SoundEvent SoundEvent,
            SoundCategory SoundCategory
    ) {
        if (!world.isClient) {
            world.playSound(
                    source, // Player - if non-null, will play sound for every nearby player *except* the specified player
                    blockPos, // The position of where the sound will come from
                    SoundEvent, // The sound that will play, in this case, the sound the anvil plays when it lands.
                    SoundCategory, // This determines which of the volume sliders affect this sound
                    1f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                    1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
            );
        }
    }
}
