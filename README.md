# SPRING PLUS
## LEVEL 1-1 @Transactional의 이해

@Transactional 의 속성 중 readOnly 는 수정, 삭제, 삽입에 제한을 거는 속성입니다.

기존 Class 단위로 @Transactional(readOnly = true) 가 걸려있어 아래의 에러가 걸렸습니다.

>jakarta.servlet.ServletException: Request processing failed: org.springframework.orm.jpa.JpaSystemException: could not execute statement [Connection is read-only. Queries leading to data modification are not allowed] [insert into todos (contents,created_at,modified_at,title,user_id,weather) values (?,?,?,?,?,?)]

따라서 클래스 단위의 @Transactional을 삭제하고, 문제의 메서드에만 @Transactional을 걸었습니다.

클래스 단위의 @Transactional(readOnly = true)를 유지하고 해당 메서드만 false로 걸 수도 있지만, 인텔리제이의 경고가 눈에 거슬려 일일히 @Transactional(readOnly=true)를 거는 것으로 하였습니다.

아래는 변경된 자료입니다.

링크: https://github.com/jypLord/spring-plus/blob/level-1-1/src/main/java/org/example/expert/domain/todo/service/TodoService.java
![image](https://github.com/user-attachments/assets/4f89ef43-830f-480b-81fc-b076089ca1a4)
