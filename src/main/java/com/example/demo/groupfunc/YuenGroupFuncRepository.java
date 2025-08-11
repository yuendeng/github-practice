package com.example.demo.groupfunc;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface YuenGroupFuncRepository extends JpaRepository< YuenGroupFunc,GroupFuncPK>{
	
	 List<YuenGroupFunc> findByGroupid(long groupid);
	    
	 List<YuenGroupFunc> findByFuncid(long funcid);

	 List<YuenGroupFunc> findByGroupidAndFuncid(long groupid, long funcid);
	 
	 Page<YuenGroupFunc> findAll(Pageable pageable);
}



