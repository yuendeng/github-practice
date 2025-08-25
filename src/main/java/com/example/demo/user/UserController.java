package com.example.demo.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userS;
	
	@RequestMapping("/hello")
	public String hello() {
        return userS.hello();
    }
	
	@GetMapping("/getUser")
	public List<HanUser>getUser() {
        return userS.getAllUser();
    }
	
	@PostMapping("/getUserByName")
	public List<HanUser>getUserByName(@RequestBody String name) {
        return userS.getUserByName(name);
    }
	
	//查詢⼤於等於某個 age 的會員資料
	@PostMapping("/getUserByAge")
	public List<HanUser>getUserByAge(@RequestBody Integer age) {
        return userS.getUserByAge(age);
    }
	
	@PostMapping("/getOneUser")
	public HanUser getOneUser(@RequestBody String id) {
        return userS.getOneUser(id);
    }
	
	@PostMapping("/create")
	public String create(@RequestBody CreatrRequest request) {
        return userS.create(request);
    }
	
	@PostMapping("/update")
	public String update(@RequestBody CreatrRequest request) {
        return userS.update(request);
    }
	
	@PostMapping("/delete")
	public String delete(@RequestBody CreatrRequest request) {
        return userS.delete(request);
    }
	//查詢姓名不是null 的會員資料
	@GetMapping("/getUserNameIsNotNull")
	public List<HanUser> getUserNameIsNotNull() {
	    return userS.getUserNameNotNull();
	}
	//查詢姓名為某某的單筆 的會員資料
	@PostMapping("/getOneByName")
	public HanUser getOneByName(@RequestBody Map<String, String> param) {
	    String name = param.get("name");
	    System.out.println("收到名字: " + name);
	    return userS.getOneByName(name);
	}
	/*
	@PostMapping("/getUserByName")
	public List<HanUser>getUserByName(@RequestBody String name) {
        return userS.getUserByName(name);
    }
	*/
	//查詢10~20間age 的會員資料
	@GetMapping("/getAgeBetween10And20")
	public List<HanUser> getAgeBetween10And20() {
	    return userS.getAgeBetween10And20();
	}
	
	//查詢全部資料且根據 age 進⾏倒序排列
	@GetMapping("/getAllOrderByAge")
	public List<HanUser> getAllOrderByAge() {
	    return userS.getAllOrderByAge();
	}
	//查詢按age分群的個別⼈數(10歲的有2⼈.....)
	@GetMapping("/countByAge")
	public List<Object[]> countByAge() {
	    return userS.countByAge();
	}

}
