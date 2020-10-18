package com.bsk.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bsk.auth")
public class AuthProperties {

    private final Lock lock = new Lock();

    private final Validate validate = new Validate();

    public Lock getLock() {
        return lock;
    }

    public Validate getValidate() {
        return validate;
    }

    /**
     * 用户锁配置
     */
    public static class Lock {

        /**
         * 锁住重试次数
         */
        private int retry = 5;

        /**
         * 锁住时间，单位为s
         */
        private int time = 30;

        public int getRetry() {
            return retry;
        }

        public void setRetry(int retry) {
            this.retry = retry;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }

    public static class Validate {

        /**
         * 重试次数，超过该数值，需要填写验证码
         */
        private int retry = 3;

        public int getRetry() {
            return retry;
        }

        public void setRetry(int retry) {
            this.retry = retry;
        }
    }
}
