package com.vtrack.app.service.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Repository;

/**
 * @author jayant
 *
 */

@Entity
@Repository
@Table(name = "USER")
@NamedQueries({ @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
		@NamedQuery(name = "Users.validatePwd", query = "SELECT u FROM Users u WHERE u.emailId = :email and u.pwd = :pwd"),
		@NamedQuery(name = "Users.validateDeviceId", query = "SELECT u FROM Users u WHERE u.emailId = :email and u.deviceToken = :deviceId"),
		@NamedQuery(name = "Users.forgetPwd", query = "UPDATE Users u SET u.pwd= :pwd WHERE u.emailId= :email"),
		@NamedQuery(name = "Users.activateUser", query = "UPDATE Users u SET u.isActive= :activeInd WHERE u.emailId= :email"), })
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1385794955661915701L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "emailid", nullable = false)
	private String emailId;
	@Column(name = "Last_Login_Date")
	private Date lastLoginDate;

	@Column(name = "Dob")
	private Date dob;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "INITIALS", nullable = true)
	private String initials;

	@Transient
	private String displayName;

	@Column(name = "SUPERVISOR_ID", nullable = true)
	private String supervisorId;

	// @Type(type="yes_no")
	@Column(name = "ACTIVE_IND", columnDefinition = "TINYINT(1)")
	private Boolean isActive;
	@Column(name = "Password")
	private String pwd;
	
	@Column(name = "city", nullable = true)
	private String city;
	
	@Column(name = "country", nullable = true)
	private String country;
	
	@Column(name = "country_code", nullable = true)
	private String countryCode;
	
	@Column(name = "phone", nullable = true)
	private String phone;
	
	@Column(name = "address", nullable = true)
	private String address;
	
	@Column(name = "pincode", nullable = true)
	private String pincode;
	
	@Column(name = "vendor_establishment_id", nullable = true)
	private String vendorId;

	@Column(name = "vendor_establishment_name", nullable = true)
	private String vendorName;
	
	@Column(name = "device_token", nullable = true)
	private String deviceToken;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "activation_code", nullable = true)
	private String activationCode;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users() {
		super();
	}

	public Users(String emailId, String firstName, String lastName,String phoneNo) {
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phoneNo;
	}

	public Users(Long userId, String firstName, String lastName, String managedObjectId) {
		this.id = userId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getDisplayName() {
		return null != initials ?  initials +" "+ lastName + "," + firstName : lastName + "," + firstName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}


	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the vendorId
	 */
	public String getVendorId() {
		return vendorId;
	}

	/**
	 * @param vendorId the vendorId to set
	 */
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * @param vendorName the vendorName to set
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	/**
	 * @return the deviceToken
	 */
	public String getDeviceToken() {
		return deviceToken;
	}

	/**
	 * @param deviceToken the deviceToken to set
	 */
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the activationCode
	 */
	public String getActivationCode() {
		return activationCode;
	}

	/**
	 * @param activationCode the activationCode to set
	 */
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	/*
	 * @OneToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "USER_ROLE_ID") private UserRole userRole;
	 */
}
