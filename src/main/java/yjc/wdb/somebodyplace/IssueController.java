package yjc.wdb.somebodyplace;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import yjc.wdb.somebodyplace.bean.Issue;
import yjc.wdb.somebodyplace.bean.Member;
import yjc.wdb.somebodyplace.service.IssueService;
import yjc.wdb.somebodyplace.service.MemberService;


@Controller
public class IssueController {
	
	 public final static String AUTH_KEY_FCM = "AAAACTVNafU:APA91bF1R0nMfHzvV47CWk2tY2GKQgWYtm1snntQo4Vj9OjalOEV6eAUYnYKcVG8P7ZegoYJBB0pTCNfm6Gk0UUDgzys-3yNYXKAl381F0IdTfKxmPi3mebewUk1St8XwOZKscI6h-l2";
	 public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
		

	@Inject
	private IssueService service;
	
	@Inject
	private MemberService service2;
	
	@RequestMapping(value="issue", method=RequestMethod.GET)
	public String issue(HttpServletRequest req, Model model, HttpSession session) throws Exception{
		
		Object x = session.getAttribute("applogin");
		List<Issue> list = service.listAll();
		model.addAttribute("list", list);
		model.addAttribute("member_code", session.getAttribute("member_code"));
		
		if(x != null) {
			return "issue/issue";
		} else {
			model.addAttribute("cont", "issue/issue.jsp");
			return "index";			
		}
	}
	
	
	@RequestMapping(value="missue", method=RequestMethod.GET)
	public String missue(Model model) throws Exception{
		
		List<Issue> list = service.listAll();
		model.addAttribute("list", list);
	
		return "/issue/issue";
	}
	
	
	@RequestMapping(value="addIssue", method=RequestMethod.GET)
	public String modifyForm(HttpServletRequest req, Model model, HttpSession session){
		
		Object x = session.getAttribute("applogin");
		
		if(x != null) {
			return "issue/addIssue";
		} else {
			model.addAttribute("cont", "issue/addIssue.jsp");
			return "index";		
		}

		
	}
	
	
	@RequestMapping(value="m_addIssue", method=RequestMethod.GET)
	public String m_addIssue(Model model){
		
		return "issue/addIssue";
	}
	
	@RequestMapping(value="maddIssue", method=RequestMethod.GET)
	public String mmodifyForm(Model model){
	
		
		return "/issue/addIssue";
	}
	
	@RequestMapping(value="deleteIssue", method=RequestMethod.GET)
	public String deleteIssue(Model model,Issue issue,int issue_code) throws Exception{
		System.out.println(issue.getIssue_code());
		System.out.print(issue_code);
		
		service.remove(issue_code);
		
		
		
		
		List<Issue> list = service.listAll();
		model.addAttribute("list", list);
		model.addAttribute("cont", "issue/issue.jsp");
		return "index";
	}
	

	@RequestMapping(value="searchIssue", method=RequestMethod.POST)	
	public String searchIssue(Issue issue, RedirectAttributes rttr,HttpServletRequest req,Model model,String search_keyword) throws Exception{
		
		issue.setSearch_keyword(search_keyword);
		System.out.println("컨트롤러");
	
		
		List<Issue> list2 = service.searchlistAll(issue.getSearch_keyword());
		
		System.out.print(list2);
		model.addAttribute("list", list2);
		model.addAttribute("search_keyword",issue.getSearch_keyword());
		
		model.addAttribute("cont", "issue/issue.jsp");
		return "index";
	}
	
