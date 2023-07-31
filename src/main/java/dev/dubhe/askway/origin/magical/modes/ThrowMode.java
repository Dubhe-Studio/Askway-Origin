package dev.dubhe.askway.origin.magical.modes;

import dev.dubhe.askway.origin.entities.MagicEntity;
import dev.dubhe.askway.origin.init.AskwayModEntities;
import dev.dubhe.askway.origin.magical.MagicGroup;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.goals.IGoal;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ThrowMode implements IMode {
    @Override
    public void execute(@NotNull ICaster caster, IGoal goal, ITarget direct, MagicGroup @NotNull ... magics) {
        MagicEntity magic = AskwayModEntities.MAGIC.get().create(caster.getLevel());
        if (magic != null) {
            caster.getLevel().addFreshEntity(magic);
            magic.setMagicGroup(magics).setOwner(caster.getOwner());
            magic.moveTo(caster.getPos());
            Vec3 vec3 = caster.getUpVector();
            Vector3f vector3f = caster.getViewVector().toVector3f().rotate(new Quaternionf().setAngleAxis(0.0D, vec3.x, vec3.y, vec3.z));
            magic.shoot(vector3f.x(), vector3f.y(), vector3f.z(), 1.2F, 1.0F);
        }
    }
}
