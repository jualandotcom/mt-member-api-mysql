package com.mt.member.api.db.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tb_notifications database table.
 * 
 */
@Entity
@Table(name="tb_notification")
@NamedQuery(name="TbNotification.findAll", query="SELECT t FROM TbNotification t")
public class TbNotification implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String statusActive = "active";
	public final static String statusDisable = "disable";

	@Id
	@Column(name="tbn_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tbuId;

	@Column(name="tbn_create_date")
	private Date tbuCreateDate;

	@Column(name="tbn_create_id")
	private Integer tbuCreateId;

	@Column(name="tbn_update_date")
	private Date tbuUpdateDate;

	@Column(name="tbn_update_id")
	private Integer tbuUpdateId;

	@Column(name="tbn_html")
	private String tbuHtml;

	@Column(name="tbn_status")
	private String tbuStatus;

	public Integer getTbuId() {
		return tbuId;
	}

	public void setTbuId(Integer tbuId) {
		this.tbuId = tbuId;
	}

	public Date getTbuCreateDate() {
		return tbuCreateDate;
	}

	public void setTbuCreateDate(Date tbuCreateDate) {
		this.tbuCreateDate = tbuCreateDate;
	}

	public Integer getTbuCreateId() {
		return tbuCreateId;
	}

	public void setTbuCreateId(Integer tbuCreateId) {
		this.tbuCreateId = tbuCreateId;
	}

	public Date getTbuUpdateDate() {
		return tbuUpdateDate;
	}

	public void setTbuUpdateDate(Date tbuUpdateDate) {
		this.tbuUpdateDate = tbuUpdateDate;
	}

	public Integer getTbuUpdateId() {
		return tbuUpdateId;
	}

	public void setTbuUpdateId(Integer tbuUpdateId) {
		this.tbuUpdateId = tbuUpdateId;
	}

	public String getTbuHtml() {
		return tbuHtml;
	}

	public void setTbuHtml(String tbuHtml) {
		this.tbuHtml = tbuHtml;
	}

	public String getTbuStatus() {
		return tbuStatus;
	}

	public void setTbuStatus(String tbuStatus) {
		this.tbuStatus = tbuStatus;
	}

}