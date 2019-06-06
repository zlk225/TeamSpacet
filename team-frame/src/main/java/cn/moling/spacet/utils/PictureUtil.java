package cn.moling.spacet.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Auther: zhanglk
 * @Date: 2018/12/25 09:15
 * @Description:
 */
public class PictureUtil {

    public static void main(String[] args) throws Exception {
        String src = "E:\\workspace_idea\\nin_course_v1.0\\lspt-dsh\\target\\lspt-dsh-0.0.1-SNAPSHOT\\WEB-INF\\classes\\static\\data\\upload\\127a5d6a11ab41a898406a0081ef5cc5.png";
        String type = getPictureOriginallySuffix(src);
        System.out.println(type);
    }

    /**
     * 获取图片最初的格式
     * @param pictureAddress
     * @return
     */
    public static String getPictureOriginallySuffix(String pictureAddress){
        FileInputStream is = null;
        try {
            is = new FileInputStream(pictureAddress);
            return getTypeByStream(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return  "";
        }
    }

    /**
     * byte数组转换成16进制字符串
     *
     * @param src byte数组
     * @return 返回16进制字符串
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 根据文件流读取图片文件真实类型
     *
     * @param is FileInputStream
     * @return 返回真实格式
     */
    public static String getTypeByStream(FileInputStream is) {
        byte[] b = new byte[4];
        try {
            int i = is.read(b, 0, b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String type = bytesToHexString(b);
        if (type != null) {
            type = type.toUpperCase();
            if (type.contains("FFD8FF")) {
                return "jpg";
            } else if (type.contains("89504E47")) {
                return "png";
            } else if (type.contains("47494638")) {
                return "gif";
            } else if (type.contains("49492A00")) {
                return "tif";
            } else if (type.contains("424D")) {
                return "bmp";
            }
        }
        return type;
    }
}
