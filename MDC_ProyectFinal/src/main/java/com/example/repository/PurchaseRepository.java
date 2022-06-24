package com.example.repository;

import java.util.Date;
import java.util.List;

import com.example.entities.Purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	List<Purchase> findByUserId(long id);
	
	@Query("select sum(p.amount) from Purchase p where :date1 <= p.date and p.date < :date2")
	public Float purchaseReport(@Param("date1") Date date1, @Param("date2") Date date2);
}
