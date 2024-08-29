package wolforce.hearthwell.events;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;
import wolforce.hearthwell.HearthWell;
import wolforce.hearthwell.net.Net;
import wolforce.hearthwell.particles.ParticleEnergy;
import wolforce.hearthwell.particles.ParticleEnergyData;

import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventsRegistry {

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
//		DataSerializers.registerSerializer(EntityHearthWell.SERIALIZER_BYTE_ARRAY);
//		DataSerializers.registerSerializer(EntityHearthWell.SERIALIZER_SHORT_SET);
		Net.register();
	}

	@SubscribeEvent
	public static void registerBlocks(RegisterEvent event) {
		HearthWell.setupBlocks();
		IForgeRegistry<Block> registry = event.getForgeRegistry();
		for (Map.Entry<String, Block> entry : HearthWell.blocks.entrySet()) {
			registry.register(entry.getKey(), entry.getValue());
		}
	}

	@SubscribeEvent
	public static void registerItems(RegisterEvent event) {
		HearthWell.setupItems();
		IForgeRegistry<Item> registry = event.getForgeRegistry();
		for (Map.Entry<String, Item> entry : HearthWell.items.entrySet()) {
			registry.register(entry.getKey(), entry.getValue());
		}
	}

	@SubscribeEvent
	public static void registerParticles(RegisterEvent event) {
		event.getForgeRegistry().register(ParticleEnergy.REG_ID, ParticleEnergyData.TYPE);
	}

}