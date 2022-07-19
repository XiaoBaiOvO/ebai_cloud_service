package com.ebai.ebai_cloud_service.annotation;

//import javassist.bytecode.SignatureAttribute;
import org.aspectj.lang.ProceedingJoinPoint;
        import org.aspectj.lang.annotation.*;
        import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect // FOR AOP
@Order(-99) // 控制多个Aspect的执行顺序，越小越先执行, 当然也可以不写这注解, 对于写和不写@order的两个切面, 有@order的优先于无@order的执行; 都有@order时, 越小越执先执行
@Component
public class TestAspect {

    @Pointcut("execution(* com.ebai.ebai_cloud_service.annotation.*.*(..))")
    public void point() { }


//    // @Before可以有两者写法, @annotation(形参test)
//    @Before("@annotation(MyAnnotation)")// 拦截被TestAnnotation注解的方法；如果你需要拦截指定package指定规则名称的方法，可以使用表达式execution(...)，具体百度一下资料一大堆
//    public void beforeTest(JoinPoint point, MyAnnotation MyAnnotation) throws Throwable {
//        System.out.println("beforeTest:" + MyAnnotation.value());   // 直接获取注解参数
//    }
//

    @After("@annotation(myAnnotation)")
    public void afterTest( MyAnnotation myAnnotation) {
        System.out.println("注解入参:" + myAnnotation.value());
    }


    // 可以控制方法运行, 修改入参和返回值
    @Around("@annotation(test)")   // test表示aroundTest方法中的test入参
    public Object aroundTest(ProceedingJoinPoint pjp, MyAnnotation test) throws Throwable {
//        System.out.println("afterTest:" + mq.value());
        // 获取入参并修改
        Object[] args = pjp.getArgs();
        args[0] = "";
        // 传入修改后的参数, 并继续执行
        Object res = pjp.proceed(args);
        // 修改返回值
        return res.toString() + res.toString();
    }


/*
   // 指定切面
   @Pointcut("@annotation(com.great.annotation.TestAnnotation)")
    public void annotationPointCut() {
    }
   // @Before可以有两者写法, @annotation(函数名annotationPointCut)
   @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);   // 获取指定注解实例
        System.out.println("打印：" + annotation.value() + " 前置日志1");   // 获取注解实例的参数
    }
    @After("annotationPointCut()")
    public void afterTTT(JoinPoint point) {
        MethodSignature sign = (MethodSignature) point.getSignature();
        Method method = sign.getMethod();
        TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);  // 获取指定注解实例
        System.out.println("打印自带参数：" + annotation.value() + " 后置日志1");  // 获取注解实例的参数
    }
*/

}