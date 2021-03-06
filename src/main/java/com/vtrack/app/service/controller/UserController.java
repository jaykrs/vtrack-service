package com.vtrack.app.service.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.util.Strings;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.vtrack.app.service.constant.ServiceConstants;
import com.vtrack.app.service.entity.AccountTransection;
import com.vtrack.app.service.entity.Users;
import com.vtrack.app.service.mail.EmailInterface;
import com.vtrack.app.service.repository.TransectionRepository;
import com.vtrack.app.service.repository.UsersRepository;

/**
 * @author jayant
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private TransectionRepository transectionRepository;
	@Autowired
	private EmailInterface emailUtils;

	@Autowired
	private Environment environment;
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	ResponseEntity<?> getUsersById(@PathVariable Long id) {
		Optional<Users> users = usersRepository.findById(id);
		log.info("found user with id" + id);
		users.get().setPwd("");
		return users.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/getbydevice/{emailId}/{deviceid}")
	ResponseEntity<?> getUsersByDeviceId(@PathVariable String emailId, @PathVariable String deviceid) {
		Users users = usersRepository.validateDeviceId(emailId,deviceid);
		log.info("found user with id" + emailId);
		users.setPwd("");
		return ResponseEntity.ok().body(users);
	}
	
	/**
	 * @param user
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/add")
	ResponseEntity<?> createUser(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
		Users result = null;
		log.info("Request to create user: {}", json.get(ServiceConstants.EMAILID));
		if (null == usersRepository.findByEmailId(json.get(ServiceConstants.EMAILID))) {
			Users user = new Users(json.get(ServiceConstants.EMAILID), json.get(ServiceConstants.FNAME),
					json.get(ServiceConstants.LNAME), json.get(ServiceConstants.PHONENO));
			user.setCountryCode(json.get(ServiceConstants.COUNTRYCODE));
			user.setVendorId(json.get(ServiceConstants.VENDORID));
			user.setVendorName(json.get(ServiceConstants.VENDORNAME));
			user.setCreatedAt(new Date());
			LocalDate lt = LocalDate.now().plusDays(ServiceConstants.USER_EXPIRATION_DATE);
			user.setValidationDate(convertToDateViaSqlDate(lt));
			user.setIsActive(Boolean.FALSE);
			user.setDeviceToken(json.get(ServiceConstants.DEVICETOKEN));
			user.setPwd(new String(new Base64().encode(json.get(ServiceConstants.PWD).getBytes())));
			user.setActivationCode(UUID.randomUUID().toString());
			if (null != json.get(ServiceConstants.COUNTRY))
				user.setCountry(json.get(ServiceConstants.COUNTRY));
			if (null != json.get(ServiceConstants.ADDRESS))
				user.setAddress(json.get(ServiceConstants.ADDRESS));
			result = usersRepository.saveAndFlush(user);
			result.setPwd(Strings.EMPTY);
			String actContentUrl = environment.getProperty("email.send.activateurl")+result.getEmailId()+"/"+result.getActivationCode();
			String emailContent = environment.getProperty("email.send.activate.msg");
			emailUtils.sendMailJetEmail(ServiceConstants.USER_ACTIVATION, emailContent.replace("ACTIVATION_URL", actContentUrl) , result.getEmailId(), result.getDisplayName());
			result.setActivationCode("");
			return ResponseEntity.created(new URI("/api/user/add/" + result.getId())).body(result);
		}
		return ResponseEntity.unprocessableEntity().body(json.get(ServiceConstants.EMAILID) +ServiceConstants.EMAILEXISTS);
	}
	/**
	 * @param user
	 * @return
	 */
	@PutMapping("/update")
	ResponseEntity<Users> updateUser(@Valid @RequestBody Map<String, String> json) {
		Users user = usersRepository.findByEmailId(json.get(ServiceConstants.EMAILID));
		String deviceId = json.get(ServiceConstants.DEVICETOKEN);
		if (deviceId.equals(user.getDeviceToken())) {
			log.info("Request to update user: {}", user);
			if (null != json.get(ServiceConstants.INITIALS))
				user.setInitials(json.get(ServiceConstants.INITIALS));
			if (null != json.get(ServiceConstants.FNAME))
				user.setFirstName(json.get(ServiceConstants.FNAME));
			if (null != json.get(ServiceConstants.LNAME))
				user.setLastName(json.get(ServiceConstants.LNAME));
			if (null != json.get(ServiceConstants.PWD) && json.get(ServiceConstants.PWD).length() > 4)
				user.setPwd(new String(new Base64().encode(json.get(ServiceConstants.PWD).getBytes())));
			if (null != json.get(ServiceConstants.CITY))
				user.setCity(json.get(ServiceConstants.CITY));
			if (null != json.get(ServiceConstants.PHONENO))
				user.setPhone(json.get(ServiceConstants.PHONENO));
			if (null != json.get(ServiceConstants.ADDRESS))
				user.setAddress(json.get(ServiceConstants.ADDRESS));
			if (null != json.get(ServiceConstants.PINCODE))
				user.setPincode(json.get(ServiceConstants.PINCODE));
			if (null != json.get(ServiceConstants.VENDORID))
				user.setVendorId(json.get(ServiceConstants.VENDORID));
			if (null != json.get(ServiceConstants.VENDORNAME))
				user.setVendorName(json.get(ServiceConstants.VENDORNAME));
			if (null != json.get(ServiceConstants.COUNTRYCODE))
				user.setCountryCode(json.get(ServiceConstants.COUNTRYCODE));
			if (null != json.get(ServiceConstants.COUNTRY))
				user.setCountry(json.get(ServiceConstants.COUNTRY));
			user = usersRepository.save(user);
		}
		return ResponseEntity.ok().body(user);
	}

	/**
	 * api/user/validate {"emailId":"yyyyyy@in.com" , "pwd":"xxxxx"}
	 * 
	 * @param json
	 * @param request
	 * @return
	 */
	@PostMapping("/devicevalidate")
	ResponseEntity<?> validateUser(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) {
		log.info("Request to validate user: {}", json.get(ServiceConstants.EMAILID));
		String deviceId = json.get(ServiceConstants.DEVICETOKEN);
		Users result = usersRepository.validatePwd(json.get(ServiceConstants.EMAILID), json.get(ServiceConstants.PWD));
		if (null != result && result.getIsActive()) {
			result.setDeviceToken(deviceId);
			result.setLastLoginDate(new Date());
			usersRepository.save(result);
			result.setPwd("");
			return ResponseEntity.ok().body(result);
		} else if (null != result && !result.getIsActive()) {
			return ResponseEntity.ok(result.getEmailId() + " , " + ServiceConstants.INACTIVEUSERMSG);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * @param email
	 * @return
	 */
	@GetMapping("/resetpwd/{email}")
	ResponseEntity<Map<String, String>> resetPwd(@PathVariable String email) {
		Users user = usersRepository.findByEmailId(email);
		Map<String, String> response = new HashMap<String, String>();
		response.put(ServiceConstants.EMAILID, email);
		if (null != user) {
			log.info("found user with email" + email + "reset pwd reqest");
			String _tmpPwd = UUID.randomUUID().toString().substring(0, 10);
			int _b = usersRepository.forgetPwd(email, new String(new Base64().encode(_tmpPwd.getBytes())));
			String fwdContentUrl = environment.getProperty("email.send.forgotpwd.msg");
			emailUtils.sendMailJetEmail(ServiceConstants.RESET_PWD_SUBJECT, fwdContentUrl.replace("RESET_PWD_MSG", ServiceConstants.TEMP_PWD_EMAIL + _tmpPwd), user.getEmailId(), user.getDisplayName());
	//		emailUtils.sendPlainEmail(user.getEmailId(), ServiceConstants.TEMP_PWD_EMAIL + _tmpPwd);
			if (_b == 1) {
				response.put(ServiceConstants.MSG, "Please check your " + email + " inbox for temporary password");
			}
		} else {
			response.put(ServiceConstants.MSG, HttpStatus.NOT_FOUND.toString());
		}
		return ResponseEntity.ok().body(response);
	}
	
	/**
	 * @param email
	 * @param activationKey
	 * @return
	 */
	@GetMapping("/activateUser/{email}/{activationKey}")
	String activateUser(@PathVariable String email, @PathVariable String activationKey) {
		Users user = usersRepository.findByEmailId(email);
		Map<String, String> response = new HashMap<String, String>();
		response.put(ServiceConstants.EMAILID, email);
		String actContentMSG = environment.getProperty("email.send.forgotpwd.msg");
		if (null != user) {
			log.info("found user with email " + email + " activate reqest");
			if (activationKey.equals(user.getActivationCode())) {
				int _b = usersRepository.activateUser(email, Boolean.TRUE);
				if (_b == 1) {
					return actContentMSG.replace("RESET_PWD_MSG", user.getDisplayName() + " activation successfull");
				}
			}
		} else {
			response.put(ServiceConstants.MSG, HttpStatus.NOT_FOUND.toString());
		}
		return  actContentMSG.replace("RESET_PWD_MSG",user.getDisplayName() +" : "+ ServiceConstants.INACTIVEUSERMSG);
	}
	
	@PostMapping("/startpayment")
	ResponseEntity<?> makePayment(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
	
		Users users = usersRepository.findByEmailId(json.get(ServiceConstants.EMAILID));
		log.info("Request to make payment by user: {}", json.get(ServiceConstants.EMAILID));
		if (null != users && users.getIsActive()) {
			String order_rcptid_11 = Strings.EMPTY;
			 Order order = null;
			try {
				 order_rcptid_11 = UUID.randomUUID().toString().substring(0, 10);  
				JSONObject orderRequest = new JSONObject();
				  orderRequest.put("amount", ServiceConstants.DEFAULT_ORDER_AMT); // amount in the smallest currency unit
				  orderRequest.put("currency", ServiceConstants.DEFAULT_ORDER_CURR);
				  orderRequest.put("receipt", order_rcptid_11);
				  orderRequest.put("payment_capture", Boolean.FALSE);
				  order = new RazorpayClient(ServiceConstants.RAZORPAY_KEY, ServiceConstants.RAZORPAY_SECRET).Orders.create(orderRequest);
				} catch (RazorpayException e) {
				  // Handle Exception
				  System.out.println(e.getMessage());
				}
			AccountTransection acctTransection = new AccountTransection(users.getId(), ServiceConstants.DEFAULT_ORDER_AMNT, new Date(), order_rcptid_11);
			acctTransection.setStatus(Boolean.FALSE);
			acctTransection.setCurrency(ServiceConstants.DEFAULT_ORDER_CURR);
			acctTransection.setOrderId(order.get("id"));
			AccountTransection result = transectionRepository.save(acctTransection);
			return ResponseEntity.created(new URI("/api/user/startpayment/" + result.getId())).body(result);
		}
		return ResponseEntity.unprocessableEntity().body(json.get(ServiceConstants.EMAILID) +ServiceConstants.PAYMENT_ERROR);
	}
	
	@PostMapping("/completepayment")
	ResponseEntity<?> completePayment(@Valid @RequestBody Map<String, String> json,
			HttpServletRequest request) throws URISyntaxException {
	
		Users users = usersRepository.findByEmailId(json.get(ServiceConstants.EMAILID));
		log.info("Request to complete payment by user: {}", json.get(ServiceConstants.EMAILID));
		String transectionId = json.get(ServiceConstants.RZPAY_TRANSECTION_ID);
		Optional<AccountTransection> acountTransection = transectionRepository.findById(Long.parseLong(json.get(ServiceConstants.ID)));
		if (null != users && users.getIsActive() && null != transectionId && !Strings.isEmpty(transectionId) && acountTransection.isPresent()) { 
			AccountTransection actTransection = acountTransection.get();
			actTransection.setTransectionId(transectionId);
			actTransection.setStatus(Boolean.TRUE);
			actTransection.setRemarks("payment done of " + actTransection.getAmount() +" by user "+ users.getEmailId() + " with order id " + actTransection.getOrderId());
			Date validDate = convertToDateViaSqlDate(convertToLocalDateViaSqlDate(users.getValidationDate()).plusDays(ServiceConstants.PLAN_EXTENSION_DAYS));
			users.setValidationDate(validDate);
			usersRepository.saveAndFlush(users);
			transectionRepository.saveAndFlush(actTransection);
			return ResponseEntity.created(new URI("/api/user/completepayment/" + actTransection.getId())).body(actTransection);
		}
		return ResponseEntity.unprocessableEntity().body(json.get(ServiceConstants.EMAILID) +ServiceConstants.PAYMENT_COMPLETION_ERROR); 
		
	}
	
	@GetMapping("/getallpayment/{emailId}/{deviceId}")
	ResponseEntity<?> getPaymentByUsersId(@PathVariable String emailId,@PathVariable String deviceId) {
		Users user = usersRepository.validateDeviceId(emailId, deviceId);
		Optional<List<AccountTransection>> accountTransectionList = null;
		if(null != user && user.getIsActive()) 
		accountTransectionList = transectionRepository.findByUserId(user.getId());
		List<AccountTransection> acList = accountTransectionList.get().stream().filter(act -> act.getStatus()).collect(Collectors.toList());
//		log.info("found transection with userId" + user.getEmailId() + " count "+accountTransectionList.get().size());
		return accountTransectionList.map(response -> ResponseEntity.ok().body(acList))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	
	}
	
	@GetMapping("/logout/{emailId}/{deviceId}")
	ResponseEntity<?> doLogout(@PathVariable String emailId,@PathVariable String deviceId) {
		Users user = usersRepository.validateDeviceId(emailId, deviceId);
		user.setDeviceToken(Strings.EMPTY);
		usersRepository.saveAndFlush(user);
		user.setPwd(Strings.EMPTY);
		return  ResponseEntity.ok().body(user);	
	}
	
	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
	    return java.sql.Date.valueOf(dateToConvert);
	}
	
	public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}
}
