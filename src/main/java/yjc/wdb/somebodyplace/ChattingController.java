package yjc.wdb.somebodyplace;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import yjc.wdb.somebodyplace.bean.Auto;
import yjc.wdb.somebodyplace.bean.Chatting;
import yjc.wdb.somebodyplace.bean.Detail;
import yjc.wdb.somebodyplace.bean.Member;
import yjc.wdb.somebodyplace.bean.Option;
import yjc.wdb.somebodyplace.bean.Place;
import yjc.wdb.somebodyplace.bean.Product;
import yjc.wdb.somebodyplace.service.ChattingService;
import yjc.wdb.somebodyplace.service.DetailService;
import yjc.wdb.somebodyplace.service.OptionService;
import yjc.wdb.somebodyplace.service.PlaceService;
import yjc.wdb.somebodyplace.service.ProductService;
import yjc.wdb.somebodyplace.util.MediaUtils;
import yjc.wdb.somebodyplace.util.UploadFileUtils;

@Controller
public class ChattingController {
    
	@Inject
	private ChattingService service;
	
	@Inject
	private ProductService productservice;
	
	@Inject
	private OptionService optionservice;
	
	@Inject
	private DetailService detailservice;
	
	@Inject
	private PlaceService placeservice;
	
	private static final Logger logger = LoggerFactory.getLogger(ChattingController.class);

	@Resource(name="uploadPath")
	private String uploadPath;
	
    @ResponseBody
    @RequestMapping(value="addChat", method=RequestMethod.POST)	
	public void addChat(Chatting chatting) throws Exception{
		service.registChat(chatting);
	}
    
    @ResponseBody
    @RequestMapping(value="delChat", method=RequestMethod.POST)
    public void delChat(HttpServletRequest req, Chatting chatting) throws Exception {
    	service.delChat(chatting);
    }
    
    @ResponseBody
    @RequestMapping(value="getReceiver", method=RequestMethod.POST)
    public int getReceiver(Member member, HttpServletRequest req, Model model) throws Exception {
    	member.setMember_nickname(req.getParameter("member_nickname"));
    	List<Member> x = service.getReceiver(member);
    	return x.get(0).getMember_code();
    }

    @ResponseBody
    @RequestMapping(value="getRooms", method=RequestMethod.POST)
    public JSONArray getRooms(Chatting chatting, HttpServletRequest req, Model model) throws Exception {
    	chatting.setReceiver(req.getParameter("chat_receiver"));
    	List<Chatting> x = service.getRooms(chatting);
  
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd (HH:mm)");

    	// JSON �������� �ҷ��´�.
    	
    	JSONObject jsonObject = new JSONObject();
    	JSONArray resultArray = new JSONArray();
    	
    	  for(int i=0; i < x.size(); i++){
    		    JSONObject todoInfo = new JSONObject();
    		    todoInfo.put("chat_sender", x.get(i).getChat_sender());
    		    todoInfo.put("sender", x.get(i).getSender());
    		    todoInfo.put("chat_receiver", x.get(i).getChat_receiver());
    		    todoInfo.put("chat_content", x.get(i).getChat_content());
    		    todoInfo.put("chat_time", dateFormat.format(x.get(i).getChat_time()));
    		    resultArray.add(todoInfo);
    		  }
    	return resultArray;
    }
    
    @ResponseBody
    @RequestMapping(value = "getSenderNum", method=RequestMethod.POST)
    public int getSenderNum(Chatting chatting, HttpServletRequest req) throws Exception {
    	chatting.setSender(req.getParameter("sender"));
    	int x =  service.getSenderNum(chatting);
    	
    	return x;
    }
    
