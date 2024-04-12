package org.kosa.mini.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kosa.mini.entity.BoardVO;
import org.kosa.mini.page.PageRequestVO;
import org.kosa.mini.page.PageResponseVO;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public PageResponseVO<BoardVO> getList(PageRequestVO pageRequestVO) {
        List<BoardVO> list = boardMapper.getList(pageRequestVO);
        int total = boardMapper.getTotalCount(pageRequestVO);

        log.info("list {} ", list);
        log.info("total  = {} ", total);

        PageResponseVO<BoardVO> pageResponseVO = PageResponseVO.<BoardVO>withAll()
                .list(list)
                .total(total)
                .size(pageRequestVO.getSize())
                .pageNo(pageRequestVO.getPageNo())
                .build();

        return pageResponseVO;
    }

    public BoardVO detail(BoardVO board) throws ServletException, IOException {
        //view Count의 값을 증가한다.
        //만약 값을 증가 하지 못하면 게시물이 존재하지 않는 경우임
        if (0 == boardMapper.incViewCount(board)) {
            return null;
        }
        //view Count의 값이 증가된 객체를 얻는다
        BoardVO resultVO = boardMapper.detail(board);
        log.info(resultVO.getView_count());
        log.info(resultVO.toString());
        return resultVO;
    }

    public int delete(BoardVO board) {
        return boardMapper.delete(board);
    }

    public int update(BoardVO board) {
        return boardMapper.update(board);
    }

    public BoardVO updateForm(BoardVO board) {
        return boardMapper.detail(board);
    }

    public int insert(BoardVO board) {
        return boardMapper.insert(board);
    }
}
