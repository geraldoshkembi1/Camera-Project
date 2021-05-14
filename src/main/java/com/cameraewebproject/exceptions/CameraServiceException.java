package com.cameraewebproject.exceptions;

public class CameraServiceException  extends RuntimeException{
    private static final long serialVersionID = 1L;

    public CameraServiceException(String message){
        super(message);
    }
}
