package com.service.ucenter;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Date;

/**
 * @author alpha
 * @className: JwtTest
 * @date 2022/8/2 11:12
 * @Description
 */
@SpringBootTest
public class JwtTest {
    private long tokenExpiration = 24 * 60 * 60 * 1000;
    private static String tokenSignKey = "123456";

    @Test
    void jwt() {

        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT") //令牌类型
                .setHeaderParam("alg", "HS256")//算法类型
                .setSubject("user")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("nickname", "bob")
                .claim("avatar", "1.png")
                .signWith(SignatureAlgorithm.HS256, tokenSignKey)
                .compact();
        System.out.println("token = " + token);
        //token = eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjU5NDMwMjEwLCJuaWNrbmFtZSI6ImJvYiIsImF2YXRhciI6IjEucG5nIn0.A8yTUokza5rDg9RGzHwYTUORd-cNQ2JSDe_sdstDYRc

    }
    @Test
    void parse(){
        try {
            String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjU5NTE2Nzk1LCJuaWNrbmFtZSI6ImJvYiIsImF2YXRhciI6IjEucG5nIn0.LC6DpZxWwNYYFARqjh8LRRx15kfuI5ah2UVD36XD78U";
            Jwt jwt = Jwts.parser().setSigningKey(tokenSignKey).parse(token);
            Header header = jwt.getHeader();
            System.out.println("header = " + header);
            DefaultClaims body = (DefaultClaims) jwt.getBody();
//        System.out.println("body.getClass().getName() = " + body.getClass().getName());
//        body.getClass().getName() = io.jsonwebtoken.impl.DefaultClaims

            Object id = body.get("id");
            System.out.println("id = " + id);
            Object avatar = body.get("avatar");
            System.out.println("avatar = " + avatar);
            Date expiration = body.getExpiration();
            System.out.println("expiration = " + expiration);
            String subject = body.getSubject();
            System.out.println("subject = " + subject);
        } catch (ExpiredJwtException e) {
            throw new RuntimeException(e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void testBase64() throws UnsupportedEncodingException {
        String str = "测试字符串mjnj898****";
        //编码
        byte[] encode = Base64.getEncoder().encode(str.getBytes("UTF-8"));
        System.out.println("new String(encode) = " + new String(encode));
        //解码
        byte[] decode = Base64.getDecoder().decode(encode);
        System.out.println("new String(decode) = " + new String(decode,"UTF-8"));
    }

    @Test
    void testUrlEncode() throws UnsupportedEncodingException {
        String url = "www.baidu.com";
        String encode = URLEncoder.encode(url, "UTF-8");
        System.out.println("encode = " + encode);
        String decode = URLDecoder.decode(encode, "UTF-8");
        System.out.println("decode = " + decode);
    }

}
