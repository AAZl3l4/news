package xyz.ldszyn.news.controller.Impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.ldszyn.news.POJO.Login;
import xyz.ldszyn.news.POJO.Result;
import xyz.ldszyn.news.POJO.Users;
import xyz.ldszyn.news.controller.usersController;
import xyz.ldszyn.news.service.usersService;

import java.time.LocalDate;


@RestController
public class usersControllerImpl implements usersController {
    @Autowired
    usersService users;

    @PostMapping("/reg")
    public Result reg(@RequestBody Users user){
        return users.reg(user);
    }
    @PostMapping("/login")
    public Result login(@RequestBody Login login){
        return users.login(login.username,login.password);
    }
    @GetMapping("/getuser")
    public Result getuser(HttpServletRequest request){
        return users.getuser(request);
    }
    @PutMapping("/upuser")
    public Result upuser(HttpServletRequest request,@RequestBody Users user){
        return users.upuser(request,user);
    }
    @GetMapping("/popup")
    public Result popup(HttpServletRequest request){
        return users.popup(request);
    }
    @GetMapping("/getimg")
    public Result getimg(HttpServletRequest request){
        return users.getimg(request);
    }

}
