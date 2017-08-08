package yjc.wdb.somebodyplace.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.somebodyplace.bean.Criteria;
import yjc.wdb.somebodyplace.bean.Review;
import yjc.wdb.somebodyplace.dao.ReviewDAO;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Inject
	private ReviewDAO dao;
	
	@Override
	public List<Review> getReviewList(int post_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.getReviewList(post_code);
	}

	@Override
	public void addReview(Review review) throws Exception {
		// TODO Auto-generated method stub
		dao.addReview(review);
	}

	@Override
	public void modifyReview(Review review) throws Exception {
		// TODO Auto-generated method stub
		dao.modifyReview(review);
	}

	@Override
	public void deleteReview(Review review) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteReview(review);
	}

	@Override
	public List<Review> listReviewPage(int post_code, Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listReviewPage(post_code, cri);
	}

	@Override
	public int countReview(int post_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.countReview(post_code);
	}
}
