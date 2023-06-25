package dev.dubhe.askway.origin.magical.registers;

import dev.dubhe.askway.origin.AskwayOrigin;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class MagicalRegisters {
    public static final DeferredRegister<ICaster> CASTER_REGISTER = DeferredRegister.create(AskwayOrigin.of("caster"), AskwayOrigin.MODID);
    public static final Supplier<IForgeRegistry<ICaster>> CASTER_REGISTRY = CASTER_REGISTER.makeRegistry(RegistryBuilder::new);

    MagicalRegisters() {
    }
}
