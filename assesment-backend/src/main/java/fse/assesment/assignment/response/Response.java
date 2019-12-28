package fse.assesment.assignment.response;

import java.io.Serializable;

import fse.assesment.assignment.common.ErrorData;
import fse.assesment.assignment.exception.ErrorCodes;

public class Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String status;
	private  ErrorData error;
	private String successMessage;
	
	public Response() {
		this.status="SUCCESS";
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ErrorData getError() {
		return error;
	}
	public void setError(ErrorCodes errorCodes) {
		this.error=new ErrorData(errorCodes.getCode(),errorCodes.getDescription());
		this.status="ERROR";
	}


	public String getSuccessMessage() {
		return successMessage;
	}


	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	
	
}
