package com.bridgelabz.AddressBook.aspectService;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import com.bridgelabz.AddressBook.model.AddressBookModel;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class MyAspectjService {

    @Pointcut("execution(* com.bridgelabz.AddressBook.service.AddressBookService.*(..))")
    public void loggingPointCut() {
    }

    // @Before(value = "execution(*
    // com.bridgelabz.AddressBook.service.AddressBookService.*(..))")
    // public void Before(JoinPoint joinPoint) {
    // System.out.println("Before : " + joinPoint.getSignature().getName());
    // }

    @Before("loggingPointCut()")
    public void Before(JoinPoint joinPoint) {
        System.out.println("Before : " + joinPoint.getSignature().getName());
    }

    @org.aspectj.lang.annotation.After(value = "execution(* com.bridgelabz.AddressBook.service.AddressBookService.*(..))")
    public void After(JoinPoint joinPoint) {
        System.out.println("After : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "execution(* com.bridgelabz.AddressBook.service.AddressBookService.*(..))", returning = "addressBookModel")
    public void afterReturn(JoinPoint joinPoint, AddressBookModel addressBookModel) {
        log.info("After method invoke : " + addressBookModel);

    }

}