    @ResponseBody
    @RequestMapping(value="getReceiverName", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
    public String getReceiverName(Chatting chatting, HttpServletRequest req) throws Exception {
    	chatting.setReceiver(req.getParameter("receiver"));
    	
    	String x = service.getReceiverName(chatting);
    	
    	return x;
    }
    
    @ResponseBody
    @RequestMapping(value="countChatsInRoom", method=RequestMethod.POST)
    public int countChatsInRoom(Chatting chatting, HttpServletRequest req) throws Exception {
    	chatting.setSender(req.getParameter("sender"));
    	chatting.setReceiver(req.getParameter("receiver"));
    	int x = service.countChatsInRoom(chatting);
    	return x;
    }
    
    @ResponseBody
    @RequestMapping(value = "chatList", method=RequestMethod.POST)
    public JSONArray chatList(Chatting chatting, HttpServletRequest req) throws Exception {
    	chatting.setReceiver(req.getParameter("receiver"));
    	chatting.setSender(req.getParameter("sender"));
    	
    	
    	List<Chatting> x = service.chatList(chatting);
    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd (HH:mm)");

    	
    	JSONArray resultArray = new JSONArray();
    	
  	  for(int i=0; i < x.size(); i++){
		    JSONObject todoInfo = new JSONObject();
		    todoInfo.put("chat_sender", x.get(i).getChat_sender());
		    todoInfo.put("sender", x.get(i).getSender());
		    todoInfo.put("chat_receiver", x.get(i).getChat_receiver());
		    todoInfo.put("chat_content", x.get(i).getChat_content());
		    todoInfo.put("chat_time", dateFormat.format(x.get(i).getChat_time()));
		    resultArray.add(todoInfo);
		  }
    	
    	return resultArray;
    }
    
    @ResponseBody
    @RequestMapping(value="addDist", method=RequestMethod.POST)	
	public void addDist(HttpServletRequest req, Chatting chatting) throws Exception{
    	chatting.setReceiver(req.getParameter("receiver"));
		service.addDist(chatting);
	}
    
    @ResponseBody
    @RequestMapping(value="readDist", method=RequestMethod.POST)	
	public void readDist(HttpServletRequest req, Chatting chatting) throws Exception{
    	chatting.setReceiver(req.getParameter("receiver"));
		service.readDist(chatting);
	}
    

    
    
    @ResponseBody
    @RequestMapping(value="countDist", method=RequestMethod.POST)	
	public int countDist(HttpServletRequest req, Chatting chatting) throws Exception{
    	chatting.setReceiver(req.getParameter("receiver"));
		int x = service.countDist(chatting);
		
		return x;
	}
    
    @RequestMapping(value="appCountDist", method=RequestMethod.GET)	
    public void appCountDist(HttpServletRequest req, HttpSession session, HttpServletResponse res, Chatting chatting, Model model) throws Exception {

		String callback = req.getParameter("callback");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
		
		req.setCharacterEncoding("utf-8");
		
    	chatting.setReceiver(req.getParameter("receiver"));
		int x = service.countDist(chatting);
		
		map.put("result", x);

		try { 
			result = mapper.writeValueAsString(map);
		}
		catch (JsonGenerationException e) {
			e.printStackTrace();
		}
		catch (JsonMappingException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(callback+"(" + result + ")");
		PrintWriter out = res.getWriter();
		out.write(callback+"(" + result + ")");
	}
    
    
    
    
    @ResponseBody
    @RequestMapping(value="addAutoList", method=RequestMethod.POST)
	public void addAutoList(HttpServletRequest req, Auto auto) throws Exception{
    	auto.setAuto_code(Integer.parseInt(req.getParameter("auto_code")));
    	auto.setAuto_title(req.getParameter("auto_title"));
		service.addAutoList(auto);
	}
    
    @RequestMapping(value="delAutoList", method=RequestMethod.POST)
    public void delAutoList(HttpServletRequest req, Auto auto) throws Exception {
    	auto.setAuto_code(Integer.parseInt(req.getParameter("auto_code")));
    	auto.setAuto_title(req.getParameter("auto_title"));
		service.delAutoList(auto);    	
    }
    
    @ResponseBody
    @RequestMapping(value="readAutoList", method=RequestMethod.GET)
    public JSONArray readAutoList(Auto auto, HttpServletRequest req) throws Exception {
    	auto.setAuto_code(Integer.parseInt(req.getParameter("auto_code")));
    	
    	List<Auto> x = service.readAutoList(auto);
    	
    	JSONArray resultArray = new JSONArray();
    	
    	for(int i=0; i < x.size(); i++){
    		JSONObject todoInfo = new JSONObject();
    		if(x.get(i).getAuto_content()==null) {
    			todoInfo.put("auto_content", "");
    		} else {
    			todoInfo.put("auto_content", x.get(i).getAuto_content());
    		}
		    todoInfo.put("auto_title", x.get(i).getAuto_title());
		    resultArray.add(todoInfo);
		  }
    	return resultArray;
    }
    @ResponseBody
    @RequestMapping(value="addAuto", method=RequestMethod.POST)
    public void addAuto(Auto auto, HttpServletRequest req) throws Exception {
    	auto.setAuto_code(Integer.parseInt(req.getParameter("auto_code")));
    	auto.setAuto_title(req.getParameter("auto_title"));
    	auto.setAuto_content(req.getParameter("auto_content"));
    	
    	if(req.getParameter("auto_content")=="") {
    		auto.setAuto_content(null);
    	}
    	
    	service.addAuto(auto);
    }
    
    @ResponseBody
    @RequestMapping(value="readAuto", method=RequestMethod.POST)
    public JSONArray readAuto(Auto auto, HttpServletRequest req) throws Exception {
       auto.setAuto_title(req.getParameter("auto_title"));
       if(req.getParameter("auto_code")!=null)
       auto.setAuto_code(Integer.parseInt(req.getParameter("auto_code")));
      List<Auto> x = service.readAuto(auto);
      JSONArray resultArray = new JSONArray();
      
      if(x.size()==0) {
          return resultArray;
       }

      else if(x.get(0).getAuto_content()==null) {
         JSONObject todoInfo = new JSONObject();
         todoInfo.put("auto_content", "아직 자동 답변을 입력하지 않았습니다!");
         todoInfo.put("member_nickname", x.get(0).getMember_nickname());
          resultArray.add(todoInfo);
          return resultArray;      
     }
      else {
	      JSONObject todoInfo = new JSONObject();
	      todoInfo.put("auto_content", x.get(0).getAuto_content());
	      todoInfo.put("member_nickname", x.get(0).getMember_nickname());
	      resultArray.add(todoInfo);
	      return resultArray;      
      }
    }
    
    @ResponseBody
	@RequestMapping("displayChatImage")
	public ResponseEntity<byte[]> displayChatImage(String fileName) throws Exception {
		ResponseEntity<byte[]> entity = null;
		
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);
		
		logger.info("ext:"+ext);
		
		MediaType mediaType = MediaUtils.getMediaType(ext);
		logger.info("mediaType:"+mediaType);
		InputStream in = null;
		
		logger.info("File Name : "+fileName);
		
		HttpHeaders headers = new HttpHeaders();
		// uploadPath : resources/upload
		// fileName : /2017/05/18/thumbNail_rose_XXXXXX.jpg
		try {
			in = new FileInputStream(uploadPath + fileName);
			if(mediaType != null) {
				headers.setContentType(mediaType);	
			} else {
				fileName = fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				String fN = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				headers.add("Content-Disposition", "attachment; fileName=\""+ fN +"\"");
			}
			
			byte[] data = IOUtils.toByteArray(in);
			entity = new ResponseEntity<byte[]>(data, headers, HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
    
    
    
	@RequestMapping(value="uploadIntoChat", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadIntoChat(MultipartFile file) throws Exception {
		logger.info("originalName:"+file.getOriginalFilename());
		logger.info("size:"+file.getSize());
		logger.info("contentType:"+file.getContentType());
		
		String savedName = UploadFileUtils.uploadFile(file.getOriginalFilename(), uploadPath, file.getBytes());
		
		return new ResponseEntity<>(savedName, HttpStatus.CREATED);
	}  
	
	@ResponseBody
	@RequestMapping(value="searchTheirItem", method=RequestMethod.POST)
	public JSONObject searchTheirItem(HttpServletRequest req) throws Exception {
		
		String keyword = req.getParameter("keyword");
		String isSame = "";
		int product_allCode = 0;
		int owner = Integer.parseInt(req.getParameter("owner"));
		
		List<Product> ownerItem = productservice.getProductInfo(owner);
		
		System.out.println("내가 입력한 키워드는 : "+keyword+", 물건 주인의 회원코드는 : "+owner);
		
		for(int x = 0;x<ownerItem.size();x++) {
			System.out.println("물건 주인이 파는 물건 이름은 :"+ownerItem.get(x).getProduct_name());
			if(keyword.matches(ownerItem.get(x).getProduct_name())) {
				isSame = "success";
				product_allCode = ownerItem.get(x).getProduct_code();
				break;
			} else {
				isSame = "failed";
			}

		}
		
		JSONObject resultJSON = new JSONObject();
		
		resultJSON.put("result", isSame);
		resultJSON.put("product_code", product_allCode);

		return resultJSON;
	}
	
	@ResponseBody
	@RequestMapping(value="searchTheirItemList", method=RequestMethod.POST)
	public JSONArray searchTheirItemList(HttpServletRequest req) throws Exception {
		
		int owner = Integer.parseInt(req.getParameter("owner"));
		List<Product> ownerItem = productservice.getProductInfo(owner);

	    JSONArray resultArray = new JSONArray();
	    
	    if(ownerItem.size()==0) {
	    	return resultArray;
	    } else {
	    	for(int x =0;x<ownerItem.size();x++) {
			      JSONObject todoInfo = new JSONObject();
			      System.out.println("이번 상품은 : "+ownerItem.get(x).getProduct_name());
			      String name = ownerItem.get(x).getProduct_name();
			      todoInfo.put("product_name", name);
			      todoInfo.put("product_code", ownerItem.get(x).getProduct_code());
			      todoInfo.put("product_price", ownerItem.get(x).getProduct_price());
			      System.out.println(ownerItem.get(x).getProduct_img());
			      todoInfo.put("product_img", ownerItem.get(x).getProduct_img());
			      resultArray.add(todoInfo);
		      }
	    	return resultArray; 
	    }
	}
	
	@ResponseBody
	@RequestMapping(value="getTheirItem", method=RequestMethod.POST)
	public JSONObject getTheirItem(Model model, HttpServletRequest req) throws Exception {
		
		int product_code = Integer.parseInt(req.getParameter("product_code"));
		Product product = productservice.selectProduct(product_code);
		
		JSONObject resultJSON = new JSONObject();
		resultJSON.put("product_name", product.getProduct_name());
		resultJSON.put("product_price", product.getProduct_price());
		resultJSON.put("product_code",  product.getProduct_code());
		resultJSON.put("product_type", product.getType());
		
		List<Option> option = optionservice.selectOption(product_code);
		JSONArray optionArray = new JSONArray();
		
		for(int k = 0;k<option.size();k++) {
			JSONObject optionJSON = new JSONObject();
			optionJSON.put("mcate_name", option.get(k).getOption_name());
			optionJSON.put("mcate_code", option.get(k).getOption_code());
			
			List<Detail> detail = detailservice.selectDetail(option.get(k).getOption_code());
			JSONArray detailArray = new JSONArray();
			for(int b=0; b<detail.size();b++) {
				JSONObject detailJson = new JSONObject();
				detailJson.put("dcate_name", detail.get(b).getDetail_name());
				detailJson.put("dcate_code", detail.get(b).getDetail_code());
				detailJson.put("option_code", detail.get(b).getOption_code());
				detailJson.put("add_price", detail.get(b).getAdditional_price());
				detailArray.add(detailJson);
			}
			optionJSON.put("mcate_detail", detailArray);
			optionArray.add(optionJSON);
		}
		resultJSON.put("product_mcate", optionArray);
		
	    System.out.println(resultJSON);
		
		return resultJSON;
	}
	
	@ResponseBody
	@RequestMapping(value="getDetailPriceForChat", method=RequestMethod.POST)
	public int getDetailPriceForChat(Model model, HttpServletRequest req) throws Exception {
		int detail_code = Integer.parseInt(req.getParameter("detail_code"));
		
		int detail_price = detailservice.getDetailPrice(detail_code);
		
		return detail_price;
	}
	
	@ResponseBody
	@RequestMapping(value="searchTheirAddress", method=RequestMethod.POST)
	public JSONArray searchTheirAddress(Model model, HttpServletRequest req) throws Exception {
		
		JSONArray resultArray = new JSONArray();
		
		int member_code = Integer.parseInt(req.getParameter("owner"));
		
		List<Place> placeInfo = placeservice.getMyPlaceInfo(member_code);
		
	    if(placeInfo.size()==0) {
	    	return resultArray;
	    } else {
	    	for(int x =0;x<placeInfo.size();x++) {
			      JSONObject todoInfo = new JSONObject();
			      todoInfo.put("place_addr", placeInfo.get(x).getPlace_addr());
			      todoInfo.put("place_lat", placeInfo.get(x).getPlace_lat());
			      todoInfo.put("place_lng", placeInfo.get(x).getPlace_lng());
			      resultArray.add(todoInfo);
		      }
	    }
				
		return resultArray;
	}
}