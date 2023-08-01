package dev.dubhe.askway.origin.network;

import com.google.gson.Gson;
import dev.dubhe.askway.origin.AskwayOrigin;
import dev.dubhe.askway.origin.events.handler.AskwayModNetworkHandler;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import dev.dubhe.askway.origin.magical.visuals.IVisual;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MagicalVisualNetworkImpl {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            AskwayOrigin.of("visual"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int id = 0;

    public static void register() {
        INSTANCE.registerMessage(id++, MagicalVisualPack.class, MagicalVisualPack::toBuf, MagicalVisualPack::fromBuf, MagicalVisualPack::consumer);
    }

    public record MagicalVisualPack(String id, Vec3 caster, String element, int energy, Vec3 target) {
        public static final Gson GSON = new Gson();

        public static MagicalVisualPack create(IVisual v, ICaster c, AbstractElement elem, int energy, ITarget t) {
            String id = IVisual.VISUAL_CUSTOM_REGISTRY.getId(v);
            Vec3 caster = c.getPos();
            String element = AbstractElement.ELEMENT_CUSTOM_REGISTRY.getId(elem);
            Vec3 target = t.getPos();
            return new MagicalVisualPack(id, caster, element, energy, target);
        }

        public static void toBuf(MagicalVisualPack pack, FriendlyByteBuf buf) {
            buf.writeUtf(GSON.toJson(pack));
        }

        public static MagicalVisualPack fromBuf(FriendlyByteBuf buf) {
            String res = buf.readUtf();
            return GSON.fromJson(res.substring(res.indexOf('{')), MagicalVisualPack.class);
        }

        public static void consumer(MagicalVisualPack pack, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> AskwayModNetworkHandler.visual(pack)));
            context.get().setPacketHandled(true);
        }
    }
}
