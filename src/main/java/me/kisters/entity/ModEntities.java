package me.kisters.entity;

import me.kisters.DynamiteMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static EntityType<DynamiteEntity> DYNAMITE_ENTITY_TYPE;

    public static void registerModEntities() {
       
        DYNAMITE_ENTITY_TYPE = registerEntity("dynamite_entity",
                EntityType.Builder.<DynamiteEntity>create(DynamiteEntity::new, SpawnGroup.MISC)
                        .dimensions(0.25f, 0.25f)
                        .maxTrackingRange(64)
                        .trackingTickInterval(10)
        );
    }
    
    private static <T extends net.minecraft.entity.Entity> EntityType<T> registerEntity(String name, EntityType.Builder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, 
                Identifier.of(DynamiteMod.MOD_ID, name), 
                builder.build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(DynamiteMod.MOD_ID, name))));
    }
}