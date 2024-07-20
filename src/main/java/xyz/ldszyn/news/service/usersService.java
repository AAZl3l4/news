package xyz.ldszyn.news.service;

import jakarta.servlet.http.HttpServletRequest;
import xyz.ldszyn.news.POJO.Result;
import xyz.ldszyn.news.POJO.Users;


public interface usersService {
    Result reg(Users user);

    Result login(String username,String password);

    Result getuser(HttpServletRequest request);

    Result upuser(HttpServletRequest request,Users user);

    Result popup(HttpServletRequest request);

    Result getimg(HttpServletRequest request);
}
