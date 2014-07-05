package org.lutra.cpa.wrapper;

import com.google.gson.Gson;

import java.util.Map;

public class GsonRootSkipper
{
    private static Gson g = new Gson();
    private GsonRootSkipper(){}

    final public static <T> T getFromJSON(String json, Class<T> clazz)
    {
        Map<String,Object> m = g.fromJson(json, Map.class);
        String inner = g.toJson(m.get(m.keySet().iterator().next()));
        T ret = g.fromJson(inner, clazz);
        return ret;
    }
}
