package xyz.ldszyn.news.controller.Impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.ldszyn.news.POJO.*;
import xyz.ldszyn.news.controller.newsConteroller;
import xyz.ldszyn.news.service.newsService;

import javax.xml.transform.Source;


@RestController
public class newsConterollerImpl implements newsConteroller {
    @Autowired
    newsService newsService;

    @PostMapping("/addnews")
    public Result addnews(HttpServletRequest request, @RequestBody news news) {
        return newsService.addnews(request, news);
    }

    @GetMapping("/gettype")
    public Result gettype(HttpServletRequest request) {
        return newsService.gettype(request);
    }
    @GetMapping("/getalltype")
    public Result getalltype() {
        return newsService.allgettype();
    }

    @PostMapping("/addtype")
    public Result addtype(HttpServletRequest request, @RequestBody type type) {
        return newsService.addtype(request, type);
    }

    @GetMapping("/getnews")
    public Result getnews(HttpServletRequest request) {
        return newsService.getnews(request);
    }

    @GetMapping("/getnewsdata")
    public Result getnewsdata(int id) {
        return newsService.getnewsdata(id);
    }

    @DeleteMapping("/delenews/{id}")
    public Result delenews(HttpServletRequest request, @PathVariable("id") int id) {
        return newsService.delenews(request, id);
    }

    @DeleteMapping("/delenewstype")
    public Result delenewstype(HttpServletRequest request, @RequestParam(value = "categoryName") String typeName) {
        return newsService.delenewstype(request, typeName);
    }

    @PutMapping("/upnews")
    public Result upnews(HttpServletRequest request, @RequestBody news news) {
        return newsService.upnews(request, news);
    }

    @GetMapping("/getallnews")
    public Result getallnews() {return newsService.getallnews();}
    @GetMapping("/getalllikenews")
    public Result getalllikenews(HttpServletRequest request) {return newsService.getalllikenews(request);}

    @PutMapping("/uplistlike")
    public Result uplistlike(HttpServletRequest request,@RequestBody list list) {
        return newsService.uplistlike(request,list.id,list.liked);
    }
    @PutMapping("/looktype")
    public Result looktype(@RequestParam("typeId") String typeId) {
        return newsService.looktype(typeId);
    }
    @GetMapping("/getcomments")
    public Result getcomments(int id){
        return newsService.getcomments(id);
    }
    @PostMapping("/addcomment")
    public Result addcomment(HttpServletRequest request,@RequestBody comments comments){
        return newsService.addcomment(request,comments);
    }
    @DeleteMapping("/delcomment/{commentId}")
    public Result delcomment(HttpServletRequest request,@PathVariable int commentId){
        return newsService.delcomment(request,commentId);
    }
    @GetMapping("/getname")
    public Result getname(HttpServletRequest request) {return newsService.getname(request);}
}
