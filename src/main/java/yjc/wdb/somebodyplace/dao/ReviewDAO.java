package yjc.wdb.somebodyplace.dao;

import java.util.List;

import yjc.wdb.somebodyplace.bean.Criteria;
import yjc.wdb.somebodyplace.bean.Review;

public interface ReviewDAO {
	public List<Review> getReviewList(int post_code) throws Exception;
	public void addReview(Review review) throws Exception;
	public void modifyReview(Review review) throws Exception;
	public void deleteReview(Review review) throws Exception;
	public List<Review> listReviewPage(int post_code, Criteria cri) throws Exception;
	public int countReview(int post_code) throws Exception;
}
