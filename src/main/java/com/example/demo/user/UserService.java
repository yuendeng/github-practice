package com.example.demo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	private HanUserRepository hanuserR;
	
	public String hello() {
        return "hello world!!!";
    }
	
	public List<HanUser>getAllUser() {
		List<HanUser> result=hanuserR.findAll();
		System.out.println(result);
        return result;
    }
	
	public List<HanUser>getUserByName(String name) {
		List<HanUser> result=hanuserR.findByName(name);
		System.out.println(result);
        return result;
    }
	
	public List<HanUser>getUserByAge(Integer age) {
		List<HanUser> result=hanuserR.findByAge(age);
		System.out.println(result);
        return result;
    }
	
	//
	public HanUser getOneUser(String id) {
		return hanuserR.findById(Integer.parseInt(id)).orElse(null);
	}
	
	public String create(CreatrRequest request) {
		try {
			if(hanuserR.findById(request.getId()).isPresent())
				return "資料已存在";
		HanUser addData=new HanUser(request.getId(),request.getName(),request.getAge());
	    hanuserR.save(addData);
		}catch(Exception e) {
			return "fall";
		}
		return "done";	
	}
	
	public String update(CreatrRequest request) {
		try {
			if(!hanuserR.findById(request.getId()).isPresent())
				return "資料不存在";
		HanUser addData=new HanUser(request.getId(),request.getName(),request.getAge());
	    hanuserR.save(addData);
		}catch(Exception e) {
			return "fall";
		}
		return "done";	
	}
	
	public String delete(CreatrRequest request) {
		try {
			if(!hanuserR.findById(request.getId()).isPresent())
				return "資料不存在";
	    hanuserR.deleteById(request.getId());
		}catch(Exception e) {
			return "fall";
		}
		return "done";	
	}
	//
	public List<HanUser> getUserNameNotNull(){
		 return hanuserR.findByNameIsNotNull();
	}
	/*
	public List<HanUser>getUserByName(String name) {
		List<HanUser> result=hanuserR.findByName(name);
		System.out.println(result);
        return result;
    }
	*/
	
	//*
	public HanUser getOneByName(String name) {
	    return hanuserR.findFirstByName(name).orElse(null);
	}
	//
	public List<HanUser> getAgeBetween10And20() {
	    return hanuserR.findAgeBetween10And20();
	}
	//
	public List<HanUser> getAllOrderByAge() {
	    return hanuserR.findAllOrderByAgeDesc();
	}
	//
	public List<Object[]> countByAge() {
	    return hanuserR.countUserGroupByAge();
	}

	
}
