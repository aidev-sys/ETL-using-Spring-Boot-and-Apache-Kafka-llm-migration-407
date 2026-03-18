package com.example.demo.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.demo.service.LoadingService;

@Controller
public class LoadingController {
	
	@Autowired
	private LoadingService service;
	
	
	@RabbitListener(queues = "target_queue")
	public void listen(String message) {
		
		System.out.println(message);
	    service.loadDataToDB(message);
	    
	}

}