package org.kosa.mini.board;

import org.kosa.mini.entity.BoardVO;
import org.kosa.mini.page.PageRequestVO;

import java.util.List;

public interface BoardMapper {

//	List<BoardVO> list(BoardVO boardVO);
	List<BoardVO> getList(PageRequestVO pageRequestVO);
	int  getTotalCount(PageRequestVO pageRequestVO);
	BoardVO detail(BoardVO boardVO);
	int delete(BoardVO boardVO);
	int update(BoardVO boardVO);

	int insert(BoardVO boardVO);
	int incViewCount(BoardVO board);


}
