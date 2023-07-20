package com.example.norza.controller;

import com.example.norza.service.FestivalCommentService;
import com.example.norza.service.FestivalService;
import com.example.norza.domain.Festival;
import com.example.norza.domain.FestivalComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("festival")
@RequiredArgsConstructor
public class FestivalController {
    private final FestivalService festivalService;
    private final FestivalCommentService commentService;

    @GetMapping("")
    public String showFestivalList(Model model) {
        model.addAttribute(festivalService.findAll());

        return "/festival/festival_list.html";
    }

    @GetMapping("{id}")
    public String showFestivalOne(@PathVariable long id, Model model) {
        //번호에 대한 검증 필요없음. 이건 검색이 아니라 리스트에서  클릭해서 들어가기때문에
        Map<String, String> map = festivalService.getMapById(id);
        model.addAttribute(festivalService.findById(id));
        model.addAttribute("map", map);
        return "/festival/festival_one.html";
    }

    @GetMapping("{id}/search")
    public String searchFestivalOne(@PathVariable long id, Model model) throws IOException {
        Festival festival = festivalService.findById(id);
        model.addAttribute(festival);
        model.addAttribute(festivalService.jsonToList(festival.getName()));
        return "/festival/festival_search.html";
    }

    @GetMapping("{id}/comment") //댓글남기기 메뉴를 누르면 댓글 화면을 처음에만 보여주기위한 URI
    public String showFestivalComment(@PathVariable long id, Model model) {
        Festival festival = festivalService.findById(id);
        model.addAttribute(festival);
        return "/festival/festival_comment.html";
    }

    @ResponseBody//저장하고나서는 저장 잘됐다고 날려주는 json
    @PostMapping("{id}/comment")
    public FestivalComment saveFestivalComment(@PathVariable long id,
                                               @RequestBody FestivalComment params, Model model) {
        Long key = commentService.save(params, id);
        FestivalComment s = commentService.findCommentById(key);
        return s;
    }

    @ResponseBody
    @GetMapping("{id}/comment/list") // 비동기로 계속해서 이 api에 요청할 예정임
    public List<FestivalComment> ShowFestivalCommentList(@PathVariable long id,Model model) {
        List<FestivalComment> list = commentService.findAllById(id);
        return list;
    }

    @Transactional //delete의 경우 트랜잭션 걸어주기 ->왜? Transaction공부하기
    @ResponseBody
    @DeleteMapping("/{id}/comment/{commentId}")
    public long deleteFestivalComment(@PathVariable long id, @PathVariable long commentId,Model model) {
        return commentService.delete(commentId);
    }

}
