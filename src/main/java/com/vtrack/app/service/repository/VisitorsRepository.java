package com.vtrack.app.service.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.vtrack.app.service.entity.Visitors;

/**
 * @author jayant
 *
 */
public interface VisitorsRepository extends JpaRepository<Visitors, Long>{
	List<Visitors> findByEmailId(String emailId);
	List<Visitors> findByphone(String phone);
	Optional<List<Visitors>> findAllByVendor(@Param("vendorId") String vendorId,@Param("createdBy") Long createdBy);
	Optional<List<Visitors>> findAllByVendorName(@Param("vendorId") String vendorId,@Param("createdBy") Long createdBy, @Param("visitorName") String visitorName);
	Optional<List<Visitors>> findAllByVendorAndDate(@Param("vendorId") String vendorId, @Param("visitSDate") Date visitSDate,@Param("visitEDate") Date visitEDate);
}
