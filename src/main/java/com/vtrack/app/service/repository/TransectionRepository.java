package com.vtrack.app.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vtrack.app.service.entity.AccountTransection;

/**
 * @author jayant
 *
 */
public interface TransectionRepository extends JpaRepository<AccountTransection, Long>{
	Optional<List<AccountTransection>> findByUserId(Long userId);
	List<AccountTransection> findByOrderId(String orderId);
	}
