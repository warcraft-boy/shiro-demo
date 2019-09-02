package com.chenjianwen.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * @Description: 自定义sessionManager
 * @Date: Created in 2019/9/2 <br>
 * @Author: chenjianwen
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {

        Session session = null;
        Serializable sessionId = getSessionId(sessionKey);
        ServletRequest request = null;
        if(sessionKey instanceof WebSessionKey){
            request = ((WebSessionKey)sessionKey).getServletRequest();
            if(request != null && sessionId != null){
                session = (Session) request.getAttribute(sessionId.toString());
                if(session == null){
                    session = super.retrieveSession(sessionKey);
                    request.setAttribute(sessionId.toString(),session);
                }
            }
        }else{
            session = super.retrieveSession(sessionKey);
        }
        return session;
    }
}
