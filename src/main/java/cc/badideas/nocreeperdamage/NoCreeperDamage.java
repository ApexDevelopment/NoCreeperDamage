package cc.badideas.nocreeperdamage;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoCreeperDamage implements ModInitializer {
    public static final String MOD_ID = "nocreeperdamage";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final GameRule<Boolean> CREEPER_BLOCK_DAMAGE = GameRuleBuilder.forBoolean(true)
            .category(GameRuleCategory.MOBS)
            .buildAndRegister(Identifier.fromNamespaceAndPath(MOD_ID, "creeper_block_damage"));

    @Override
    public void onInitialize() {
        LOGGER.info("NoCreeperDamage initialized");
    }
}
