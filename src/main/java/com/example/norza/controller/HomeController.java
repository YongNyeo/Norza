package com.example.norza.controller;

import com.example.norza.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final NewsService newsService;

    @GetMapping("/")
    public String home(HttpSession session, Model model) throws IOException {
        model.addAttribute("sessionUser", session.getAttribute("sessionUser"));
        model.addAttribute("newsList",newsService.CrawlingNews());
        return "home.html";
    }
}
