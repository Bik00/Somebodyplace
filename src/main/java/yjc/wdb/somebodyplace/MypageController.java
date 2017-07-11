package yjc.wdb.somebodyplace;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.javassist.SerialVersionUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import yjc.wdb.somebodyplace.bean.Issue;
import yjc.wdb.somebodyplace.bean.Member;
import yjc.wdb.somebodyplace.service.MemberService;

@Controller
public class MypageController {
	
	@Inject
	private MemberService service;
	
	@RequestMapping(value="myPage", method=RequestMethod.GET)
	public String mypage(Model model){
		
		
		
		
		
		model.addAttribute("cont", "mypage/mypage.jsp");
		return "index";
	}
	
	@RequestMapping(value="modifyForm", method=RequestMethod.GET)
	public String modifyForm(Model model){
		model.addAttribute("cont", "mypage/modifyForm.jsp");
		return "index";
	}
	
	@RequestMapping(value="orderList", method=RequestMethod.GET)
	public String orderlist(Model model,String member_code) throws Exception{
		int member_code2=Integer.parseInt(member_code);
	
		
		List<Member> list =service.orderlist(member_code2);
		model.addAttribute("orderlist",list);
		System.out.print(list);
		model.addAttribute("cont", "mypage/orderList.jsp");
		return "index";
	}
	
	@RequestMapping(value="cartList", method=RequestMethod.GET)
	public String cart(Model model,String member_code) throws Exception{
		
		int member_code2=Integer.parseInt(member_code);
		
		List<Member> list=service.cartlist(member_code2);
		String a="";
		ArrayList<String> tt=new ArrayList();
		for(int z=0;z<=list.size();z++){
			for(int k=z+1;k<list.size();k++){
				System.out.print(list.get(z).getCart_code());
				System.out.println(list.get(k).getCart_code());
				if(list.get(z).getCart_code()==list.get(k).getCart_code()){
					
					a=list.get(z).getDetail_name();
					a+=list.get(k).getDetail_name();
					tt.add(a);
				}
			}	
		
		}
		model.addAttribute("cartlist",list);
		model.addAttribute("tt",tt);
		
		model.addAttribute("cont", "mypage/cart.jsp");
		return "index";
	}
	
	@RequestMapping(value="wishlist", method=RequestMethod.GET)
	public String wishlist(Model model){
		model.addAttribute("cont", "mypage/wishlist.jsp");
		return "index";
	}
	
	@RequestMapping(value="favorites", method=RequestMethod.GET)
	public String fovorties(Model model){
		model.addAttribute("cont", "mypage/favorites.jsp");
		return "index";
	}
	
	@RequestMapping(value="interestSetting", method=RequestMethod.GET)
	public String interestSetting(Model model){
		model.addAttribute("cont", "mypage/interestSetting.jsp");
		return "index";
	}
}
