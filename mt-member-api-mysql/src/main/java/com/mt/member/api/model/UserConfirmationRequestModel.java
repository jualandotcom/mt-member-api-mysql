package com.mt.member.api.model;

import javax.validation.constraints.NotNull;

import com.mt.member.api.model.RequestModel;

public class UserConfirmationRequestModel extends RequestModel {

	@NotNull(message = "Uid is not null")
	private String tbuUid;

	public String getTbuUid() {
		return tbuUid;
	}

	public void setTbuUid(String tbuUid) {
		this.tbuUid = tbuUid;
	}
}
