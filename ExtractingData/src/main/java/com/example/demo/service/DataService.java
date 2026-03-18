package com.example.demo.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class DataService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value(value = "${rabbitmq.fileAddress}")
	private String path;
	
	public List<String> getDataFromFile() {
		FileReader reader = null;
		List<String> data = new ArrayList<String>();
		try {
			reader = new FileReader(path);
			BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				data.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
	@Bean
	public Queue dataQueue() {
		return new Queue("dataQueue", true);
	}
	
	@RabbitListener(queues = "dataQueue")
	public void processFileData(String message) {
		System.out.println("Received message: " + message);
		// Process the message here
	}
}