package yjc.wdb.somebodyplace.service;

import java.util.List;

import yjc.wdb.somebodyplace.bean.Timeline;

public interface TimelineService {

	void addTimeline(Timeline timeline);

	List<Timeline> readTimelineByProduct(Timeline timeline);

	List<Timeline> readMyAllTimelines(int member_code);

}