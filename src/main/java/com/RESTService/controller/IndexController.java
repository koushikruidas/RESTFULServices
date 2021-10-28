package com.RESTService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class IndexController {
	@GetMapping(value = "/hello")
	public String home() {
		return "Hello Koushik Ruidas";
	}
}
