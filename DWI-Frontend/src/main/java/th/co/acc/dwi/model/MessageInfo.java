package th.co.acc.dwi.model;

import java.io.Serializable;

public class MessageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7198307424844571252L;
	String code;
	String message;
	
	public MessageInfo() {
	}

	public MessageInfo(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageService [code=" + code + ", message=" + message + "]";
	}

}
