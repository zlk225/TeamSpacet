package cn.moling.spacet.utils;

import com.google.common.base.CaseFormat;

/**
 * @Auther: zhanglk
 * @Date: 2019/4/19 09:13
 * @Description:
 */
public class StringUtil {

    /**
     * 判断是否是空字符串null和""
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if (str != null && !str.equals("")) {
            return false;
        }
        return true;
    }

    /**
     * 判断两个字符串是否相等 如果都为null则判断为相等,一个为null另一个not null则判断不相等 否则如果s1=s2则相等
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean equals(String s1, String s2) {
        if (StringUtil.isEmpty(s1) && StringUtil.isEmpty(s2)) {
            return true;
        } else if (!StringUtil.isEmpty(s1) && !StringUtil.isEmpty(s2)) {
            return s1.equals(s2);
        }
        return false;
    }

    /**
     * 生成uuid
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 判断字符串是否为null、“ ”、“null”
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(String obj) {
        if (obj == null) {
            return true;
        } else if (obj.toString().trim().equals("")) {
            return true;
        } else if (obj.toString().trim().toLowerCase().equals("null")) {
            return true;
        }

        return false;
    }

    /**
     * 跟上面一样，只是传入值不一样
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (null == obj) return false;
        return isNullOrEmpty(obj.toString());
    }

    /**
     * 判断字符串是否为空格 或 null
     *
     * @param arg
     * @return
     */
    public static boolean isBlankOrNull(String arg) {
        if (arg == null) return true;
        if (arg.trim().equals("")) return true;
        return false;
    }


    /**
     * 获取一个36位的唯一标识。
     *
     * @return
     */
    public static String getUUID36() {
        return UUID.randomUUID().toString();
    }

    /*转为首字母小写的驼峰*/
    public static String ConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

}
