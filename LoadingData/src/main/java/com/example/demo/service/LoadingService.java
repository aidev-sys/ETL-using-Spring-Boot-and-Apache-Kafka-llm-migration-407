package com.example.demo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MathOperation;
import com.example.demo.repository.LoadingRepository;

@Service
public class LoadingService {
	
	@Autowired
	private LoadingRepository repo;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@RabbitListener(queuesToDeclare = @Queue(name = "data.queue", durable = true))
	public void loadDataToDB(String data) {
		
		String[] gettingEveryCharAsString = data.split(",");
		MathOperation dataToBePushed = new MathOperation(gettingEveryCharAsString[0], gettingEveryCharAsString[1], gettingEveryCharAsString[2], gettingEveryCharAsString[3]);
		repo.save(dataToBePushed);
	}
}