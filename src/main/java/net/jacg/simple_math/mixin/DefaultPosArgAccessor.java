package net.jacg.simple_math.mixin;

import net.minecraft.command.argument.CoordinateArgument;
import net.minecraft.command.argument.DefaultPosArgument;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DefaultPosArgument.class)
public interface DefaultPosArgAccessor {
    @Accessor("x")
    CoordinateArgument simple_math$getX();

    @Accessor("y")
    CoordinateArgument simple_math$getY();

    @Accessor("z")
    CoordinateArgument simple_math$getZ();
}
