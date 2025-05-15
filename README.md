# SPRING PLUS
## 2-1 Cascade의 이해

OneToMany 관계에서 
Todo를 저장할 때 연관된 Manager들도 자동으로 저장될 수 있도록 Cascade.PERSIST를 설정해주었고, 
Todo를 삭제할 때 연관된 Manager들도 자동으로 삭제될 수 있도록 OrphanRemoval =true를 설정해주었습니다.
![image](https://github.com/user-attachments/assets/bb958bda-12dc-45b4-9b76-e3d4a58282fa)

링크: https://github.com/jypLord/spring-plus/blob/level2-1/src/main/java/org/example/expert/domain/todo/entity/Todo.java
