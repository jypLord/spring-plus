# SPRING PLUS
## 2-1 AOP의 이해
AOP는 메서드나 클래스의 동작을 가로채서 추가적인 로직을 시행하는 것입니다. 

@After은 어떤 메서드 실행 직후, @Before은 메서드 실행 직전에 실행할 다른 메서드에 붙이는 것입니다.
원래 의도대로 동작하게 하기 위해 @After -> @Before 교체하였습니다. 

```java
    @Before("execution(* org.example.expert.domain.user.controller.UserController.getUser(..))")
    public void logAfterChangeUserRole(JoinPoint joinPoint) {
        String userId = String.valueOf(request.getAttribute("userId"));
        String requestUrl = request.getRequestURI();
        LocalDateTime requestTime = LocalDateTime.now();

        log.info("Admin Access Log - User ID: {}, Request Time: {}, Request URL: {}, Method: {}",
                userId, requestTime, requestUrl, joinPoint.getSignature().getName());
    }
```
## 2-2 Cascade의 이해
Cascade는 부모 엔티티의 작업(Persist, Remove 등)이 자식 엔티티에 자동 전파되도록 설정하는 옵션입니다.

```java
public class Todo extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;
    private String weather;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private List<Manager> managers = new ArrayList<>();

```
Todo 의 담당자들을 정하는 것으로 보이기 때문에, 할 일(부모)이 삭제됐을 때 담당자(자식)도 같이 삭제되도록 하였습니다.
