# SPRING PLUS
## LEVEL 1-5 AOP의 이해

@After() 은 괄호 안의 메서드가 실행된 이후에 해당 메서드를 실행하는 AOP 어노테이션입니다. 
메서드가 실행되기 전에 실행하고싶다면 @Before을 붙여야 합니다.


```java
    @Before("execution(* org.example.expert.domain.user.controller.UserController.getUser(..))")
    public void logAfterChangeUserRole(JoinPoint joinPoint) {
        String userId = String.valueOf(request.getAttribute("userId"));
        String requestUrl = request.getRequestURI();
        LocalDateTime requestTime = LocalDateTime.now();

        log.info("Admin Access Log - User ID: {}, Request Time: {}, Request URL: {}, Method: {}",
                userId, requestTime, requestUrl, joinPoint.getSignature().getName());
    }
}

```
