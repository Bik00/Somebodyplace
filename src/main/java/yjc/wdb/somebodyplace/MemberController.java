package yjc.wdb.somebodyplace;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import yjc.wdb.somebodyplace.bean.Issue;
import yjc.wdb.somebodyplace.bean.Member;
import yjc.wdb.somebodyplace.bean.Product;
import yjc.wdb.somebodyplace.service.MemberService;
import yjc.wdb.somebodyplace.service.PlaceService;
import yjc.wdb.somebodyplace.service.ProductService;

@SessionAttributes({"member_code", "member_email"})
@Controller
public class MemberController {
	
		@Inject
		private MemberService service;
		@Inject
		private ProductService productservice;
		@Inject
		private PlaceService placeservice;
		
		/* 무조건 자기자신 */
		public static int member_code;
		
		@RequestMapping(value="loginForm", method=RequestMethod.GET)
		public String loginForm(Model model){
			model.addAttribute("cont", "member/loginForm.jsp");
			return "index";
		}
		
		@RequestMapping(value="join", method=RequestMethod.GET)
		public String join(Model model, Member member, RedirectAttributes rttr,
				String member_email) throws Exception{
			model.addAttribute("cont", "member/joinForm.jsp");
			return "index";
		}
		
		@RequestMapping(value="join", method=RequestMethod.POST)	
		public String boardFormPost(Member member,  HttpServletRequest req, 
				RedirectAttributes rttr, Model model) throws Exception{
			String email = req.getParameter("email");
			String pw = req.getParameter("pw");
			String nickname = req.getParameter("nickname");
			String birthDate = req.getParameter("birth");
			String gender = req.getParameter("gender");
			String age = req.getParameter("age");
			String profile = req.getParameter("member_profile");
			int age2=Integer.parseInt(age);

			double lat = Double.parseDouble(req.getParameter("lat"));
			double lng = Double.parseDouble(req.getParameter("lng"));

			member.setMember_email(email);
			member.setMember_pw(pw);
			member.setMember_nickname(nickname);
			member.setMember_birth(birthDate);
			member.setMember_lng(lng);	
			member.setMember_lat(lat);
			member.setMember_gender(gender);
			member.setMember_age(age2);
			member.setMember_profile(profile);
	
			service.regist(member);
			rttr.addFlashAttribute("result", "SUCCESS!!");
			model.addAttribute("cont", "main.jsp");
			return "index";
		}
	
	   @RequestMapping(value="login")
	   public String login( Member member, HttpServletRequest req, Model model,HttpSession session) throws Exception{
		   	
		   	System.out.println("접속 성공");
		   
	  	    member.setMember_email(req.getParameter("email"));
	  	    member.setMember_pw(req.getParameter("pw"));	      	    
	  	    List<Member> x = service.login(member);
	  	    String applogin = req.getParameter("applogin");
	  	    
	  	    if(x.size() != 0){
	  	    	
	  	    	session.setAttribute("nickname", x.get(0).getMember_nickname());
	  	    	session.setAttribute("code", x.get(0).getMember_code());
	  	    	
	  	     	session.setAttribute("member_nickname", x.get(0).getMember_nickname());
	  	    	session.setAttribute("member_code", x.get(0).getMember_code());
		    	
	  	    	// scpark
	  	    	model.addAttribute("member_code", x.get(0).getMember_code());
	  	    	model.addAttribute("member_email", x.get(0).getMember_email());
	  	    	
	  	    	session.setAttribute("member_email", x.get(0).getMember_email());
	  	    	member_code = x.get(0).getMember_code();
	  	    	PlaceController.memberEmail = x.get(0).getMember_email();
	  	    	//로그인시 메인 상품 출력 
	  	        List<Product> list = productservice.selectAllProduct();//광민
	            model.addAttribute("Product", list); 
	  	    	
				if (applogin != null) {
					session.setAttribute("applogin", "success");
					return "main";
				} else {
					model.addAttribute("cont", "main.jsp");
					return "index";
				}
	  	    }else {
	  	    	session.invalidate();
	  	    	
	  	    	model.addAttribute("cont", "member/loginForm.jsp");
				return "index";	
	  	    }      
	   }
	   
	@ResponseBody
	@RequestMapping(value = "mobileLogin", method = RequestMethod.GET)
	public void mobileLogin(HttpServletRequest req, HttpSession session, HttpServletResponse res, Member member,
			Model model) throws Exception {
		
		System.out.println("접속 성공");
		String callback = req.getParameter("callback");
		HashMap<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		String result = null;

		req.setCharacterEncoding("utf-8");
		String email = req.getParameter("member_email");
		String pw = req.getParameter("member_pw");

		member.setMember_email(email);
		member.setMember_pw(pw);

		session = req.getSession();

		List<Member> x = service.login(member);

		if (x.size() == 1) {
			map.put("code", Integer.toString(x.get(0).getMember_code()));
			map.put("nickname", x.get(0).getMember_nickname());
			map.put("result", "success");
			session.setAttribute("code", x.get(0).getMember_code());
			session.setAttribute("nickname", x.get(0).getMember_nickname());
		} else {
			// 디비에 값이 없을 경우
			map.put("result", "fail");
		}

		try {
			result = mapper.writeValueAsString(map);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(callback + "(" + result + ")");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.write(callback + "(" + result + ")");

	}
	   
		   
		@RequestMapping(value="logout")
		public String logout(Model model,HttpSession session){
			
			session.invalidate();
			member_code = 0;
			PlaceController.Cmember_code = 0;
					
			model.addAttribute("cont", "main.jsp");
			return "index";
		}
		
		
		@RequestMapping(value="updateinterest", method=RequestMethod.POST)
		public String interestSetting(Model model, HttpServletRequest req) throws Exception{
			String member_interest = req.getParameter("member_interest");
			int member_code = MemberController.member_code;
			
			Member member = new Member();
			
			member.setMember_interest(member_interest);
			member.setMember_code(member_code);
			
			service.interestupdate(member);
			member_interest = service.getMemberInterest(member_code);
			System.out.println(member_interest);
			
			model.addAttribute("member_interest", member_interest);
			model.addAttribute("cont", "mypage/interestSetting.jsp");
			return "index";
		}	
}
