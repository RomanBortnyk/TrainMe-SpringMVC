package trainMe.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by romab on 11/26/16.
 */

@Aspect
public class Audience {


    @Pointcut("execution(* trainMe.aspects.Performance.perform(..))")
    public void performance() {}


//    @Around("performance()")
//    public void watchPerformance(ProceedingJoinPoint jp) {
//        try {
//            System.out.println("Silencing cell phones");
//            System.out.println("Taking seats");
//            jp.proceed();
//            System.out.println("CLAP CLAP CLAP!!!");
//        } catch (Throwable e) {
//            System.out.println("Demanding a refund");
//        }
//    }


    @Before("performance()")
    public void silenceCellPhones() {
        System.out.println("Silencing cell phones");
    }
    @Before("performance()")
    public void takeSeats() {
        System.out.println("Taking seats");
    }
    @AfterReturning("performance()")
    public void applause() {
        System.out.println("CLAP CLAP CLAP!!!");
    }
    @AfterThrowing("performance()")
    public void demandRefund() {
        System.out.println("Demanding a refund");
    }



}
