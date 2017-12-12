package com.geekhome.common.vo;

public enum ErrorCode {
	
	EXCEPTION("程序异常", "00001"),
	USER_NOT_EXIST("用户未注册", "00002"),
    VERIFY_CODE_WRONG("验证码错误","00003"),
    OLD_PWD_WRONG("旧密码错误","00004"),
    USERNAME_PASSWORD_WRONG("用户名或密码错误","00005"),
    TODAY_HAVE_SIGN("今日已签到","00006");

	private String errorMsg;
	private String errorCode;

	private ErrorCode(final String errorMsg, final String errorCode) {
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
