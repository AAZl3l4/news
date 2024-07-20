package xyz.ldszyn.news.controller.Impl;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.ldszyn.news.POJO.Result;
import xyz.ldszyn.news.dao.usersDao;

import java.io.IOException;
import java.time.Instant;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

// 使用 @ServerEndpoint 注解表示此类是一个 WebSocket 端点
// 通过 value 注解，指定 websocket 的路径
@ServerEndpoint(value = "/PP")
@Component
public class PP {
    private Session session;
    int S = 0;
    String JWT;
    private static Set<Session> sessions = new CopyOnWriteArraySet<>();

    // 收到消息
    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println(message);
        if (S == 0) {
            JWT = message;
            S += 1;
            return;
        }
        if (S==1){
            if (JWTver(JWT)!=true){
                this.session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, "身份验证失败"));
                return;
            }
            S++;
        }
        if (JWT == null || JWT == "") {
            this.session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, "身份验证失败"));
        }
        String[] parts = message.split(".and");
        JSONObject messageObject = new JSONObject();
        messageObject.put("username", parts[0]);
        messageObject.put("avatar", parts[1]);
        messageObject.put("text", parts[2]);
        messageObject.put("time", parts[3]);
        String jsonString = messageObject.toString();
        mass(jsonString);
    }

    // 连接打开
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) throws IOException {
        // 获取客户端请求的 HTTP 握手头部信息
        this.session = session;
        sessions.add(session);
    }

    // 连接关闭
    @OnClose
    public void onClose(CloseReason closeReason) {
        sessions.remove(session);
    }

    // 连接异常
    @OnError
    public void onError(Throwable throwable) throws IOException {
        this.session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, "连接异常"));
    }

    //群发
    public void mass(String message) throws IOException {
        for (Session session : sessions) {
            if (session.isOpen() && !session.equals(this.session)) {
                session.getBasicRemote().sendText(message);
            }
        }
    }
    private boolean JWTver(String JWT) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey("niyongyuancaibudao")
                    .parseClaimsJws(JWT)
                    .getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}