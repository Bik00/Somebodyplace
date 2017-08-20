package yjc.wdb.somebodyplace.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.somebodyplace.bean.Timeline;

@Repository
public class TimelineDAOImpl implements TimelineDAO {

	@Inject
	private SqlSession sql;
	private static final String NAMESPACE = "yjc.wdb.somebodyplace.TimelineMapper";
	
	@Override
	public void addTimeline(Timeline timeline) {
		// TODO Auto-generated method stub
		
		sql.update(NAMESPACE+".addTimeline", timeline);
	}

	@Override
	public List<Timeline> readTimelineByProduct(Timeline timeline) {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".readTimelineByProduct", timeline);
	}

	@Override
	public List<Timeline> readMyAllTimelines(int member_code) {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".readMyAllTimelines", member_code);
	}
}
