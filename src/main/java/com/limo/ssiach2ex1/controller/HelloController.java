package com.limo.ssiach2ex1.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

    @GetMapping("/hola")
    public String hola() throws Exception{
        Callable<String> task=()->SecurityContextHolder.getContext().getAuthentication().getName();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService= new DelegatingSecurityContextExecutorService(executorService);

        try {
            return "Hola, "+executorService.submit(task).get()+"!";
        }finally {
            executorService.shutdown();
        }

    }

    @GetMapping("/ciao")
    public String ciao() throws Exception{
        Callable<String> task=()->{
            return SecurityContextHolder.getContext().getAuthentication().getName();
        };


        ExecutorService e = Executors.newCachedThreadPool();
        try{
            return "Ciao, "+e.submit(new DelegatingSecurityContextCallable<String>(task)).get()+"!";
        }finally {
            e.shutdown();
        }

    }

    @Async
    @GetMapping("/bye")
    public void goodbye() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        System.out.println(username);
    }

    @GetMapping("/hello")
    public String hello() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        return "Hello, "+authentication.getName()+"!";
    }

    @GetMapping("/happy")
    public String happy(Authentication authentication) {
        return "happy, "+authentication.getName()+"!";
    }



}
