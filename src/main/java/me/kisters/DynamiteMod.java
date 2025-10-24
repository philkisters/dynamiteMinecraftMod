package me.kisters;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.kisters.entity.ModEntities;
import me.kisters.item.ModItems;

public class DynamiteMod implements ModInitializer {
    public static final String MOD_ID = "dynamitemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Loading DynamiteMod...");
        ModItems.initialize();
        ModEntities.registerModEntities();
    }
}