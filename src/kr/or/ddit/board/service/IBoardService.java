package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {
	
	// 전체 글 갯수 가져오기
	public int countList();
	
	// 게시글 조회
	public List<BoardVO> boardList(Map<String, Integer> map);
}
