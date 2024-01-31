package com.tagir.project_management_api.exceptions;

public class UniqueUserProjectException extends RuntimeException{
    public UniqueUserProjectException(){
        super("This project already manage by this user");
    }
}
