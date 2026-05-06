package cc.badideas.nocreeperdamage.mixin;

import cc.badideas.nocreeperdamage.NoCreeperDamage;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerExplosion.class)
public class ExplosionImplMixin {
    @Shadow @Final
    @Mutable
    private Explosion.BlockInteraction blockInteraction;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void afterConstructor(
            ServerLevel world,
            Entity entity,
            DamageSource damageSource,
            ExplosionDamageCalculator behavior,
            Vec3 pos,
            float power,
            boolean createFire,
            Explosion.BlockInteraction destructionType,
            CallbackInfo ci
    ) {
        if (entity instanceof Creeper) {
            boolean allowCreeperDamage = world.getGameRules().get(NoCreeperDamage.CREEPER_BLOCK_DAMAGE);
            if (!allowCreeperDamage) {
                this.blockInteraction = Explosion.BlockInteraction.KEEP;
            }
        }
    }
}
