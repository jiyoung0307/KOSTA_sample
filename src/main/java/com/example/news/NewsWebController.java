package com.example.news;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/news")
public class NewsWebController {
    final NewsDAO dao;

    @Value("${news.imgdir}")
    String imgDir;

    @Autowired
    public NewsWebController(NewsDAO dao) {
        this.dao = dao;
    }
    // 목록보기
    @GetMapping("/list")
    public String list(Model m){
        try {
            List<News> newsList = dao.getAll();
            m.addAttribute("newsList", newsList);
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("뉴스 목록 가져오기에서 문제 발생");
            m.addAttribute("error", "뉴스 목록 보기가 정상적으로 처리되지 않았습니다.");
        }
        return "news/newsList";
    }

    // 등록, 삭제, 상세보기
    @PostMapping("/addNews")
    public String addNews(@RequestParam("title") String title,
                          @RequestParam("img") MultipartFile img,
                          @RequestParam("content") String content,
                          Model m){

        try {
            File file = new File(imgDir + img.getOriginalFilename());
            News news = new News();
            news.setTitle(title);
            news.setContent(content);
            news.setImg("/img/"+file.getName());
            int res = dao.addNews(news);
            if(res == 1) img.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("뉴스 등록 과정에서 오류 발생!");
            m.addAttribute("error", "뉴스 등록이 정상적으로 처리되지 않았습니다.");
        }
        return "redirect:/news/list";
    }

    @GetMapping("/delete/{aid}")
    public String delNews(@PathVariable("aid") int aid, Model m){
        try {
            dao.delNews(aid);
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("뉴스 삭제 오류 발생!");
            m.addAttribute("error", "뉴스가 정상적으로 삭제되지 않았습니다.");
        }
        return "redirect:/news/list";
    }

    @GetMapping("/{aid}")
    public String getNews(@PathVariable("aid") int aid, Model m){
        try {
            News news = dao.getNews(aid);
            m.addAttribute("news", news);
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("뉴스 상세보기 발생!");
            m.addAttribute("error", "뉴스 상세보기가 정상적으로 처리되지 않았습니다.");
        }
        return "/news/newsView";
    }
}