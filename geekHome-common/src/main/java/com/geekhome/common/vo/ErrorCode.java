package com.geekhome.common.vo;

public enum ErrorCode {
	
	EXCEPTION("程序异常", "00001"),
	USER_NOT_EXIST("用户未注册", "00002");

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
