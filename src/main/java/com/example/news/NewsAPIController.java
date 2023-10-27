package com.example.news;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/news")
public class NewsAPIController {
    final NewsDAO dao;

    @Autowired
    public NewsAPIController(NewsDAO dao) {
        this.dao = dao;
    }

    // 뉴스 목록보기
    @GetMapping
    public List<News> list() {
        List<News> newsList = new ArrayList<>();
        try {
            newsList = dao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("뉴스 목록 가져오기 문제 발생!");
            //error 객체 추가

        }
        return newsList;
    }

    // 뉴스 상세보기
    @GetMapping("{aid}")
    public News getNews(@PathVariable("aid") int aid) {
        News news = null;

        try {
            news = dao.getNews(aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    // 뉴스 등록
     @PostMapping
    public String addNews(@RequestBody News news) {
         try {
             dao.addNews(news);
         } catch (SQLException e) {
             e.printStackTrace();
             return "News API: 뉴스 등록 실패!!";
         }
         return "News API: 뉴스 등록됨!!";
     }

     // 뉴스 삭제
    @DeleteMapping("{aid}")
    public String delNews(@PathVariable("aid") int aid) {
        try {
            dao.delNews(aid);
        } catch (SQLException e) {
            e.printStackTrace();
            return "News API: 뉴스 삭제 실패!!" + aid;
        }
        return "News API: 뉴스 삭제 성공!!" + aid;
    }

}
