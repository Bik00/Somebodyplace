package yjc.wdb.somebodyplace;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import yjc.wdb.somebodyplace.bean.Issue;
import yjc.wdb.somebodyplace.bean.Member;
import yjc.wdb.somebodyplace.bean.Product;
import yjc.wdb.somebodyplace.service.IssueService;
import yjc.wdb.somebodyplace.service.MemberService;
import yjc.wdb.somebodyplace.service.PlaceService;
import yjc.wdb.somebodyplace.service.ProductService;
import yjc.wdb.somebodyplace.util.MediaUtils;


@SessionAttributes({"member_code", "member_email"})
@Controller
public class MemberController {
	
		@Inject
		private MemberService service;
		@Inject
		private ProductService productservice;
		@Inject
		private PlaceService placeservice;
		@Inject
		private IssueService issueservice;
		
		/* 무조건 자기자신 */
		public static int member_code;
		public static double lat;
		public static double lng;
		
		public static int oriWidth;
		public static int oriHeight;
		
		@Resource(name="uploadPath")
		private String uploadPath;
		
		@Resource(name="joinFormPath")
		private String joinFormPath;
		
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
		
		@Autowired
		BCryptPasswordEncoder passwordEncoder;
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
			
			
			
			
			member.setMember_pw(passwordEncoder.encode(pw));
			System.out.print("암호화된비번"+passwordEncoder.encode(pw));
			
			
			
			
			
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
	   public String login( Issue issue ,Member member, HttpServletRequest req, Model model,HttpSession session) throws Exception{

		   	System.out.println("접속 성공");
		   	
	  	    member.setMember_email(req.getParameter("email"));
	  	    member.setMember_pw(req.getParameter("pw"));
	
	    
	  	    List<Member> x = service.login(member);
	  	    String applogin = req.getParameter("applogin");

	  	    //암호화된 비번가져오기
	  	    String Amember_pw=service.eLogin(req.getParameter("email"));
	  	    //암호화된 비번이랑 로그인할때 입력한 비번이랑 비교 
	  	    if(passwordEncoder.matches(req.getParameter("pw"),Amember_pw)){
	  		System.out.println("계정정보 일치");
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
            
 		   // 메인 실시간 이슈
 		   List<Member> memberForDistance = service.getMemberInfo(member_code);
 		   lng = memberForDistance.get(0).getMember_lat();
 		   lat = memberForDistance.get(0).getMember_lng();
 		   	   
 		   // 추천 (랜덤) 상품 함 뽑아보자
 		   
		   List<Product> random_item = productservice.getRandomItem(lat, lng);
		   double distance = productservice.getDistance(random_item.get(0).getPlace_lat(), random_item.get(0).getPlace_lng(), lat, lng);
		   double result_distance = Double.parseDouble(String.format("%.2f",distance));
		   random_item.get(0).setDistance(result_distance);
		   model.addAttribute("random_item", random_item);

		   List<Product> new_item = productservice.getNewItem();
		   distance = productservice.getDistance(new_item.get(0).getPlace_lat(), new_item.get(0).getPlace_lng(), MemberController.lat, MemberController.lng);
		   result_distance = Double.parseDouble(String.format("%.2f",distance));
		   new_item.get(0).setDistance(result_distance);
		   model.addAttribute("new_item", new_item);
		   
		   List<Product> best_item = productservice.getBestItem();
		   distance = productservice.getDistance(best_item.get(0).getPlace_lat(), best_item.get(0).getPlace_lng(), MemberController.lat, MemberController.lng);
		   result_distance = Double.parseDouble(String.format("%.2f",distance));
		   best_item.get(0).setDistance(result_distance);
		   model.addAttribute("best_item", best_item);
		   
 		   List<Issue> issueList = issueservice.mainIssue(lat, lng);
 		   model.addAttribute("mainIssue", issueList);
 		   model.addAttribute("lat", lat);
           model.addAttribute("lng", lng);
  	    	
			if (applogin != null) {
				session.setAttribute("applogin", "success");
				return "main";
			} else {
				model.addAttribute("cont", "main.jsp");
			    //옆에 떠다니는 메뉴를 표시하기 위한 변수 넘기기
			    model.addAttribute("isProduct", "true");
				return "index";
			}
  	    
	  		
	  		}else{
	  		System.out.println("계정정보 불일치");
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
		public String logout(Issue issue,Model model,HttpSession session, HttpServletRequest req) throws Exception{
			// 메인 실시간 이슈
/*			List<Issue> issueList = issueservice.mainIssue();
			model.addAttribute("mainIssue", issueList);*/
			session.removeAttribute("member_code");
			session.invalidate();
			member_code = 0;
			PlaceController.Cmember_code = 0;
			
			model.addAttribute("cont", "main.jsp");
			
			lat = 35.895406;
			lng = 128.625386;
			
		    //옆에 떠다니는 메뉴를 표시하기 위한 변수 넘기기
		    model.addAttribute("isProduct", "true");
			return "index";
		}
		
		
		@RequestMapping(value="updateinterest", method=RequestMethod.POST)
		public String interestSetting(Model model, HttpServletRequest req) throws Exception{
			String member_interest = req.getParameter("member_interest");
			int member_code = MemberController.member_code;
			
			List<Member> memberForDistance = service.getMemberInfo(member_code);
			lng = memberForDistance.get(0).getMember_lat();
			lat = memberForDistance.get(0).getMember_lng();
			model.addAttribute("lat", lat);
			model.addAttribute("lng", lng);
			
			
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
		
	@RequestMapping(value = "cropProfile", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
    public Object cropProfile(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, MultipartHttpServletRequest req) throws IOException{
		String sibal = request.getParameter("sibal");
        int x1=Integer.parseInt(request.getParameter("x"));
        int y1=Integer.parseInt(request.getParameter("y"));
        int x2=Integer.parseInt(request.getParameter("x2"));
        int y2=Integer.parseInt(request.getParameter("y2"));
        int w=Integer.parseInt(request.getParameter("w"));
        int h=Integer.parseInt(request.getParameter("h"));
        oriWidth=Integer.parseInt(request.getParameter("oriWidth"));
        oriHeight=Integer.parseInt(request.getParameter("oriHeight"));
        System.out.println(x1+" "+y1+" "+x2+" "+y2+" "+w+" "+" "+h);

        
        String image = request.getParameter("imageName");
        Iterator<String> itr = req.getFileNames();
        File result = new File(joinFormPath);
        System.out.println("imageName"+image);
		if (itr.hasNext()) {
			MultipartFile mpf = req.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded!");
			
			try {
				// just temporary save file info into ufile
				System.out.println("file length : " + mpf.getBytes().length);
				System.out.println("file name : " + mpf.getOriginalFilename());
				String pdfPath = request.getSession().getServletContext().getRealPath("");
				
				mpf.transferTo(new File(pdfPath+"\\resources\\img\\"+mpf.getOriginalFilename())); 
				System.out.println(pdfPath+"\\resources\\img\\"+mpf.getOriginalFilename());
				
				
				BufferedImage bi = ImageIO.read(new File(pdfPath+"\\resources\\img\\"+mpf.getOriginalFilename()));
				BufferedImage out = bi.getSubimage(x1, y1, w, h);
				
/*				BufferedImage out = resizeImage(bi, bi.getType());*/
				String savedPath = calculatePath(joinFormPath);
				UUID uid = UUID.randomUUID();
				result = new File(joinFormPath+savedPath, uid+mpf.getOriginalFilename());
				ImageIO.write(out,"jpg", result);
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} else {
			result = new File(joinFormPath);
		}
		String er = result.getPath();
		System.out.println(er);
        response.setContentType("text/html");
        
		return new ResponseEntity<>(er, HttpStatus.CREATED);
	}
		
	private static String calculatePath(String joinFormPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		DecimalFormat df = new DecimalFormat("00");
		String monthPath = df.format(cal.get(Calendar.MONTH)+1);
		/*/2017/05 */
		monthPath = yearPath + File.separator + monthPath;
		
		/*/2017/05/12 */
		
		String datePath = File.separator+ df.format(cal.get(Calendar.DATE));
		datePath = monthPath+datePath;
		
		File folder = new File(joinFormPath+datePath);
		if(folder.exists()==false) {
			folder.mkdirs();
		}
		
		return datePath;
	}
	
	@ResponseBody  //占쏙옙환占싹깍옙占쏙옙占쌔쇽옙 (占쏙옙占쏙옙占쏙옙)
	   @RequestMapping("displayMemberProfile")
	   public ResponseEntity<byte[]> displayMemberProfile(String fileName)throws Exception{

	      InputStream in = null;
	      ResponseEntity<byte[]> entity = null;

	      //logger.info("File name : " + fileName);
	      
	      String ext = fileName.substring(fileName.lastIndexOf(".")+1);
	      String fileFormat = fileName.substring(fileName.lastIndexOf("."));
	      //logger.info("ext:"+ext);
	      
	      MediaType mediaType = MediaUtils.getMediaType(ext.toLowerCase());

	      HttpHeaders headers = new HttpHeaders();
	      //uploadPath : resources/upload
	      //fileName : /2017/05/18/ThumNail_rose_XXXXXXXX.jpg
	      
	      try{
	         in = new FileInputStream(joinFormPath+fileName);
	         
	         System.out.println(in);
	         if(mediaType != null){
	            headers.setContentType(mediaType);
	         }else{
	            fileName = fileName.substring(fileName.indexOf("_")+1);
	            System.out.println(fileName);
	            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	            String fN = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	            headers.add("Content-Disposition", "attachment; filename=\""+fN+"\"");
	         }
	         
	         byte[] data = IOUtils.toByteArray(in);
	         entity = new ResponseEntity<byte[]>(data, headers, HttpStatus.CREATED);
	         
	      }catch(Exception e){
	         e.printStackTrace();
	         entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
	      }finally{
	         in.close();
	      }
	      
	      return entity;
	   }

}
