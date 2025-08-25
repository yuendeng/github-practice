package com.example.demo.groupfunc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GroupFuncService {
	@Autowired
	private  YuenGroupFuncRepository groupFuncR;
	
	public List<YuenGroupFunc> findAll(){
		 return groupFuncR.findAll();
	}
	
	public YuenGroupFunc findById(SearchRequest rq) {
		return groupFuncR.findById(new GroupFuncPK(rq.getGroupid(), rq.getFuncid())).orElse(null);
	}
	
	public String create(SearchRequest request) {
        try {
            GroupFuncPK pk = new GroupFuncPK(request.getGroupid(), request.getFuncid());
            if (groupFuncR.findById(pk).isPresent()) {
                return "資料已存在";
            }

            YuenGroupFunc newData = new YuenGroupFunc(
                request.getGroupid(),
                request.getFuncid(),
                request.getAuthtype(),
                new java.sql.Timestamp(System.currentTimeMillis()) 
            );
            groupFuncR.save(newData);
        } catch (Exception e) {
        	log.error("{}",e.getMessage());
            return "fail";
        }
        return "done";
    }
	
	public String update(SearchRequest request) {
        try {
            GroupFuncPK pk = new GroupFuncPK(request.getGroupid(), request.getFuncid());
            if (!groupFuncR.findById(pk).isPresent()) {
                return "資料不存在";
            }

            YuenGroupFunc updateData = new YuenGroupFunc(
                request.getGroupid(),
                request.getFuncid(),
                request.getAuthtype(),
                new java.sql.Timestamp(System.currentTimeMillis()) // 更新 cdate
            );
            groupFuncR.save(updateData);
        } catch (Exception e) {
            return "fail";
        }
        return "done";
    }
	
	public String delete(SearchRequest request) {
        try {
            GroupFuncPK pk = new GroupFuncPK(request.getGroupid(), request.getFuncid());
            if (!groupFuncR.findById(pk).isPresent()) {
                return "資料不存在";
            }

            groupFuncR.deleteById(pk);
        } catch (Exception e) {
            return "fail";
        }
        return "done";
    }
	//可以用pk的class去刪除就好 不用用到全部
	
	public Page<YuenGroupFunc> pageSearchAll(){
		return groupFuncR.findAll(PageRequest.of(0, 5));//第0頁開始，每次搜5筆資料
	}
}