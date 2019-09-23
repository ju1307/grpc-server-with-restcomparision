package com.grpc.grpcserver.service;

import java.util.List;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.grpc.grpcserver.proto.AttItem;
import com.grpc.grpcserver.proto.AttItemList;
import com.grpc.grpcserver.proto.AttItemServiceGrpc;
import com.grpc.grpcserver.repository.DataEntity;
import com.grpc.grpcserver.repository.DataRepository;

import io.grpc.stub.StreamObserver;

@GRpcService
public class DataBaseServiceGrpc  extends AttItemServiceGrpc.AttItemServiceImplBase{

	@Autowired
    DataRepository datarepository;

	@Override
	public void getItemService(AttItem request, StreamObserver<AttItemList> responseObserver) {
		Pageable firstPageWithTwoElements = new PageRequest(1, 1000);//.of(0, 2);
		Page<DataEntity> list = datarepository.findAll(firstPageWithTwoElements);
		 AttItemList.Builder responseBuilder = AttItemList.newBuilder();
		list.getContent().forEach(dataItem->{
			AttItem attItem = AttItem.newBuilder().setId(dataItem.getId().toString()).setItemId(dataItem.getItemId()).setItemNo(dataItem.getItemNo()).build();
			responseBuilder.addAttItem(attItem);
		});
		AttItemList attItemList = responseBuilder.build();
		responseObserver.onNext(attItemList);
		 responseObserver.onCompleted();
	}

	@Override
	public void getItemStreamService(AttItem request, StreamObserver<AttItemList> responseObserver) {
		long count = datarepository.count();
		count=count/1000;
		 AttItemList.Builder responseBuilder = AttItemList.newBuilder();
		 Pageable firstPageWithTwoElements = new PageRequest(1, 1000);//.of(0, 2);
			Page<DataEntity> list = datarepository.findAll(firstPageWithTwoElements);
		for(int i=0;i<10;i++) {
			
			 
			list.getContent().forEach(dataItem->{
				AttItem attItem = AttItem.newBuilder().setId(dataItem.getId().toString()).setItemId(dataItem.getItemId()).setItemNo(dataItem.getItemNo()).build();
				responseBuilder.addAttItem(attItem);
			});
			AttItemList attItemList = responseBuilder.build();
			responseObserver.onNext(attItemList);	
		}
		responseObserver.onCompleted();
	}
}
