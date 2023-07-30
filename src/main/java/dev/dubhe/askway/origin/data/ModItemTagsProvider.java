package dev.dubhe.askway.origin.data;

import dev.dubhe.askway.origin.AskwayOrigin;
import dev.dubhe.askway.origin.init.AskwayModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                               CompletableFuture<TagLookup<Block>> blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, blockTagsProvider, AskwayOrigin.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(AskwayModTags.Items.WILLOW_LOGS)
                .addTag(AskwayModTags.Items.PEACH_LOGS);
        tag(ItemTags.LOGS).addTag(AskwayModTags.Items.LIGHTNING_PEACH_LOGS);

    }
}
