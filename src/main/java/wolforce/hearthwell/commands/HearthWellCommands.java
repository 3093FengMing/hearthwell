package wolforce.hearthwell.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import wolforce.hearthwell.client.screen.ScreenHearthWellMap;
import wolforce.hearthwell.data.MapData;

import java.util.function.Predicate;

import static net.minecraft.commands.Commands.literal;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HearthWellCommands {

	private static Predicate<CommandSourceStack> OP = (source) -> {
		return source.hasPermission(2);
	};

	@SubscribeEvent
	public static void init(RegisterCommandsEvent event) {
		CommandDispatcher<CommandSourceStack> d = event.getDispatcher();

		if (FMLEnvironment.dist == Dist.CLIENT) {
			d.register(literal("hw")//
					.then(literal("reload") //
							.requires(OP) //
							.executes(HearthWellCommands.reloadData)//
					)//
					.then(literal("edit") //
							.requires(OP) //
							.executes(HearthWellCommands.toggleEditMode)//
					)//
			); //
		}
	}

	public static final Command<CommandSourceStack> reloadData = (CommandContext<CommandSourceStack> context) -> {
		MapData.loadData();
		return 1;
	};

	@SuppressWarnings("resource")
	public static final Command<CommandSourceStack> toggleEditMode = (CommandContext<CommandSourceStack> context) -> {
		ScreenHearthWellMap.EDIT_MODE = !ScreenHearthWellMap.EDIT_MODE;
		Minecraft.getInstance().player.sendSystemMessage(Component.literal("Hearth Well Edit Mode is now: " + (ScreenHearthWellMap.EDIT_MODE ? "ON" : "OFF")));
		return 1;
	};
}
