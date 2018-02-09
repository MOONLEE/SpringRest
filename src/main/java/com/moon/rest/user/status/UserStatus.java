package com.moon.rest.user.status;

public enum UserStatus {
	EXIST_USER("10", "이미 존재하는 이메일입니다."),
	NOTEXIST_USER("20", "존재하지않는 이메일입니다."),
	INVALID_ID("21", "유효하지 않는 ID입니다."),
	INVALID_EMAIL("22", "유효하지 않는 이메일입니다."),
	INVALID_PASS("23", "유효하지 않는 패스워드입니다."),
	CREATE_SUCCESS("90", "가입이 완료되었습니다."),
	LOGIN_SUCCESS("91", "로그인이 완료되었습니다."),
	ERROR("99", "예상치 못한 에러가 발생하였습니다.\n 관리자에게 문의 해주세요 ㅜ");
	
	private String status;
	private String message;
	
	UserStatus(String status, String message) {
		this.status = status;
		this.message = message;
		
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}