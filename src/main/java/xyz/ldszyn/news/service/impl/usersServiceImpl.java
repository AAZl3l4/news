package xyz.ldszyn.news.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ldszyn.news.POJO.Result;
import xyz.ldszyn.news.POJO.Users;
import xyz.ldszyn.news.dao.usersDao;
import xyz.ldszyn.news.service.usersService;
import xyz.ldszyn.news.utility.JWT;

@Service
public class usersServiceImpl implements usersService {
    @Autowired
    usersDao usersDao;

    @Override

    public Result reg(Users user) {

        if (usersDao.query(user.username) != null) return Result.error("用户名已存在");
        if (usersDao.reg(user) != 0) return Result.succeed("注册成功");
        else return Result.error("注册失败");
    }

    @Override
    public Result login(String username, String password) {
        Users query = usersDao.query(username);
        if (query == null) return Result.error("用户不存在");
        if (query.isban==1) return Result.error("用户已封禁");
        else if (query.password.equals(password)){
            String jwt = JWT.create(username);
            usersDao.addJWT(jwt,query.id);
            return Result.succeed(jwt);
        }
        else return Result.error("密码错误");
    }

    @Override
    public Result getuser(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        Users user = usersDao.getuser(jwt);
        return Result.succeed(user);
    }

    @Override
    public Result upuser(HttpServletRequest request,Users user) {
        String jwt = request.getHeader("Authorization");
        Users u = usersDao.getuser(jwt);
        if (usersDao.upuser(user)!=1){return Result.error("修改异常");}
        return Result.succeed("修改成功");
    }

    @Override
    public Result popup(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        if (usersDao.upjwt(jwt)!=1){return Result.error("退出异常");}
            return Result.succeed("退出成功");
    }

    @Override
    public Result getimg(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        Users u = usersDao.getuser(jwt);
        if (u==null){return Result.error("数据异常");}
        return Result.succeed(u.headImg);
    }
}