	//푸쉬알림 보내는 메소드 
	@RequestMapping(value="push", method=RequestMethod.GET)	
	public String push(Model model) throws Exception{
		System.out.print("푸쉬다시보냈음");
		
		 String userDeviceIdKey= "cltM_tmDN7Y:APA91bGOgWCqZiY2pszFE4kWljUdxyeeOt_rs_UQJORoVb7mpaD7Lni8NUwAvOdVRRzJw77kwxzJEFsXetmN8mU_MPhXOH-KnYLYYbUWtK3C6T3zR-2XhhBqmr5wWKyc2wG9_OYhbIB6";
		 String authKey = AUTH_KEY_FCM; // You FCM AUTH key
	     String FMCurl = API_URL_FCM;

	        URL url = new URL(FMCurl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        conn.setUseCaches(false);
	        conn.setDoInput(true);
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "key=" + authKey);
	        conn.setRequestProperty("Content-Type", "application/json");

	        JSONObject json = new JSONObject();
	        JSONObject info = new JSONObject();

	        info.put("body", "이슈가 도착했어요!!"); // Notification body
	        

	        json.put("notification", info);
	        json.put("to", userDeviceIdKey.trim()); // deviceID

	        try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())){
	//혹시나 한글 깨짐이 발생하면 
	//try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")){ 인코딩을 변경해준다.

	            wr.write(json.toString());
	            wr.flush();
	        }catch(Exception e){
	        }

	        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	        }

	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (conn.getInputStream())));

	        String output;
	        System.out.println("Output from Server .... \n");
	        while ((output = br.readLine()) != null) {
	            System.out.println(output);
	        }

	        conn.disconnect();





		
		
		return "redirect:issue";
	}
	
	
	
	
	
	
	@RequestMapping(value="createIssue", method=RequestMethod.POST)	
	public String postBoardForm(Issue issue, RedirectAttributes rttr,Model model,String roadAddrPart1,HttpSession session) throws Exception{
		
		
		
		
		 String userDeviceIdKey= "cltM_tmDN7Y:APA91bGOgWCqZiY2pszFE4kWljUdxyeeOt_rs_UQJORoVb7mpaD7Lni8NUwAvOdVRRzJw77kwxzJEFsXetmN8mU_MPhXOH-KnYLYYbUWtK3C6T3zR-2XhhBqmr5wWKyc2wG9_OYhbIB6";
		 String authKey = AUTH_KEY_FCM; // You FCM AUTH key
	     String FMCurl = API_URL_FCM;
	     System.out.print("푸쉬보냄");
	        URL url = new URL(FMCurl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        conn.setUseCaches(false);
	        conn.setDoInput(true);
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "key=" + authKey);
	        conn.setRequestProperty("Content-Type", "application/json");

	        JSONObject json = new JSONObject();
	        JSONObject info = new JSONObject();

	        info.put("body", "이슈가 도착했어요!!확인해보세요"); // Notification body
	        json.put("color","#da3f3a");
	        json.put("notification", info);
	        json.put("to", userDeviceIdKey.trim()); // deviceID

	        try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())){
	//혹시나 한글 깨짐이 발생하면 
	//try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")){ 인코딩을 변경해준다.

	            wr.write(json.toString());
	            wr.flush();
	        }catch(Exception e){
	        }

	        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	        }

	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (conn.getInputStream())));

	        String output;
	        System.out.println("Output from Server .... \n");
	        while ((output = br.readLine()) != null) {
	            System.out.println(output);
	        }

	        conn.disconnect();




		 System.out.println(issue.getMember_code());
		 System.out.println(issue.getHashtag());
		 //맴버코드로 닉네임을 알아냄 
		 String nickname = service2.read(issue.getMember_code());
		 model.addAttribute("member_nickname",nickname);
		 
		  System.out.println("content:" +issue.getIssue_content());
	      System.out.println("img:" + issue.getIssue_img());
	      System.out.println("한국주소" + issue.getAddress());
	      System.out.println("위도"+issue.getLat());
	      System.out.println("경도"+issue.getLng());
	      System.out.println("반경"+issue.getRadius());
	 
	      
	   //이슈 DB에 등록하고 list에 담음 
		service.regist(issue);
		List<Issue> list = service.listAll();
		System.out.print(list);
		model.addAttribute("list", list);
		
		//여기서부턴 이슈알림 보내는거!!!
		
		//게시글의 위도,경도,반경을 보내서 memberlist에 담음 
		List<Member> memberlist = service2.listAll(issue.getLat(),issue.getLng(),issue.getRadius());
	
		model.addAttribute("memberlist", memberlist);
		
		session.setAttribute("pushMemberlist",memberlist);
	
	
		model.addAttribute("cont", "issue/issue.jsp");
		return "index";
	}
   
	@ResponseBody
	@RequestMapping(value="getIssueReceiver", method=RequestMethod.POST)
	public JSONArray getIssueReceiver(HttpServletRequest req, Model model, HttpSession session) throws Exception{
		
		double lat = Double.parseDouble(req.getParameter("lat"));
		double lng = Double.parseDouble(req.getParameter("lng"));
		int radius = Integer.parseInt(req.getParameter("radius"));
		
		System.out.println("위도는 : "+lat+", 경도는 : "+lng+", 반경은 : "+radius);
		
		List<Member> memberlist = service2.listAll(lat,lng,radius);
		
    	JSONObject jsonObject = new JSONObject();
    	JSONArray resultArray = new JSONArray();
		
		for(int k =0; k<memberlist.size();k++) {	
			JSONObject todoInfo = new JSONObject();
		    todoInfo.put("issue_receiver", memberlist.get(k).getMember_code());
		    resultArray.add(todoInfo);
		}
    	
		return resultArray;
	}

}
