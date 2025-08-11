package com.example.demo.groupfunc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/groupfunc")
public class YuenGroupController {

    @Autowired
    private GroupFuncService groupfuncS;

    @GetMapping("/getUser")
    public List<YuenGroupFunc> getUser() {
        return groupfuncS.findAll();
    }

    @PostMapping("/getOneUser")
    public YuenGroupFunc getOneUser(@RequestBody SearchRequest rq) {
        return groupfuncS.findById(rq);
    }
    
    @PostMapping("/create")
	public String create(@RequestBody SearchRequest request) {
        return groupfuncS.create(request);
    }
	
	@PostMapping("/update")
	public String update(@RequestBody SearchRequest request) {
        return groupfuncS.update(request);
    }
	
	@PostMapping("/delete")
	public String delete(@RequestBody SearchRequest request) {
        return groupfuncS.delete(request);
	}
	
	@PostMapping("/pagesear")
	public Page<YuenGroupFunc> pageSearchAll(){
		return groupfuncS.pageSearchAll();
	}
	
	
}
