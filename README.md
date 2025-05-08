# SPRING PLUS
## Cascade의 이해
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
