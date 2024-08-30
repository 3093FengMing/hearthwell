package wolforce.hearthwell.registries;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wolforce.hearthwell.HearthWell;

public class Particles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HearthWell.MODID);

    public static <T extends ParticleOptions> RegistryObject<ParticleType<T>> register(String id, ParticleType<T> particleType) {
        return PARTICLES.register(id, () -> particleType);
    }

    public static void register(IEventBus bus) {
        Particles.PARTICLES.register(bus);
    }
}
