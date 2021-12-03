package com.system.bcs.dbdao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.system.bcs.beans.Coupon;
import com.system.bcs.repo.CouponRepo;

@Repository
public class CouponDBDAO {

	@Autowired
	private CouponRepo couponRepo;

	public void addCoupon(Coupon coupon) {
		couponRepo.save(coupon);
	}

	public void updateCoupon(Coupon coupon) {
		couponRepo.saveAndFlush(coupon);
	}

	public void deleteCoupon(Coupon coupon) {
		couponRepo.delete(coupon);
	}

	public void deleteCouponById(int id) {
		couponRepo.deleteById(id);
	}

	public List<Coupon> getAllCoupons() {
		return couponRepo.findAll();
	}

	public Optional<Coupon> getOneCoupon(int couponID) {
		return couponRepo.findById(couponID);
	}

	public void addCouponPurchase(int customerID, int couponID) {
		couponRepo.addCouponPurchase(customerID, couponID);
	}

	public void deleteCouponPurchase(int customerID, int couponID) {
		couponRepo.deleteCouponPurchase(customerID, couponID);
	}

	public void deleteCouponPurchaseByCouponID(int couponID) {
		couponRepo.deleteCouponPurchaseByCouponID(couponID);
	}

}
