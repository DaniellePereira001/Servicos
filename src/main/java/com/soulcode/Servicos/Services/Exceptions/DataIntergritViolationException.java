package com.soulcode.Servicos.Services.Exceptions;

public class DataIntergritViolationException extends RuntimeException{

    public  DataIntergritViolationException(String msg){
        super(msg);
    }
}
