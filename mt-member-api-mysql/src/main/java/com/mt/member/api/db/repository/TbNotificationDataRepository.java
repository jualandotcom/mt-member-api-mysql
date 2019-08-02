package com.mt.member.api.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mt.member.api.db.entity.TbNotificationData;

public interface TbNotificationDataRepository extends JpaRepository<TbNotificationData, Integer> {
	
}