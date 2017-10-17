package com.geekhome.common.vo;

/**
 * @Description: markdown 上传图片vo
 * @author handx
 * @date 2017年10月13日 下午3:35:12
 * @version V1.0
 */
public class MarkdownUploadImage {

	private Integer success;// : 0 | 1, // 0 表示上传失败，1 表示上传成功
	private String message;// : "提示的信息，上传成功或上传失败及错误信息等。",
	private String url;// : "图片地址" // 上传成功时才返回

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MarkdownUploadImage(Integer success, String message, String url) {
		super();
		this.success = success;
		this.message = message;
		this.url = url;
	}

	public MarkdownUploadImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MarkdownUploadImage [success=" + success + ", message=" + message + ", url=" + url + "]";
	}

}
