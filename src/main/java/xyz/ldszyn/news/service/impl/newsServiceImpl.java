package xyz.ldszyn.news.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ldszyn.news.POJO.*;
import xyz.ldszyn.news.dao.newsDao;
import xyz.ldszyn.news.dao.usersDao;
import xyz.ldszyn.news.service.newsService;

import java.util.Arrays;
import java.util.List;


@Service
public class newsServiceImpl implements newsService {
    @Autowired
    usersDao usersDao;
    @Autowired
    newsDao newsDao ;

    @Override
    public Result gettype(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        Users u = usersDao.getuser(jwt);
        return Result.succeed(newsDao.gettype(u.id));
    }
    @Override
    public Result allgettype() {
        return Result.succeed(newsDao.getalltype());
    }

    @Override
    public Result addnews(HttpServletRequest request, news news) {
        String jwt = request.getHeader("Authorization");
        Users u = usersDao.getuser(jwt);
        news.userId=u.id;
        if (news.content.equals("<script>"))return Result.error("包含js语句");
        if (newsDao.addnews(news)!=1)return Result.error("添加失败");
        return Result.succeed("添加成功");
    }

    @Override
    public Result addtype(HttpServletRequest request, type type) {
        String jwt = request.getHeader("Authorization");
        Users u = usersDao.getuser(jwt);
        type.userId=u.id;
        String[] gettype = newsDao.gettype(u.id);
        List<String> namesList = Arrays.asList(gettype);
        boolean contains = namesList.stream().anyMatch(name -> name.equals(type.typeName));
        if (contains==true) return Result.error("添加失败,类别已存在");
        if (newsDao.addtype(type)!=1)return Result.error("添加失败");
        return Result.succeed("添加成功");
    }

    @Override
    public Result getnews(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        Users u = usersDao.getuser(jwt);
        return Result.succeed(newsDao.getnews(u.id));
    }

    @Override
    public Result getnewsdata(int id) {
        return Result.succeed(newsDao.getnewsdata(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result delenews(HttpServletRequest request,int id) {
        String jwt = request.getHeader("Authorization");
        Users u = usersDao.getuser(jwt);
        if (newsDao.delenews(u.id,id)!=1) return Result.error("删除失败");
        newsDao.deluserenews(id);
        newsDao.deluserelike(id);
        return Result.succeed("删除成功");
    }

    @Override
    public Result delenewstype(HttpServletRequest request, String typeName) {
        String jwt = request.getHeader("Authorization");
        Users u = usersDao.getuser(jwt);
        if (newsDao.delenewstype(u.id,typeName)==0) return Result.error("删除失败");
        newsDao.deltypenews(typeName);
        return Result.succeed("删除成功");
    }

    @Override
    public Result upnews(HttpServletRequest request, news news) {
        String jwt = request.getHeader("Authorization");
        Users u = usersDao.getuser(jwt);
        if (newsDao.upnews(news, u.id)!=1)  return Result.error("修改失败");
        return Result.succeed("修改成功");
    }

    @Override
    public Result getallnews() {
        return Result.succeed(newsDao.getallnews());
    }

    @Override
    public Result uplistlike(HttpServletRequest request, int id, boolean liked) {
        String jwt = request.getHeader("Authorization");
        Users u = usersDao.getuser(jwt);
        System.out.println("现在是"+liked);
        if (liked==false){
            if (newsDao.looklist(u.id,id)==1)return Result.error("已经点赞过了,快去查看吧");
            if (newsDao.addlist(u.id,id)==1) return Result.succeed("添加成功");
        }
        if (liked==true) {
            if(newsDao.removelist(u.id,id)==1) return Result.succeed("删除成功");
        }
        return Result.error("更新失败");
    }

    @Override
    public Result looktype(String typeId) {
        return Result.succeed(newsDao.looktype(typeId));
    }

    @Override
    public Result getalllikenews(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        Users u = usersDao.getuser(jwt);
        int[] getlike = newsDao.getlike(u.id);
        if(getlike==null||getlike.length==0)return Result.error("你还没有喜欢");
        return Result.succeed(newsDao.getalllikenews(getlike));
    }

    @Override
    public Result getcomments(int id) {
        return Result.succeed(newsDao.getcomments(id));
    }

    @Override
    public Result addcomment(HttpServletRequest request, comments comments) {
        if (comments.text.equals("<script>")) return Result.error("包含js语句");
        String jwt = request.getHeader("Authorization");
        Users u = usersDao.getuser(jwt);
        comments.userId=u.name;
        if (newsDao.addcomments(comments)==1) return Result.succeed("评论成功");
        return Result.error("评论失败");
    }

    @Override
    public Result delcomment(HttpServletRequest request, int commentId) {
        String jwt = request.getHeader("Authorization");
        Users u = usersDao.getuser(jwt);
        int news_id=usersDao.queryid(commentId);
        int user_id=usersDao.queryuserid(news_id);
        if (u.id!=user_id)return Result.error("无权限");
        usersDao.delcomment(commentId);
        return null;
    }

    @Override
    public Result getname(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        System.out.println(jwt);
        Users u = usersDao.getuser(jwt);
        return Result.succeed(u);
    }
}
