package yjc.wdb.somebodyplace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import yjc.wdb.somebodyplace.bean.Criteria;
import yjc.wdb.somebodyplace.bean.PageMaker;
import yjc.wdb.somebodyplace.bean.Review;
import yjc.wdb.somebodyplace.service.ReviewService;

@RestController
public class ReviewController {

	@Inject
	private ReviewService reviewservice;
	
	@RequestMapping(value="addReview", method=RequestMethod.POST)
	public ResponseEntity<String> addReview(@RequestBody Review review) {
		
		ResponseEntity<String> entity = null;

		try {
			reviewservice.addReview(review);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="getReviewList/{post_code}", method=RequestMethod.GET)
	public ResponseEntity<List<Review>> getReviewList(@PathVariable("post_code") int post_code) {
		
		ResponseEntity<List<Review>> entity = null;
		
		try{
			entity = new ResponseEntity<>(reviewservice.getReviewList(post_code), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="modifyReview/{review_code}", method={RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> modifyReview(@PathVariable("review_code") int review_code, HttpServletRequest req) {
		ResponseEntity<String> entity = null;
		
		Review review = new Review();
		try {
			reviewservice.modifyReview(review);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="deleteReview/{review_code}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteReview(@PathVariable("revie_code") int review_code, HttpServletRequest req) {
		
		Review review = new Review();
		ResponseEntity<String> entity = null;
		try {
			reviewservice.deleteReview(review);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="getReviewList/{post_code}/{page}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listReviewPage(@PathVariable("post_code") int post_code, @PathVariable("page") int page) {
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageMaker pageMaker = new PageMaker();
			
			pageMaker.setCri(cri);
			
			Map<String, Object> map = new HashMap<String, Object>();
			List<Review> list = reviewservice.listReviewPage(post_code, cri);
			
			map.put("list", list);
			
			int reviewCount = reviewservice.countReview(post_code);
			pageMaker.setTotalCount(reviewCount);
			
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}