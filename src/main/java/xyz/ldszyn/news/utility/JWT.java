package xyz.ldszyn.news.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

public class JWT {
    public static String create(String username) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);
        return Jwts.builder().setClaims(map) //设置自定义内容(载荷)
                .signWith(SignatureAlgorithm.HS256, "niyongyuancaibudao") //篡改令牌会报错
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //过期会报错
                .compact();
    }
//    public static String analysis(String JWT){
//        Claims claims = Jwts.parser()
//                .setSigningKey("niyongyuancaibudao")
//                .parseClaimsJws(JWT)
//                .getBody(); //获取自定义内容(载荷 JSON格式)
//        return claims;
//    }

}
