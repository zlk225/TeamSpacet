package cn.moling.spacet.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AddressUtil {
    private static Unsafe theUnsafe;
    static {
        try {
            Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            theUnsafe = (Unsafe)theUnsafeField.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    private static Map<String, Long> addressMap = new ConcurrentHashMap<>();
    public static Long getAddress(Class clazz, String fieldName){
        String key = clazz.getName() + "." + fieldName;
        Long address = addressMap.get(key);
        if(address == null){
            try {
                address = theUnsafe.objectFieldOffset(clazz.getDeclaredField(fieldName));
                addressMap.put(key, address);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return -1l;
            }
        }

        return  address;
    }
    public static void putObject(Object object,long address, Object value) {
        theUnsafe.putObject(object,address,value);
    }
    public static Object getObject(Object object,long address) {
        return theUnsafe.getObject(object,address);
    }


}
