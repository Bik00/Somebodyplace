package yjc.wdb.somebodyplace.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.somebodyplace.bean.Board;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sql;
	private static final String NAMESPACE = "yjc.wdb.somebodyplace.BoardMapper";
	
	@Override
	public void insertInit(Board board) throws Exception {
		sql.insert(NAMESPACE + ".insertInit", board);
	}

	@Override
	public List<Board> selectBoardList(int place_code) throws Exception {
		return sql.selectList(NAMESPACE + ".selectBoardList", place_code);
	}

	@Override
	public void insertBoard(Board board) throws Exception {
		sql.insert(NAMESPACE + ".insertBoard", board);
	}

	@Override
	public void updateBoard(Board board) throws Exception {
		sql.update(NAMESPACE + ".updateBoard", board);
	}

	@Override
	public void deleteBoard(int del_code) throws Exception {
		sql.delete(NAMESPACE + ".deleteBoard", del_code);
	}

	@Override
	public void writeBoardContent(int board_code, String board_content) {
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		
		map.put("board_code", board_code);
		map.put("board_content", board_content);
		sql.update(NAMESPACE+".writeBoardContent", map);
	}

}
