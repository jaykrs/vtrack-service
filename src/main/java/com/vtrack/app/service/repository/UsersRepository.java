package com.vtrack.app.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vtrack.app.service.entity.Users;

/**
 * @author jayant
 *
 */
public interface UsersRepository extends JpaRepository<Users, Long>{
	Users findByEmailId(String emailId);
	Users findByDeviceToken(String deviceToken);
	Users validatePwd(@Param("email") String emailId,@Param("pwd") String pwd);
	@Transactional
	@Modifying
	int forgetPwd(@Param("email") String emailId,@Param("pwd") String pwd);
	@Transactional
	@Modifying
	int activateUser(@Param("email") String emailId,@Param("activeInd") Boolean activeInd);
}
