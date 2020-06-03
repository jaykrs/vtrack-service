package com.vtrack.app.service.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jayant
 *
 */
@Entity
@Table(name = "account_transaction")
public class AccountTransection {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "user_Id")
	private Long userId;
	
	@Column(name = "amount")
	private int amount;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "currency")
	private String currency;
	
	@Column(name = "tdate")
	private Date transectionDate;
	
	@Column(name = "ORDERID")
	private String orderId;
	
	@Column(name = "order_rcptid_id")
	private String orderRcptidId;
	
	@Column(name = "transection_id")
	private String transectionId;
	
	@Column(name = "STATUS", columnDefinition = "TINYINT(1)")
	private Boolean status;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the transectionDate
	 */
	public Date getTransectionDate() {
		return transectionDate;
	}

	/**
	 * @param transectionDate the transectionDate to set
	 */
	public void setTransectionDate(Date transectionDate) {
		this.transectionDate = transectionDate;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @return the orderRcptidId
	 */
	public String getOrderRcptidId() {
		return orderRcptidId;
	}

	/**
	 * @param orderRcptidId the orderRcptidId to set
	 */
	public void setOrderRcptidId(String orderRcptidId) {
		this.orderRcptidId = orderRcptidId;
	}

	public AccountTransection(Long userId, int amount, Date transectionDate, String orderRcptidId) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.transectionDate = transectionDate;
		this.orderRcptidId = orderRcptidId;
	}

	/**
	 * @return the transectionId
	 */
	public String getTransectionId() {
		return transectionId;
	}

	/**
	 * @param transectionId the transectionId to set
	 */
	public void setTransectionId(String transectionId) {
		this.transectionId = transectionId;
	}

	public AccountTransection() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}