package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {

	// 전체 글 갯수 가져오기
	public int countList() throws SQLException;
	
	// 게시글 조회
	public List<BoardVO> boardList(Map<String, Integer> map) throws SQLException;
	
	public void insertBoard(BoardVO vo) throws SQLException;
}
