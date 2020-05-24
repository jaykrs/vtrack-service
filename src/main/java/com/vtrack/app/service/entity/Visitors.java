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
@Table(name = "VISITOR")
@NamedQueries({ @NamedQuery(name = "Visitors.findAllByVendor", query = "SELECT v FROM Visitors v WHERE v.vendorId = :vendorId and v.createdBy in (:createdBy)"),
		@NamedQuery(name = "Visitors.findAllByVendorAndDate", query = "SELECT v FROM Visitors v WHERE v.vendorId = :vendorId and v.visitDate in (:visitDate)")
		 })
public class Visitors implements Serializable {

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
	@Column(name = "visit_Date")
	private Date visitDate;

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
	
	@Column(name = "vendor_establishment_location", nullable = true)
	private String vendorLocation;
	
	@Column(name = "profession", nullable = true)
	private String profession;
	
	@Column(name = "remarks", nullable = true)
	private String remarks;
	
	@Column(name = "created_by")
	private int createdBy;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Visitors() {
		super();
	}

	public Visitors(String vendorId, String vendorName, int createdBy,Date visitDate, String firstName, String lastName,String phoneNo) {
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.createdBy = createdBy;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phoneNo;
		this.visitDate = visitDate;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the visitDate
	 */
	public Date getVisitDate() {
		return visitDate;
	}

	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the initials
	 */
	public String getInitials() {
		return initials;
	}

	/**
	 * @param initials the initials to set
	 */
	public void setInitials(String initials) {
		this.initials = initials;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	 * @return the vendorLocation
	 */
	public String getVendorLocation() {
		return vendorLocation;
	}

	/**
	 * @param vendorLocation the vendorLocation to set
	 */
	public void setVendorLocation(String vendorLocation) {
		this.vendorLocation = vendorLocation;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
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
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	
}
