package yjc.wdb.somebodyplace;

import java.util.List;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import yjc.wdb.somebodyplace.service.IssueService;
import yjc.wdb.somebodyplace.service.MemberService;
import yjc.wdb.somebodyplace.service.PlaceService;
import yjc.wdb.somebodyplace.service.ProductService;
import yjc.wdb.somebodyplace.bean.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	private ProductService productservice;
	@Inject
	private PlaceService placeservice;
	@Inject
	private IssueService issueservice;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	   @RequestMapping(value="main", method=RequestMethod.GET)
	   public String main(Model model, @RequestParam(defaultValue="0") int dcate_code) throws Exception{
	      // 메인 실시간 이슈
		   
		   //테스트 : 복현 2동의 위도 경도를 넣습니다.

		   MemberController.lat = 35.895406;
		   MemberController.lng = 128.625386;
		   
		   model.addAttribute("lat", MemberController.lat);
		   model.addAttribute("lng", MemberController.lng);
		   
		   List<Issue> issueList = issueservice.mainIssue(MemberController.lat, MemberController.lng);
		   
		   model.addAttribute("mainIssue", issueList);
		   
	      if(dcate_code == 0) {
	      
	         List<Product> list = productservice.selectAllProduct();//광민
	         model.addAttribute("Product", list);   
	      }
	      else if(dcate_code==1||dcate_code==2||dcate_code==3){
	    	  
	    	 List<Place> list = placeservice.MainPlacelist(dcate_code);
	    	 System.out.print(list);
	    	 model.addAttribute("Place", list);  
	         System.out.print("플레이스출력");
	         
	      }
	      else if(dcate_code==7||dcate_code==8){
	    	  
	    	  List<Product> list =productservice.selectProductByDcate(dcate_code);//광민
		         model.addAttribute("BigProduct", list); 
		         System.out.print("큰상품출력");
		         
		      }
	      else {
	         List<Product> list =productservice.selectProductByDcate(dcate_code);//광민
	         model.addAttribute("Product", list);
	      }
	      
	      //옆에 떠다니는 메뉴를 표시하기 위한 변수 넘기기
	      model.addAttribute("isProduct", "true");

	      model.addAttribute("cont", "main.jsp");
	     
	      // 복현동의 위도, 경도만 일단 가져와서 놔보자.
	      
	      
	      IssueController.calDistance(dcate_code, dcate_code, dcate_code, dcate_code);
	      
	      return "index";
	   }
	   

	@RequestMapping(value="chat", method=RequestMethod.GET)
	public String chat(Model model, HttpServletRequest req) {
        try {
        	
        	int sender = Integer.parseInt(req.getParameter("sender").trim());
        	
        	int receiver = Integer.parseInt(req.getParameter("receiver"));
        	
    		if(req.getParameter("requestbyapp").length()!=0) { //앱에서 누르면
    			model.addAttribute("requestbyapp", "success");
    			model.addAttribute("sender", sender);
    			model.addAttribute("receiver", receiver);
    		}       	
        } catch(NullPointerException ne) {
        	
        }

		return "chatting";
	}
	

	
	
	
	
	   @RequestMapping(value="jusoPopup")   
	   public String jusoPopup(){
	      // return �ϸ� view �������� forwarding �ϴ°�
	      return "/issue/jusoPopup";      
	   }
	   
	   @RequestMapping(value="memberjusoPopup")   
	   public String memberjusoPopup(){
	      // return �ϸ� view �������� forwarding �ϴ°�
	      return "/member/jusoPopup";      
	   }
	   
	   @RequestMapping(value="test_main")   
	   public String test_main(Model model){
		      model.addAttribute("cont", "test_main2.jsp");
		      return "index"; 
	   }
}
