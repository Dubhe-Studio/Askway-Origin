package dev.dubhe.askway.origin.utils;

import dev.dubhe.askway.origin.exception.CustomRegistryEntryExistsException;
import dev.dubhe.askway.origin.exception.CustomRegistryEntryRepeatException;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

public class CustomRegistry<T> {
    private final String modId;
    private final String registryId;
    private final Map<String, T> KV_MAP = new HashMap<>();
    private final Map<T, String> VK_MAP = new HashMap<>();

    public CustomRegistry(String modId, String registryId) {
        this.modId = modId;
        this.registryId = registryId;
        REGISTRATE.addRawLang("%s.%s".formatted(registryId, modId), CustomRegistry.convertName(registryId));
    }

    public Component getRegistryDisplayName() {
        return Component.translatable("%s.%s".formatted(registryId, modId));
    }

    public Component getRegistryEntryDisplayName(T t) {
        return Component.translatable("%s.%s.%s".formatted(registryId, modId, this.getId(t)));
    }

    public CustomRegistry(ResourceLocation location) {
        this(location.getNamespace(), location.getPath());
    }

    public final T register(String id, T t) {
        if (KV_MAP.containsKey(id) || VK_MAP.containsKey(t)) throw new CustomRegistryEntryRepeatException();
        KV_MAP.put(id, t);
        VK_MAP.put(t, id);
        REGISTRATE.addRawLang("%s.%s.%s".formatted(registryId, modId, id), CustomRegistry.convertName(id));
        return t;
    }

    @Nonnull
    public final T get(@Nonnull String id) {
        T t = KV_MAP.getOrDefault(id, null);
        if (t == null) throw new CustomRegistryEntryExistsException();
        return t;
    }

    @Nonnull
    public final String getId(@Nonnull T t) {
        String id = VK_MAP.getOrDefault(t, null);
        if (id == null) throw new CustomRegistryEntryExistsException();
        return id;
    }

    public boolean containID(String t) {
        return KV_MAP.containsKey(t);
    }

    public boolean contain(T t) {
        return VK_MAP.containsKey(t);
    }

    public static String convertName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母大写
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            if (camel.equals(camels[0])) result.append(" ");
            // 首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }
}
