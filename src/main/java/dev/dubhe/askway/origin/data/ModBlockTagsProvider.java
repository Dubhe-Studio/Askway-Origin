package dev.dubhe.askway.origin.data;

import dev.dubhe.askway.origin.AskwayOrigin;
import dev.dubhe.askway.origin.init.AskwayModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AskwayOrigin.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        tag(BlockTags.LOGS_THAT_BURN)
                .addTag(AskwayModTags.Blocks.WILLOW_LOGS)
                .addTag(AskwayModTags.Blocks.PEACH_LOGS);
        tag(BlockTags.LOGS).addTag(AskwayModTags.Blocks.LIGHTNING_PEACH_LOGS);

    }
}
