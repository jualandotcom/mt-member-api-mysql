package com.mt.member.api.model;

import com.mt.member.api.db.entity.TbUser;

public class UserConfirmationResponseModel extends ResponseModel {
	
	public UserConfirmationResponseModel(UserConfirmationRequestModel requestModel) {
		super(requestModel);
	}
	
	private TbUser tbUsers;

	public TbUser getTbUsers() {
		return tbUsers;
	}

	public void setTbUsers(TbUser tbUsers) {
		this.tbUsers = tbUsers;
	}
}
