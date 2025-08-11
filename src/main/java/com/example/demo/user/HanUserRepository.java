package com.example.demo.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HanUserRepository extends JpaRepository<HanUser,Integer>{
	
	List<HanUser>findByName(String name);
	
	//@Query(value = "SELECT * FROM HAN_USER WHERE AGE >= ?1 ORDER BY AGE Desc"
	//		, nativeQuery = true)
	//List<HanUser>findByAge(Integer age);
	
	@Query(value = "SELECT * FROM YUEN_USER WHERE AGE >= :age ORDER BY AGE Desc"
			, nativeQuery = true)
	List<HanUser>findByAge(@Param("age")Integer age);
	
	List<HanUser> findByNameIsNotNull();
	
	//List<HanUser>findByName(String name);
	
	Optional<HanUser> findFirstByName(String name);

	@Query(value = "SELECT * FROM YUEN_USER WHERE AGE BETWEEN 10 AND 20", nativeQuery = true)
	List<HanUser> findAgeBetween10And20();
	
	@Query(value = "SELECT * FROM YUEN_USER ORDER BY AGE DESC", nativeQuery = true)
	List<HanUser> findAllOrderByAgeDesc();
	
	@Query(value = "SELECT AGE, COUNT(*) AS count FROM YUEN_USER GROUP BY AGE", nativeQuery = true)
	List<Object[]> countUserGroupByAge();

}