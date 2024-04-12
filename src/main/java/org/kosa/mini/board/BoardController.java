package org.kosa.mini.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kosa.mini.code.CodeService;
import org.kosa.mini.entity.BoardVO;
import org.kosa.mini.entity.MemberVO;
import org.kosa.mini.page.PageRequestVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CodeService codeService;

    // pageNO => 1 이어야하는데 만약 음수면 pageRequestVO, bindingResult 여기서 처리
    // size : 10 ~ 100 이때 만약 -10이라고 주면 pageRequestVO 이걸 가지고 bindingResult 여기서 에러 값 설정이 돼서 다시 정리해줌 (pageRequestVO = PageRequestVO.builder().build();)
    @RequestMapping("list")
    // 값에 문제가 있으면 bindingResult에 에러에 관한 걸 설정
    public String list(@Valid PageRequestVO pageRequestVO, BindingResult bindingResult, Model model) throws ServletException, IOException {
        log.info("게시물 목록");

        log.info(pageRequestVO.toString());

        if(bindingResult.hasErrors()){
            pageRequestVO = PageRequestVO.builder().build();
        }

        model.addAttribute("pageResponseVO", boardService.getList(pageRequestVO));
        model.addAttribute("sizes" , codeService.getList());

        return "board/list";
    }

    @RequestMapping("detail")
    public String detail(BoardVO board, Model model) throws ServletException, IOException {
        log.info("상세보기");

        model.addAttribute("board", boardService.detail(board));

        return "board/detail";
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestBody BoardVO board) {
        log.info("삭제 -> {}", board);

        int updated = boardService.delete(board);

        Map<String, Object> map = new HashMap<>();

        if (updated == 1) {
            map.put("status", 0);
            map.put("statusMessage", "게시물이 삭제되었습니다.");
        } else {
            map.put("status", -99);
            map.put("statusMessage", "게시물 삭제 실패하였습니다.");
        }

        return map;
    }

    @RequestMapping("updateForm")
    public String updateForm(BoardVO board, Model model) {
        model.addAttribute("board", boardService.updateForm(board));

        return "board/updateForm";
    }

    @RequestMapping("update")
    @ResponseBody
    public Map<String ,Object> update(@RequestBody BoardVO board) {
        log.info("수정 -> {}", board);

        int updated = boardService.update(board);

        Map<String, Object> map = new HashMap<>();

        if (updated == 1) {
            map.put("status", 0);
            map.put("statusMessage", "게시물이 수정되었습니다.");
        } else {
            map.put("status", -99);
            map.put("statusMessage", "게시물 수정 실패하였습니다.");
        }

        return map;

    }

    @RequestMapping("insertForm")
    public String insertForm(BoardVO board, Model model) {

        return "board/insertForm";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Map<String , Object> insert(@RequestBody BoardVO board, HttpSession session) {
        log.info("등록 -> {}", board);

        Map<String, Object> map = new HashMap<>();

        MemberVO loginVO = (MemberVO) session.getAttribute("loginVO");

        if (loginVO != null) {
            board.setMember_id(loginVO.getMember_id());
            int updated = boardService.insert(board);
            if (updated == 1) {
                map.put("status", 0);
                map.put("statusMessage", "게시물이 등록되었습니다.");
            } else {
                map.put("status", -99);
                map.put("statusMessage", "게시물 등록 실패하였습니다.");
            }
        } else {
            map.put("status", -98);
            map.put("statusMessage", "로그인 정보가 존재하지 않습니다");
        }

        return map;
    }
}
