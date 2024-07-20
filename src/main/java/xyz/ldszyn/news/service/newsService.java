package xyz.ldszyn.news.service;

import jakarta.servlet.http.HttpServletRequest;
import xyz.ldszyn.news.POJO.Result;
import xyz.ldszyn.news.POJO.comments;
import xyz.ldszyn.news.POJO.news;
import xyz.ldszyn.news.POJO.type;

public interface newsService {
    Result gettype(HttpServletRequest request);

    Result allgettype();

    Result addnews(HttpServletRequest request, news news);

    Result addtype(HttpServletRequest request, type type);

    Result getnews(HttpServletRequest request);

    Result getnewsdata(int id);

    Result delenews(HttpServletRequest request,int id);

    Result delenewstype(HttpServletRequest request, String category);

    Result upnews(HttpServletRequest request, news news);

    Result getallnews();

    Result uplistlike(HttpServletRequest request, int id, boolean liked);

    Result looktype(String typeId);

    Result getalllikenews(HttpServletRequest request);

    Result getcomments(int id);

    Result addcomment(HttpServletRequest request, comments comments);

    Result delcomment(HttpServletRequest request, int commentId);

    Result getname(HttpServletRequest request);
}
