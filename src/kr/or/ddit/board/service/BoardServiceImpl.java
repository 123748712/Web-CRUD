package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private BoardDaoImpl dao;
	private static BoardServiceImpl service;
	
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getDao();
	}
	public static BoardServiceImpl getService() {
		if(service == null) {
			service = new BoardServiceImpl();
		}
		return service;
	}
	
	@Override
	public int countList() {
		int cnt = 0;
		try {
			cnt = dao.countList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public List<BoardVO> boardList(Map<String, Integer> map) {
		List<BoardVO> list = null;
		try {
			list = dao.boardList(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
