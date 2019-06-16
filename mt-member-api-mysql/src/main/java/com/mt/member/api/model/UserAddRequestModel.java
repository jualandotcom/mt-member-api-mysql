package com.mt.member.api.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.mt.member.api.model.RequestModel;

public class UserAddRequestModel extends RequestModel {

	@NotNull(message = "Email is not null")
	private String tbuEmail;

	@NotNull(message = "Password is not null")
	private String tbuPassword;

	@NotNull(message = "First name is not null")
	private String tbuFirstname;

	@NotNull(message = "Last name is not null")
	private String tbuLastname;

	private String tbuMiddlename;

	@NotNull(message = "Mobile phone is not null")
	private String tbuMobilePhone;

	@NotNull(message = "Place of birth is not null")
	private String tbuPlaceOfBirth;

	@NotNull(message = "Date of birth is not null")
	@Pattern(message="Date of birth ", regexp="^(\\d{4})(?:-([0]\\d|[1][0-2]))(?:-([0-2]\\d|[3][01]))(?:T([01]\\d|2[0-3]))(?::([0-5]\\d))(?::([0-5]\\d)(?:\\.(\\d{1,7}?)|)|)$")
	private String tbuDateOfBirth;

	public String getTbuEmail() {
		return tbuEmail;
	}

	public void setTbuEmail(String tbuEmail) {
		this.tbuEmail = tbuEmail;
	}

	public String getTbuPassword() {
		return tbuPassword;
	}

	public void setTbuPassword(String tbuPassword) {
		this.tbuPassword = tbuPassword;
	}

	public String getTbuFirstname() {
		return tbuFirstname;
	}

	public void setTbuFirstname(String tbuFirstname) {
		this.tbuFirstname = tbuFirstname;
	}

	public String getTbuLastname() {
		return tbuLastname;
	}

	public void setTbuLastname(String tbuLastname) {
		this.tbuLastname = tbuLastname;
	}

	public String getTbuMiddlename() {
		return tbuMiddlename;
	}

	public void setTbuMiddlename(String tbuMiddlename) {
		this.tbuMiddlename = tbuMiddlename;
	}

	public String getTbuMobilePhone() {
		return tbuMobilePhone;
	}

	public void setTbuMobilePhone(String tbuMobilePhone) {
		this.tbuMobilePhone = tbuMobilePhone;
	}

	public String getTbuPlaceOfBirth() {
		return tbuPlaceOfBirth;
	}

	public void setTbuPlaceOfBirth(String tbuPlaceOfBirth) {
		this.tbuPlaceOfBirth = tbuPlaceOfBirth;
	}

	public String getTbuDateOfBirth() {
		return tbuDateOfBirth;
	}

	public void setTbuDateOfBirth(String tbuDateOfBirth) {
		this.tbuDateOfBirth = tbuDateOfBirth;
	}

}
