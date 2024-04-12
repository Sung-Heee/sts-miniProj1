package org.kosa.mini.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kosa.mini.code.CodeService;
import org.kosa.mini.entity.MemberVO;
import org.kosa.mini.page.PageRequestVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final CodeService codeService;

    @RequestMapping("list")
    public String list(@Valid PageRequestVO pageRequestVO, BindingResult bindingResult, Model model) {
        log.info("사용자 목록");

        log.info(pageRequestVO.toString());

        if(bindingResult.hasErrors()) {
            pageRequestVO = PageRequestVO.builder().build();
        }

        model.addAttribute("pageResponseVO", memberService.getList(pageRequestVO));
        model.addAttribute("sizes", codeService.getList());

        return "member/list";
    }

    @RequestMapping("loginForm")
    public String loginForm() {
        log.info("로그인 화면");

        return "member/loginForm";
    }

    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody MemberVO memberVO, HttpSession session) {
        MemberVO loginVO = memberService.login(memberVO);

        Map<String, Object> map = new HashMap<>();

        if (loginVO != null) {
            session.setAttribute("loginVO", loginVO);
            map.put("loginVO", loginVO);
            map.put("status", 0);
        } else  {
            map.put("status", -99);
            map.put("statusMessage", "아이디 또는 비밀번호가 잘못 되었습니다.");
        }
        return map;
    }
}
