package dev.dubhe.askway.origin.utils;

import dev.dubhe.askway.origin.exception.CustomRegistryEntryExistsException;
import dev.dubhe.askway.origin.exception.CustomRegistryEntryRepeatException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class CustomRegistry<T> {
    private final Map<String, T> KV_MAP = new HashMap<>();
    private final Map<T, String> VK_MAP = new HashMap<>();

    public final T register(String id, T t) {
        if (KV_MAP.containsKey(id) || VK_MAP.containsKey(t)) throw new CustomRegistryEntryRepeatException();
        KV_MAP.put(id, t);
        VK_MAP.put(t, id);
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
}
