package com.bsk.security.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 监听session
 * @author jiangw
 * @date 2020/9/13 10:26
 * @since 1.0
 */
public class ShiroSessionListener implements SessionListener {

    private static Logger logger = LoggerFactory.getLogger(ShiroSessionListener.class);

    /**
     * 会话创建
     * @author jiangw
     * @date 2020/9/13 10:28
     */
    @Override
    public void onStart(Session session) {
        logger.info("会话创建：" + session);
    }

    /**
     * 会话停止
     * @author jiangw
     * @date 2020/9/13 10:28
     */
    @Override
    public void onStop(Session session) {
        logger.info("会话停止：" + session);

    }

    /**
     * 会话过期
     * @author jiangw
     * @date 2020/9/13 10:28
     */
    @Override
    public void onExpiration(Session session) {
        logger.info("会话过期：" + session);

    }
}
