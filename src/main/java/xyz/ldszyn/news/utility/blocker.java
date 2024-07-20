package xyz.ldszyn.news.utility;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.ldszyn.news.POJO.Result;
import xyz.ldszyn.news.dao.usersDao;

@Component
public class blocker implements HandlerInterceptor {
    @Autowired
    usersDao usersDao;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey("niyongyuancaibudao")
                    .parseClaimsJws(request.getHeader("Authorization"))
                    .getBody();
            if (usersDao.queryJWT(request.getHeader("Authorization"))!=1){
                response.getWriter().write(JSONObject.toJSONString(Result.notLogin()));
                return false;
            }
            return true;
        } catch (Exception e) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(Result.notLogin()));
            return false;
        }
    }
}
