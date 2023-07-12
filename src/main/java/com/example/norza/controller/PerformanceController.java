package com.example.norza.controller;

import com.example.norza.domain.Performance;
import com.example.norza.domain.PerformanceComment;
import com.example.norza.domain.SessionUser;
import com.example.norza.service.PerformanceCommentService;
import com.example.norza.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("performance")
@RequiredArgsConstructor
public class PerformanceController {
    private final PerformanceService performanceService;
    private final PerformanceCommentService commentService;

    @GetMapping("")
    public String showPerformanceList(@SessionAttribute(name = "sessionUser", required = false) SessionUser sessionUser,
                                      Model model) {
        model.addAttribute(performanceService.findAll());
        if (sessionUser != null) {
            model.addAttribute("sessionUser", sessionUser);
        }
        return "/performance/performance_list.html";
    }
    @GetMapping("/page")
    public String paging(Model model) {
        model.addAttribute(performanceService.page());
        return "/performance/page.html";
    }

    @GetMapping("{id}")
    public String showPerformanceOne(@SessionAttribute(name = "sessionUser", required = false) SessionUser sessionUser, @PathVariable long id, Model model) {
        //번호에 대한 검증 필요없음. 이건 검색이 아니라 리스트에서  클릭해서 들어가기때문에
        Map<String, String> map = performanceService.getMapById(id);
        model.addAttribute(performanceService.findById(id));
//        model.addAttribute("sessionUser", sessionUser);
        if (sessionUser != null) {
            model.addAttribute("sessionUser", sessionUser);
        }
        model.addAttribute("map", map);
        return "/performance/performance_one.html";
    }

    @GetMapping("{id}/search")
    public String searchPerformanceOne(@SessionAttribute(name = "sessionUser", required = false) SessionUser sessionUser,
                                             @PathVariable long id, Model model) throws IOException {
        Performance performance = performanceService.findById(id);
        model.addAttribute(performance);
        if (sessionUser != null) {
            model.addAttribute("sessionUser", sessionUser);
        }
        model.addAttribute(performanceService.jsonToList(performance.getName()));
        return "/performance/performance_search.html";
    }

    @GetMapping("{id}/comment") //댓글남기기 메뉴를 누르면 댓글 화면을 처음에만 보여주기위한 URI
    public String showPerformanceComment(@SessionAttribute(name = "sessionUser", required = false) SessionUser sessionUser, @PathVariable long id, Model model) {
        Performance performance = performanceService.findById(id);
        model.addAttribute(performance);
        if (sessionUser != null) {
            model.addAttribute("sessionUser", sessionUser);
        }

        return "/performance/performance_comment.html";
    }

    @ResponseBody//저장하고나서는 저장 잘됐다고 날려주는 json
    @PostMapping("{id}/comment")
    public PerformanceComment savePerformanceComment(@SessionAttribute(name = "sessionUser", required = false) SessionUser sessionUser, @PathVariable long id,
                                                     @RequestBody PerformanceComment params, Model model) {
        Long key = commentService.save(params, id);
        PerformanceComment s = commentService.findCommentById(key);
        model.addAttribute("sessionUser", sessionUser);


        return s;
    }

    @ResponseBody
    @GetMapping("{id}/comment/list") // 비동기로 계속해서 이 api를 요청할 예정임
    public List<PerformanceComment> ShowPerformanceCommentList(@SessionAttribute(name = "sessionUser", required = false) SessionUser sessionUser, @PathVariable long id, Model model) {
        List<PerformanceComment> list = commentService.findAllById(id);
        if (sessionUser != null) {
            model.addAttribute("sessionUser", sessionUser);
        }
        return list;
    }

    @Transactional //delete의 경우 트랜잭션 걸어주기
    @ResponseBody
    @DeleteMapping("/{id}/comment/{commentId}")
    public long deletePerformanceComment(@SessionAttribute(name = "sessionUser", required = false) SessionUser sessionUser, @PathVariable long id,
                                         @PathVariable long commentId, Model model) {
        if (sessionUser != null) {
            model.addAttribute("sessionUser", sessionUser);
        }
        return commentService.delete(commentId);
    }

}
