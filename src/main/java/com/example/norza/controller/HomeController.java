package com.example.norza.controller;

import com.example.norza.service.FestivalService;
import com.example.norza.service.NewsService;
import com.example.norza.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final NewsService newsService;
    private final PerformanceService performanceService;
    private final FestivalService festivalService;

    @GetMapping("/")
    public String home(Model model) throws IOException {
        model.addAttribute("newsList", newsService.CrawlingNews());
        return "home.html";
    }

    @PostMapping("/search")
    public String search(@Valid @RequestParam String selection,@Valid @RequestParam String content, Model model) {
        model.addAttribute("festivalList",festivalService.searchList(selection, content));
        model.addAttribute("performanceList",performanceService.searchList(selection, content));
        return "search.html";
    }
}
