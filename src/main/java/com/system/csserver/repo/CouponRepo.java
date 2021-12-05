package com.system.csserver.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.system.csserver.beans.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

	@Transactional
	@Modifying
//	@Query(value = "INSERT INTO bcs.customers_coupons (customers_id, coupons_id) VALUES (:customerID, :couponID)", nativeQuery = true)
	@Query(value = "INSERT INTO customers_coupons (customer_id, coupons_id) VALUES (:customerID, :couponID)", nativeQuery = true)
	void addCouponPurchase(int customerID, int couponID);

	@Transactional
	@Modifying
//	@Query(value = "DELETE FROM bsc.customers_coupons WHERE customers_id=:customerID AND coupons_id=:couponID", nativeQuery = true)
	@Query(value = "DELETE FROM customers_coupons WHERE customer_id=:customerID AND coupons_id=:couponID", nativeQuery = true)
	void deleteCouponPurchase(int customerID, int couponID);

	@Transactional
	@Modifying
//	@Query(value = "DELETE FROM bsc.customers_coupons WHERE coupons_id=:couponID", nativeQuery = true)
	@Query(value = "DELETE FROM customers_coupons WHERE coupons_id=:couponID", nativeQuery = true)
	void deleteCouponPurchaseByCouponID(int couponID);

	@Transactional
	@Modifying
//	@Query(value = "SELECT * FROM bsc.customers_coupons", nativeQuery = true)
	@Query(value = "SELECT * FROM customers_coupons", nativeQuery = true)
	List<Object[]> getCustomersVsCoupons();
}
