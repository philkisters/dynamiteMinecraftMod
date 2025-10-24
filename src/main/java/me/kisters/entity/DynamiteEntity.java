package me.kisters.entity;

import me.kisters.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class DynamiteEntity extends ThrownItemEntity {
    public DynamiteEntity(EntityType<? extends DynamiteEntity> entityType, World world) {
        super(entityType, world);
    }
    
    public DynamiteEntity(World world, LivingEntity owner) {
        super(ModEntities.DYNAMITE_ENTITY_TYPE, owner, world, new ItemStack(ModItems.DYNAMITE));
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.DYNAMITE != null ? ModItems.DYNAMITE : net.minecraft.item.Items.TNT;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getEntityWorld().isClient()) {
            this.getEntityWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(),
                    2.5f, World.ExplosionSourceType.NONE);
            
            if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
                spawnColorfulParticles(serverWorld);
            }
            
            this.getEntityWorld().playSound(null, this.getX(), this.getY(), this.getZ(), 
                    SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            
            this.discard();
        }
    }
    
    private void spawnColorfulParticles(ServerWorld world) {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        
        // Predefined beautiful colors
        int[] colors = {
            DustParticleEffect.RED,     // 16711680
            65280,                      // 0x00FF00
            255,                        // 0x0000FF  
            16776960,                   // 0xFFFF00
            16711935,                   // 0xFF00FF
            65535,                      // 0x00FFFF
            16753920,                   // 0xFF8000
            8388736,                    // 0x800080
            65407,                      // 0x00FF7F
            16744448,                   // 0xFF6080
            4194304,                    // 0x400000
            32768                       // 0x008000
        };
        
        int randomColor = colors[this.random.nextInt(colors.length)];
        
        for (int i = 0; i < 50; i++) {
            double offsetX = (this.random.nextDouble() - 0.5) * 4.0;
            double offsetY = (this.random.nextDouble() - 0.5) * 4.0;
            double offsetZ = (this.random.nextDouble() - 0.5) * 4.0;
            
            double velocityX = (this.random.nextDouble() - 0.5) * 0.5;
            double velocityY = this.random.nextDouble() * 0.3;
            double velocityZ = (this.random.nextDouble() - 0.5) * 0.5;
            
            world.spawnParticles(new DustParticleEffect(randomColor, 2.0F),
                    x + offsetX, y + offsetY, z + offsetZ, 1, velocityX, velocityY, velocityZ, 0.1);
        }
    }
}