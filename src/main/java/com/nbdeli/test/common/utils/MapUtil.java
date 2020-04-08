package com.nbdeli.test.common.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 佟星龙
 * @date 2018/6/22.
 */
public class MapUtil {

    /**
     * 根据参数创建HashMap
     *
     * @param args 形式为key,value,key,value..
     * @return
     */
    public static Map convert2HashMap(Object... args) {
        Map<Object, Object> result = new HashMap<>();

        int argsNum = args.length;
        if (argsNum % 2 == 0) {
            //key的数量
            int count = argsNum / 2;
            int idx = 0;
            while (count > 0) {
                Object key = args[idx];
                Object value = args[idx + 1];
                result.put(key, value);

                idx += 2;
                count--;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());

        System.out.println((new Date()).getTime());
    }

}
