# SPRING PLUS
## LEVEL 2-2 N+1 문제
CommentController.

![image](https://github.com/user-attachments/assets/74ead1d3-dae6-4f7b-9c96-297ac05915df)

로그를 보면 User 테이블에 쿼리가 반복해서 나타나는 것으로 보입니다.

이 것은 엔티티에서 @ManyToOne 연관관계를 맺을 때 FetchType.LAZY 로 설정했을 때 일어날 수 있는 문제입니다.
이를 해결하기 위해 아래와 같이 JPQL로 ```JOIN FETCH``` 해주었습니다. 
```java
    @Query("SELECT c FROM Comment c JOIN FETCH c.user WHERE c.todo.id = :todoId")
    List<Comment> findByTodoIdWithUser(@Param("todoId") Long todoId);
```
