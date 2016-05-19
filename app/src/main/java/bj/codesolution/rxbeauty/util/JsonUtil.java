package bj.codesolution.rxbeauty.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public class JsonUtil {
    private volatile static Gson gson;

    private static Gson getInstance() {
        if (gson == null) {
            synchronized (Gson.class) {
                if (gson == null) {
                    gson = new Gson();
                }
            }
        }
        return gson;
    }

    public static <T> Object fromJson(String json, Class<T> classOfT) {
        return JsonUtil.getInstance().fromJson(json, (Type) classOfT);
    }

    public static String toJson(Object object) {
        return JsonUtil.getInstance().toJson(object);
    }
}
