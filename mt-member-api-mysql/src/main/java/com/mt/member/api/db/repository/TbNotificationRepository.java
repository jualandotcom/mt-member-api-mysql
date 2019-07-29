package com.mt.member.api.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mt.member.api.db.entity.TbNotification;

public interface TbNotificationRepository extends JpaRepository<TbNotification, Integer> {
	
}