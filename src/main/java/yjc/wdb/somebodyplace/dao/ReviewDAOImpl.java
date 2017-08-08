package yjc.wdb.somebodyplace.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.somebodyplace.bean.Criteria;
import yjc.wdb.somebodyplace.bean.Review;

@Repository
public class ReviewDAOImpl implements ReviewDAO {

	@Inject
	private SqlSession session;
	
	private static String NAMESPACE = "yjc.wdb.somebodyplace.ReviewMapper";
	
	@Override
	public List<Review> getReviewList(int post_code) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(NAMESPACE+".getReviewList", post_code);
	}

	@Override
	public void addReview(Review review) throws Exception {
		// TODO Auto-generated method stub
		session.insert(NAMESPACE+".addReview", review);
	}

	@Override
	public void modifyReview(Review review) throws Exception {
		// TODO Auto-generated method stub
		session.update(NAMESPACE+".modifyReview", review);
	}

	@Override
	public void deleteReview(Review review) throws Exception {
		// TODO Auto-generated method stub
		session.delete(NAMESPACE+".deleteReview", review);
	}

	@Override
	public List<Review> listReviewPage(int post_code, Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("post_code",  post_code);
		paramMap.put("cri", cri);
		
		return session.selectList(NAMESPACE+".listReviewPage", paramMap);
	}

	@Override
	public int countReview(int post_code) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE+".countReview", post_code);
	}

}
