package com.demo.springbootdemo.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtil {
    // 密钥
    private static final String SECRET_KEY = "secret";
    private static final long EXPIRATION_TIME = 6000000;

    /**
     * 生成Token
     */
    public static String generateToken(Map<String, Object> claims) {

        Map<String, Object> map = new HashMap<>();
        map.put("users", claims);
        map.put("expire", System.currentTimeMillis() + EXPIRATION_TIME);
        return JWTUtil.createToken(map, SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解析Token
     */
    public static Map<String, Object> parseToken(String token) {
        if (StrUtil.isBlank(token)) {
            return null;
        }
        JWT jwt = JWTUtil.parseToken(token);
        return jwt.getPayloads();
    }

    /**
     * 验证Token是否有效
     */
    public static boolean verifyToken(String token) {
        if (StrUtil.isBlank(token)) {
            return false;
        }
        return JWTUtil.verify(token, SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 判断Token是否过期
     */
    public static boolean isTokenExpired(String token) {
        if (StrUtil.isBlank(token)) {
            return true;
        }
        JWT jwt = JWTUtil.parseToken(token);
        Object exp = jwt.getPayload("expire");
        long currentTimeStamp = System.currentTimeMillis();
        log.info("exp: {}-{}", exp, currentTimeStamp);
        if (exp == null) {
            return false;
        }
        long expireTimestamp;
        if (exp instanceof Date) {
            expireTimestamp = ((Date) exp).getTime();
        } else if (exp instanceof Number) {
            expireTimestamp = ((Number) exp).longValue();
        } else {
            try {
                expireTimestamp = Long.parseLong(exp.toString());
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return expireTimestamp <= currentTimeStamp;
    }
}
