package com.grpc.grpcserver.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grpc.grpcserver.AttItemListDTO;
@SpringBootApplication
@RestController
public class DataController {

	@Autowired
	DataRepository dataRepository;
	
	@GetMapping(value="getData")
	public AttItemListDTO getmapping(){
		Pageable firstPageWithTwoElements = new PageRequest(1, 10);//.of(0, 2);
		Page<DataEntity> list = dataRepository.findAll(firstPageWithTwoElements);
		AttItemListDTO list1= new AttItemListDTO();
		list1.setDataEnity(list.getContent());
		return list1;
		
	}
}
