package cn.moling.spacet.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

/**
 * @Auther: zhanglk
 * @Date: 2019/5/30 11:51
 * @Description:
 */
public class JwtTokenUtil {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "moling";
    private static final String ISS = "zlk";

    // 添加角色的key
    public static final String ROLE_CLAIMS = "rol";


    // 过期时间是3600秒，既是1个小时
    private static final long EXPIRATION = 3600L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;

    // 寻找证书文件
    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks"); // 寻找证书文件
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    static { // 将证书文件里边的私钥公钥拿出来
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS"); // java key store 固定常量
            keyStore.load(inputStream, "moling".toCharArray());
            privateKey = (PrivateKey) keyStore.getKey("jwt", "moling".toCharArray()); // jwt 为 命令生成整数文件时的别名
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成token
     * @param subject （主体信息）
     * @param expirationSeconds 过期时间（秒）
     * @param claims 自定义身份信息
     * @return
     */
    public static String generateToken(String subject, int expirationSeconds, Map<String,Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
//                .signWith(SignatureAlgorithm.HS512, salt) // 不使用公钥私钥
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * @author: zzx
     * @date: 2018-10-19 09:10
     * @deprecation: 解析token,获得subject中的信息
     */
    public static String parseToken(String token, String salt) {
        String subject = null;
        try {
            /*Claims claims = Jwts.parser()
//                    .setSigningKey(salt) // 不使用公钥私钥
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();*/
            subject = getTokenBody(token).getSubject();
        } catch (Exception e) {
        }
        return subject;
    }

    //获取token自定义属性
    public static Map<String,Object> getClaims(String token){
        Map<String,Object> claims = null;
        try {
            claims = getTokenBody(token);
        }catch (Exception e) {
        }

        return claims;
    }

    // 是否已过期
    public static boolean isExpiration(String expirationTime){
        /*return getTokenBody(token).getExpiration().before(new Date());*/

        //通过redis中的失效时间进行判断
        String currentTime = DateUtil.getTime();
        if(DateUtil.compareDate(currentTime,expirationTime)){
            //当前时间比过期时间小，失效
            return true;
        }else{
            return false;
        }
    }

    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
