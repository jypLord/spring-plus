# SPRING PLUS
## Spring Security의 이해
0. Security Config
![image](https://github.com/user-attachments/assets/442d3c63-977e-4018-a67f-fcea5e8d9e6d)
Security의 Config파일을 만들었습니다.
csrf와 formlogin, HttpBasic을 disable 하였습니다.

2. 로그인 필터 구현 
![image](https://github.com/user-attachments/assets/474393cb-866e-4605-a6ae-75d46e58272b)
위와 같이 formLogin을 disable했기 떄문에 UsernamePasswordFilter를 상속받은 로그인필터를 만들었습니다.
