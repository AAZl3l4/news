package xyz.ldszyn.news.utility;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import xyz.ldszyn.news.POJO.Result;

@RestControllerAdvice
public class unusual {
    @ExceptionHandler(HandlerMethodValidationException.class)
    public Result unusual(){
        return Result.error("参数异常");
    }
}
