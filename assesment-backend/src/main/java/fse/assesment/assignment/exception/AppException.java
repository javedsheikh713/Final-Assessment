package fse.assesment.assignment.exception;

import java.io.Serializable;

/**
 * 
 * @author Javed
 *
 */
public class AppException extends RuntimeException implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private ErrorCodes errorCode;
	private Throwable err;
	private boolean isTraceEnabled=false;
	private Object[] errMsgArgs;
	
	public AppException(ErrorCodes error) {
		this.errorCode=error;
	}
	
	public AppException(ErrorCodes error,Object... errMsgArgs) {
		this.errorCode=error;
		this.errMsgArgs=errMsgArgs;
	}
	
	public AppException(ErrorCodes error,boolean isTraceEnabled ) {
		this.errorCode=error;
		this.isTraceEnabled=isTraceEnabled;
	}
	
	public AppException(ErrorCodes error,Throwable err ) {
		this.errorCode=error;
		this.err=err;
	}

	public ErrorCodes getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodes errorCode) {
		this.errorCode = errorCode;
	}

	public Throwable getErr() {
		return err;
	}

	public void setErr(Throwable err) {
		this.err = err;
	}

	public boolean isTraceEnabled() {
		return isTraceEnabled;
	}

	public void setTraceEnabled(boolean isTraceEnabled) {
		this.isTraceEnabled = isTraceEnabled;
	}

	public Object[] getErrMsgArgs() {
		return errMsgArgs;
	}

	public void setErrMsgArgs(Object[] errMsgArgs) {
		this.errMsgArgs = errMsgArgs;
	}
	
	
	

}
