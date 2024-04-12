package org.kosa.mini.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kosa.mini.entity.MemberVO;
import org.kosa.mini.page.PageRequestVO;
import org.kosa.mini.page.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    public PageResponseVO<MemberVO> getList(PageRequestVO pageRequestVO) {
        List<MemberVO> list = memberMapper.getList(pageRequestVO);
        int total = memberMapper.getTotalCount(pageRequestVO);

        log.info("list => {}", list);
        log.info("total => {}", total);

        PageResponseVO<MemberVO> pageResponseVO = PageResponseVO.<MemberVO>withAll()
                .list(list)
                .total(total)
                .size(pageRequestVO.getSize())
                .pageNo(pageRequestVO.getPageNo())
                .build();

        return pageResponseVO;
    }

    public MemberVO login(MemberVO memberVO) {
        MemberVO resultVO = memberMapper.login(memberVO);
        if (resultVO != null && memberVO.isEqualsPwd(resultVO.getUser_password())) {
            return resultVO;
        }
        return null;
    }


}
