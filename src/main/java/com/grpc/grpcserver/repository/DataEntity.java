package com.grpc.grpcserver.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "att_item")
public class DataEntity {
	@Id
	@Column(name = "ITEMPLANNING_ID")
	private Long id;
	@Column(name = "ITEM_NO")
	private String itemId;
	@Column(name = "ITEM_DESC")
	private String itemNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

}
