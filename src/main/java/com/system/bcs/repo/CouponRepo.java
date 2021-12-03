package com.system.bcs.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.system.bcs.beans.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO bcs.customer_coupons (customer_id, coupons_id) VALUES (:customerID, :couponID)", nativeQuery = true)
	void addCouponPurchase(int customerID, int couponID);

	@Transactional
	@Modifying
	@Query(value = "DELETE from bsc.customer_coupons WHERE customer_id=:customerID AND coupons_id=:couponID", nativeQuery = true)
	void deleteCouponPurchase(int customerID, int couponID);

	@Transactional
	@Modifying
	@Query(value = "DELETE from bsc.customer_coupons WHERE coupons_id=:couponID", nativeQuery = true)
	void deleteCouponPurchaseByCouponID(int couponID);

	@Transactional
	@Modifying
	@Query(value = "SELECT * from bsc.customer_coupons", nativeQuery = true)
	List<Object[]> getCustomersVsCoupons();
}
