package com.moon.rest.user.code;

public enum UserStatus {
	EXIST_USER("10", "이미 존재하는 아이디입니다."),
	CREAT_SUCCESS("90", "가입이 완료되었습니다."),
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