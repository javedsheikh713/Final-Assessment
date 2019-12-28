package fse.assesment.assignment.exception;

import java.util.Objects;

import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import fse.assesment.assignment.response.Response;


@ControllerAdvice
@RestController
public class ErrorHandler {

	Logger log = LoggerFactory.getLogger(ErrorHandler.class);
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler({AppException.class,SystemException.class})
	@ResponseBody
	public final Response handleUserNotFoundException(AppException ex,WebRequest request) {
		Response response=new Response();
		
		if(ex instanceof AppException) {
		log.error("Exception :{} ,Cause :{} ", ex.getErrorCode(), ex.getMessage());
		ErrorCodes errorCodes=ex.getErrorCode();
		if(Objects.nonNull(ex.getErrMsgArgs()) && ex.getErrMsgArgs().length >0) {
			response.setError(errorCodes);
			
		}else if(Objects.nonNull(errorCodes)){
			response.setError(errorCodes);
		}
		}else {
			response.setError(ErrorCodes.SYS_DEFAULT_ERR);
		}
		
		return response;
	}
}
