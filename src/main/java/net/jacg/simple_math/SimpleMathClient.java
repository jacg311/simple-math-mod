package net.jacg.simple_math;

import dev.xpple.clientarguments.arguments.CBlockPosArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class SimpleMathClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(literal("simpleMath")
                        .then(literal("distance")
                                        .then(argument("location_a", CBlockPosArgumentType.blockPos())
                                                .then(argument("location_b", CBlockPosArgumentType.blockPos())
                                                        .executes(context -> {
                                                            var posA = CBlockPosArgumentType.getCBlockPos(context, "location_a");
                                                            var posB = CBlockPosArgumentType.getCBlockPos(context, "location_b");

                                                            context.getSource().sendFeedback(Text.of("Distance: " +
                                                                    (posA.getX() - posB.getX()) + " " +
                                                                    (posA.getY() - posB.getY()) + " " +
                                                                    (posA.getZ() - posB.getZ())
                                                            ));
                                                            return 1;
                                                        })
                                                )
                                        )
                        )
        ));
    }
}
