package yjc.wdb.somebodyplace.dao;

import java.util.List;

import yjc.wdb.somebodyplace.bean.Timeline;

public interface TimelineDAO {

	void addTimeline(Timeline timeline);

	List<Timeline> readTimelineByProduct(Timeline timeline);

	List<Timeline> readMyAllTimelines(int member_code);

}