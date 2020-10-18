package com.bsk.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bsk.token")
public class TokenProperties {

    /**
     * 加密类型
     */
    private String type = "JWT";

    /**
     * token过期时间
     */
    private long expire = 10000;

    /**
     * 加密算法
     * 1.HS256/AES对称加密算法
     * 2.RSA非对称加密
     * 3.MD5/SHA不可逆加密
     */
    private String algorithm = "HS256";

    /**
     * 密钥
     */
    private String secret = "bsk";


    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
