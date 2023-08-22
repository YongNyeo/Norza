package com.example.norza.controller;


import com.example.norza.config.Login;
import com.example.norza.domain.FreeBoard;
import com.example.norza.domain.SessionUser;
import com.example.norza.dto.FreeBoardSaveDto;
import com.example.norza.service.board.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;


@Controller
@RequestMapping("/freeboard")
@RequiredArgsConstructor
public class FreeBoardController { //자유게시판과 공지사항만 관리
    private final FreeBoardService freeBoardService;

    //리스트 불러오기 이상 무
    @GetMapping("")
    public String showFreeBoardList(Model model) {
        model.addAttribute("freeBoardList", freeBoardService.findAll());
        return "board/freeboard.html";
    }

    //게시글 등록 새 폼 불러오기 이상 무
    @GetMapping("save")
    public String showFreeBoardForm(){
        return "board/freeboard_save_form.html";
    }

    //게시글 등록 POST
    //세션있을때만 등록 가능
    @PostMapping("save")
    public String saveFreeBoard(@Login SessionUser sessionUser,@Valid @ModelAttribute FreeBoardSaveDto board) {

        freeBoardService.save(getFreeBoard(board), sessionUser);
        return "redirect:/freeboard";
    }
    //게시글 보기
    @GetMapping("{id}")
    public String showFreeBoardOne(@PathVariable long id, Model model) {
        model.addAttribute("freeBoard",freeBoardService.findById(id));
        return "board/freeboard_one.html";
    }

    /** 게시글 수정 폼 불러오기
     * 저장 name과 세션name이 같은지 비교해서 수정 버튼 보이게 설정
     */
    @GetMapping("{id}/update")
    public String updateFreeBoard(@PathVariable long id,Model model){
        model.addAttribute("freeBoard",freeBoardService.findById(id));
        return "board/freeboard_update_form.html";
    }

    //게시글 수정 POST
    @PostMapping("{id}/update")
    public String updateFreeBoard(@PathVariable long id, @Valid @ModelAttribute FreeBoardSaveDto board,
                                  RedirectAttributes redirectAttributes){
        freeBoardService.update(id,board);
        redirectAttributes.addAttribute("id",id);
        return "redirect:/freeboard/{id}";
    }

    @Transactional //delete
    @DeleteMapping("{id}")
    public String deleteFreeBoard(@PathVariable long id){
        freeBoardService.delete(id);
        return "redirect:/freeboard";
    }

    //저장,수정시 이용하는 Dto -> Entity
    private FreeBoard getFreeBoard(FreeBoardSaveDto board) {
        FreeBoard freeBoard = new FreeBoard();
        freeBoard.DtoToFreeBoard(board);
        return freeBoard;
    }
}
