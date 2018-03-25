package org.we5.waterplant.exception;

public class WaterPlantException extends RuntimeException{
	
	String errorCode;
	String errorMessage;
	
	public WaterPlantException()
	{
	}
	
	public WaterPlantException(String message)
	{
		super(message);
		this.errorMessage=message;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
