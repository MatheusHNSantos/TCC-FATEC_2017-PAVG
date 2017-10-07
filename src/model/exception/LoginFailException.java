package model.exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author felipemantoan
 */
public class LoginFailException extends Exception{
    
    LoginFailException(String message) {
        super(message);
    }
    
}
