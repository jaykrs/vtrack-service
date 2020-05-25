package com.vtrack.app.service.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtrack.app.service.constant.ServiceConstants;
import com.vtrack.app.service.entity.Users;
import com.vtrack.app.service.entity.Visitors;
import com.vtrack.app.service.mail.EmailInterface;
import com.vtrack.app.service.repository.UsersRepository;
import com.vtrack.app.service.repository.VisitorsRepository;

/**
 * @author jayant
 *
 */
@RestController
@RequestMapping("/api/visitor")
public class VisitorController {

	private final Logger log = LoggerFactory.getLogger(VisitorController.class);
	@Autowired
	private VisitorsRepository visitorsRepository;
	@SuppressWarnings("unused")
	@Autowired
	private EmailInterface emailUtils;
	@Autowired
	private UsersRepository usersRepository;
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	ResponseEntity<?> getVisitorsById(@PathVariable Long id) {
		Optional<Visitors> visitors = visitorsRepository.findById(id);
		log.info("found visitor with id" + id);
		return visitors.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * @param user
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/add")
	ResponseEntity<?> createVisitors(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		Users users = usersRepository.findByDeviceToken(json.get(ServiceConstants.DEVICETOKEN));
		if (null != users && users.getIsActive()) {
		log.info("Request to add visitors: {}", json.get(ServiceConstants.PHONENO));
		Visitors visitors = new Visitors(json.get(ServiceConstants.VENDORID),json.get(ServiceConstants.VENDORNAME),Integer.parseInt(json.get(ServiceConstants.CREATEDBY)),new Date(), json.get(ServiceConstants.FNAME),
				json.get(ServiceConstants.LNAME), json.get(ServiceConstants.PHONENO));
		
		if (null != json.get(ServiceConstants.CITY))
			visitors.setCity(json.get(ServiceConstants.CITY));
		if (null != json.get(ServiceConstants.EMAILID))
			visitors.setEmailId(json.get(ServiceConstants.EMAILID));
		if (null != json.get(ServiceConstants.ADDRESS))
			visitors.setAddress(json.get(ServiceConstants.ADDRESS));
		if (null != json.get(ServiceConstants.PINCODE))
			visitors.setPincode(json.get(ServiceConstants.PINCODE));
		if (null != json.get(ServiceConstants.VENDORLOCATION))
			visitors.setVendorLocation(json.get(ServiceConstants.VENDORLOCATION));
		if (null != json.get(ServiceConstants.DOB))
			try {
				visitors.setDob(new SimpleDateFormat("dd-MM-yyyy").parse(json.get(ServiceConstants.DOB)) );
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (null != json.get(ServiceConstants.COUNTRYCODE))
			visitors.setCountryCode(json.get(ServiceConstants.COUNTRYCODE));
		if (null != json.get(ServiceConstants.COUNTRY))
			visitors.setCountry(json.get(ServiceConstants.COUNTRY));
		if (null != json.get(ServiceConstants.INITIALS))
			visitors.setInitials(json.get(ServiceConstants.INITIALS));
		if (null != json.get(ServiceConstants.PROFESSION))
			visitors.setProfession(json.get(ServiceConstants.PROFESSION));
		if (null != json.get(ServiceConstants.REMARKS))
			visitors.setRemarks(json.get(ServiceConstants.REMARKS));
		visitors = visitorsRepository.saveAndFlush(visitors);
		return ResponseEntity.created(new URI("/api/visitors/add/" + visitors.getId())).body(visitors);
	}
		return ResponseEntity.created(new URI("/api/visitors/add/")).body(ServiceConstants.VISITORERRMSG);
	}
	/**
	 * @param user
	 * @return
	 */
	@PutMapping("/update")
	ResponseEntity<Visitors> updateVisitors(@Valid @RequestBody Map<String, String> json) {
		Optional<Visitors> visitors = visitorsRepository.findById(Long.parseLong(json.get(ServiceConstants.ID)));
		String deviceId = json.get(ServiceConstants.DEVICETOKEN);
		Users users = usersRepository.findByDeviceToken(deviceId);
		if (null != users && users.getIsActive() && visitors.isPresent()) {
			Visitors visitor = visitors.get();
			log.info("Request to update visitor: {}", visitor.getDisplayName());
			if (null != json.get(ServiceConstants.FNAME))
				visitor.setFirstName(json.get(ServiceConstants.FNAME));
			if (null != json.get(ServiceConstants.LNAME))
				visitor.setLastName(json.get(ServiceConstants.LNAME));
			if (null != json.get(ServiceConstants.CITY))
				visitor.setCity(json.get(ServiceConstants.CITY));
			if (null != json.get(ServiceConstants.PHONENO))
				visitor.setPhone(json.get(ServiceConstants.PHONENO));
			if (null != json.get(ServiceConstants.ADDRESS))
				visitor.setAddress(json.get(ServiceConstants.ADDRESS));
			if (null != json.get(ServiceConstants.PINCODE))
				visitor.setPincode(json.get(ServiceConstants.PINCODE));
			if (null != json.get(ServiceConstants.DOB))
				try {
					visitor.setDob(new SimpleDateFormat("dd-MM-yyyy").parse(json.get(ServiceConstants.DOB)) );
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (null != json.get(ServiceConstants.INITIALS))
				visitor.setInitials(json.get(ServiceConstants.INITIALS));
			if (null != json.get(ServiceConstants.COUNTRYCODE))
				visitor.setCountryCode(json.get(ServiceConstants.COUNTRYCODE));
			if (null != json.get(ServiceConstants.COUNTRY))
				visitor.setCountry(json.get(ServiceConstants.COUNTRY));
			if (null != json.get(ServiceConstants.PROFESSION))
				visitor.setProfession(json.get(ServiceConstants.PROFESSION));
			if (null != json.get(ServiceConstants.REMARKS))
				visitor.setRemarks(json.get(ServiceConstants.REMARKS));
			visitorsRepository.save(visitor);
		}
		return ResponseEntity.ok().body(visitors.get());
	}

	@GetMapping("/search/{createdBy}/{vendorId}")
	ResponseEntity<?> getVisitorsByCreatedId(@PathVariable String createdBy, @PathVariable String vendorId) {
		Optional<List<Visitors>> visitors = visitorsRepository.findAllByVendor(vendorId, Integer.parseInt(createdBy));
		log.info("found visitors with total count" + visitors.get().size());
		return visitors.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/searchDate/{vendorId}/{visitStartDate}/{visitEndDate}")
	ResponseEntity<?> getVisitorsByCreatedIdAndDate(@PathVariable String visitStartDate,@PathVariable String visitEndDate, @PathVariable String vendorId) {
		Date visitSDate = null,visitEDate = null;
		try {
			visitSDate = new SimpleDateFormat("dd-MM-yyyy").parse(visitStartDate);
			visitEDate = new SimpleDateFormat("dd-MM-yyyy").parse(visitEndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Optional<List<Visitors>> visitors = visitorsRepository.findAllByVendorAndDate(vendorId, visitSDate,visitEDate);
	//	log.info("found visitors with total count" + visitors.get().size());
		return visitors.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
