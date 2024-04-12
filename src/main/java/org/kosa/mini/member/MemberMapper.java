package org.kosa.mini.member;

import org.kosa.mini.entity.MemberVO;
import org.kosa.mini.page.PageRequestVO;

import java.util.List;

public interface MemberMapper {
	List<MemberVO> getList(PageRequestVO pageRequestVO);
	int getTotalCount(PageRequestVO pageRequestVO);
	MemberVO login(MemberVO memberVO);
	MemberVO detail(MemberVO memberVO);
	int delete(MemberVO memberVO);
	int update(MemberVO memberVO);


}
