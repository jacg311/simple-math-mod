package net.jacg.simple_math;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.jacg.simple_math.mixin.DefaultPosArgAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.CoordinateArgument;
import net.minecraft.command.argument.DefaultPosArgument;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class SimpleMathClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(literal("simpleMath")
                        .then(literal("distance")
                                        .then(argument("location_a", BlockPosArgumentType.blockPos())
                                                .then(argument("location_b", BlockPosArgumentType.blockPos())
                                                        .executes(context -> {
                                                            var posA = posFromArg(context.getArgument("location_a", DefaultPosArgument.class), context.getSource());
                                                            var posB = posFromArg(context.getArgument("location_b", DefaultPosArgument.class), context.getSource());

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

    private static BlockPos posFromArg(DefaultPosArgument arg, FabricClientCommandSource source) {
        Vec3d pos = source.getPosition();
        DefaultPosArgAccessor access = (DefaultPosArgAccessor)arg;
        return new BlockPos(
                abs(access.simple_math$getX(), pos.x),
                abs(access.simple_math$getY(), pos.y),
                abs(access.simple_math$getZ(), pos.z)
        );
    }

    private static double abs(CoordinateArgument coordArg, double offset) {
        return coordArg.toAbsoluteCoordinate(offset);
    }
}
