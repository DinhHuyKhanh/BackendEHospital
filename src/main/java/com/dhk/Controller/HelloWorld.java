package com.dhk.Controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value ="api/v1/helloworld")
@CrossOrigin("*")
public class HelloWorld {
	
	
	@GetMapping
	public ResponseEntity<?> helloworld(){	
		HashMap<String, Object> mp = new HashMap<>();
		mp.put("khanh", "jhhiiiiiiiiiiiiiiiii");
		System.out.println("ha" + mp.get("khanh"));
		return new ResponseEntity<HashMap<String, Object>>(mp,HttpStatus.OK) ;
	}

}
