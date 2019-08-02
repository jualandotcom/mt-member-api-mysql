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
 * The persistent class for the tb_notification_datas database table.
 * 
 */
@Entity
@Table(name="tb_notification_data")
@NamedQuery(name="TbNotificationData.findAll", query="SELECT t FROM TbNotificationData t")
public class TbNotificationData implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String statusSend = "send";
	public final static String statusError = "error";

	@Id
	@Column(name="tbnd_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tbndId;

	@Column(name="tbnd_create_date")
	private Date tbndCreateDate;

	@Column(name="tbnd_create_id")
	private Integer tbndCreateId;

	@Column(name="tbnd_update_date")
	private Date tbndUpdateDate;

	@Column(name="tbnd_update_id")
	private Integer tbndUpdateId;
	
	@Column(name="tbn_id")
	private Integer tbnId;
	
	@Column(name="tbnd_to")
	private String tbndTo;
	
	@Column(name="tbnd_subject")
	private String tbndSubject;

	@Column(name="tbnd_html")
	private String tbndHtml;

	@Column(name="tbnd_status")
	private String tbndStatus;

	public Integer getTbndId() {
		return tbndId;
	}

	public void setTbndId(Integer tbndId) {
		this.tbndId = tbndId;
	}

	public Date getTbndCreateDate() {
		return tbndCreateDate;
	}

	public void setTbndCreateDate(Date tbndCreateDate) {
		this.tbndCreateDate = tbndCreateDate;
	}

	public Integer getTbndCreateId() {
		return tbndCreateId;
	}

	public void setTbndCreateId(Integer tbndCreateId) {
		this.tbndCreateId = tbndCreateId;
	}

	public Date getTbndUpdateDate() {
		return tbndUpdateDate;
	}

	public void setTbndUpdateDate(Date tbndUpdateDate) {
		this.tbndUpdateDate = tbndUpdateDate;
	}

	public Integer getTbndUpdateId() {
		return tbndUpdateId;
	}

	public void setTbndUpdateId(Integer tbndUpdateId) {
		this.tbndUpdateId = tbndUpdateId;
	}

	public Integer getTbnId() {
		return tbnId;
	}

	public void setTbnId(Integer tbnId) {
		this.tbnId = tbnId;
	}

	public String getTbndTo() {
		return tbndTo;
	}

	public void setTbndTo(String tbndTo) {
		this.tbndTo = tbndTo;
	}

	public String getTbndSubject() {
		return tbndSubject;
	}

	public void setTbndSubject(String tbndSubject) {
		this.tbndSubject = tbndSubject;
	}

	public String getTbndHtml() {
		return tbndHtml;
	}

	public void setTbndHtml(String tbndHtml) {
		this.tbndHtml = tbndHtml;
	}

	public String getTbndStatus() {
		return tbndStatus;
	}

	public void setTbndStatus(String tbndStatus) {
		this.tbndStatus = tbndStatus;
	}
}