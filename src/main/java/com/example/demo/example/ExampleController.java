package com.example.demo.example;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.CreatrRequest;
import com.example.demo.user.HanUser;
import com.example.demo.user.UserService;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/example")
@Slf4j
public class ExampleController {
	
	//private String message = "Pages";
	@Autowired
	private UserService userS;
	
	@GetMapping("/test")
	public String index(Map<String, Object> model) {
		log.info("TEST controller");
		model.put("message", "1234yuen");
		List<HanUser>userlist=userS.getAllUser();
		model.put("userlist", userlist);
		return "first";
	}
	@PostMapping("/confirm")									
	public String confirm(@ModelAttribute CreatrRequest rq,Map<String,Object> model){
		log.info("confirm {}",rq);
		model.put("request", rq);
		model.put("message", "確認頁");
		return "confirm";
	}
}
