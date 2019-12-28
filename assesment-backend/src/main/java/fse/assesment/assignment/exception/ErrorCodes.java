package fse.assesment.assignment.exception;

public enum ErrorCodes {

	NO_RECORDS("CAP_001","No Records Found"),
	INVALID_REQUEST("CAP_002","Invalid Request"),
	TASK_ALREADY_EXIST("CAP_003","Task Already Exist"),
	PROJECT_ALREADY_EXIST("CAP_004","Project Already Exist"),
	USER_ALREADY_EXIST("CAP_005","User Already Exist"),
	INVALID_PROJECT("CAP_006","Please choose correct project"),
	INVALID_TASK("CAP_007","Please choose correct Task"),
	PARENT_TASK_ALREADY_EXIST("CAP_008","Parent Task Already Exist"),
	MANAGER_NOT_EXIST("CAP_009","Manager does not exist. Please choose correct one"),
	MANDATORY_FIELD_MISSING("CAP_010","Mandatory Fields are missing"),
	SYS_DEFAULT_ERR("SYS_001","System is not available");
	
	private String code;
	private String description;
	
	private ErrorCodes(String code,String description) {
		this.code=code;
		this.description=description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
