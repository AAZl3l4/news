package xyz.ldszyn.news.POJO;

public class Result {
    public int code;
    public String msg;
    public Object data;

    public Result(Object data) {
        this.code=1;
        this.msg="请求成功";
        this.data=data;
    }
    public Result(String msg) {
        this.code=0;
        this.msg=msg;
        this.data=null;
    }
    public Result() {
        this.code=4;
        this.msg="请先登录";
        this.data=null;
    }

    public static Result succeed(Object data){
        return new Result(data);
    }
    public static Result error(String msg){
        return new Result(msg);
    }
    public static Result notLogin(){
        return new Result();
    }

}
