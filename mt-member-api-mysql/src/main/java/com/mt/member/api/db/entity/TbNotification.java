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
	private Integer tbnId;

	@Column(name="tbn_create_date")
	private Date tbnCreateDate;

	@Column(name="tbn_create_id")
	private Integer tbnCreateId;

	@Column(name="tbn_update_date")
	private Date tbnUpdateDate;

	@Column(name="tbn_update_id")
	private Integer tbnUpdateId;
	
	@Column(name="tbn_code")
	private String tbnCode;
	
	@Column(name="tbn_subject")
	private String tbnSubject;

	@Column(name="tbn_html")
	private String tbnHtml;

	@Column(name="tbn_status")
	private String tbnStatus;

	public Integer getTbnId() {
		return tbnId;
	}

	public void setTbnId(Integer tbnId) {
		this.tbnId = tbnId;
	}

	public Date getTbnCreateDate() {
		return tbnCreateDate;
	}

	public void setTbnCreateDate(Date tbnCreateDate) {
		this.tbnCreateDate = tbnCreateDate;
	}

	public Integer getTbnCreateId() {
		return tbnCreateId;
	}

	public void setTbnCreateId(Integer tbnCreateId) {
		this.tbnCreateId = tbnCreateId;
	}

	public Date getTbnUpdateDate() {
		return tbnUpdateDate;
	}

	public void setTbnUpdateDate(Date tbnUpdateDate) {
		this.tbnUpdateDate = tbnUpdateDate;
	}

	public Integer getTbnUpdateId() {
		return tbnUpdateId;
	}

	public void setTbnUpdateId(Integer tbnUpdateId) {
		this.tbnUpdateId = tbnUpdateId;
	}

	public String getTbnCode() {
		return tbnCode;
	}

	public void setTbnCode(String tbnCode) {
		this.tbnCode = tbnCode;
	}

	public String getTbnSubject() {
		return tbnSubject;
	}

	public void setTbnSubject(String tbnSubject) {
		this.tbnSubject = tbnSubject;
	}

	public String getTbnHtml() {
		return tbnHtml;
	}

	public void setTbnHtml(String tbnHtml) {
		this.tbnHtml = tbnHtml;
	}

	public String getTbnStatus() {
		return tbnStatus;
	}

	public void setTbnStatus(String tbnStatus) {
		this.tbnStatus = tbnStatus;
	}

}