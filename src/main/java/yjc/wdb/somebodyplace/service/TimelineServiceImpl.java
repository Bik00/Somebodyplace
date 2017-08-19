package yjc.wdb.somebodyplace.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import yjc.wdb.somebodyplace.bean.Timeline;
import yjc.wdb.somebodyplace.dao.TimelineDAO;

@Service
public class TimelineServiceImpl implements TimelineService {

	@Inject
	private TimelineDAO dao;
	
	@Override
	public void addTimeline(Timeline timeline) {
		// TODO Auto-generated method stub
		dao.addTimeline(timeline);
	}

}