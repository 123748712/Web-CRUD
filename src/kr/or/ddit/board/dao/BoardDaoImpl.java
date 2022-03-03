package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.ibatis.config.BuildedSqlMapClient;

public class BoardDaoImpl implements IBoardDao {
	private SqlMapClient smc;
	private static BoardDaoImpl dao;
	
	private BoardDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}

	public static BoardDaoImpl getDao() {
		if(dao == null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}
	@Override
	public int countList() throws SQLException {
		return (int) smc.queryForObject("board.countList");
	}

	@Override
	public List<BoardVO> boardList(Map<String, Integer> map) throws SQLException {
		return smc.queryForList("board.boardList", map);
	}

	@Override
	public void insertBoard(BoardVO vo) throws SQLException {
		smc.insert("board.insertBoard", vo);
	}
}
