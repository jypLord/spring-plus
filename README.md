# SPRING PLUS
## Spring Security의 이해
0. Security Config
![image](https://github.com/user-attachments/assets/442d3c63-977e-4018-a67f-fcea5e8d9e6d)
Security의 Config파일을 만들었습니다.
csrf와 formlogin, HttpBasic을 disable 하였습니다.
링크: https://github.com/jypLord/spring-plus/blob/level-2-4/src/main/java/org/example/expert/config/security/SecurityConfig.java


2. 로그인 필터 구현 
![image](https://github.com/user-attachments/assets/474393cb-866e-4605-a6ae-75d46e58272b)
위와 같이 formLogin을 disable했기 떄문에 UsernamePasswordFilter를 상속받은 로그인필터를 만들었습니다.

이 API는 username이 아닌 email을 식별자로 하고있으므로 EmailPasswordAuthenticationToken을 생성하여 인증을 했습니다.
링크: https://github.com/jypLord/spring-plus/blob/level-2-4/src/main/java/org/example/expert/config/security/LoginFilter.java

3. EmailPasswordAuthenticationToken 생성
![image](https://github.com/user-attachments/assets/9d1068e3-ce19-4fff-8e89-655540ae09c7)

 UsernamePasswordAuthenticationToken이 상속받고있는 AbstractAuthenticationToken을 그대로 상속받았습니다.
링크: https://github.com/jypLord/spring-plus/blob/level-2-4/src/main/java/org/example/expert/config/security/EmailPasswordAuthenticationToken.java

4. 커스텀 인증객체 구현 (LoginAuthenticationProvider)
![image](https://github.com/user-attachments/assets/f14f28d5-716f-4e6d-915e-2ddb9e469724)
UsernamePasswordAuthenticationFilter의 전담 인증객체인 AbstractUserDetailsAuthenticationProvider을 상속받아 구현하였습니다.

 ```retrieveUser()```에서 기존에 findUserByUsername이던 것을 findUserByEmail로 바꾸어 더이상 username을 키로 인증정보를 가져오지 않습니다.

![image](https://github.com/user-attachments/assets/3d4b9ed1-bb20-4dba-89d9-6807f245816f)
위 support() 메서드를 수정하는 것으로 커스텀 인증토큰인 EmailPasswordAuthenticationToken을 인식할 수 있게 하였습니다.
링크: https://github.com/jypLord/spring-plus/blob/level-2-4/src/main/java/org/example/expert/config/security/LoginAuthenticationProvider.java

5. Email을 키로 User정보로 불러오는 UserDetailsService
![image](https://github.com/user-attachments/assets/656cdb9a-176b-4f2b-a764-e079960fc597)
바로 위에서 설명했던 findUserByEmail() 이 담긴 클래스입니다.
링크: https://github.com/jypLord/spring-plus/blob/level-2-4/src/main/java/org/example/expert/config/security/CustomUserDetailsService.java

6. UserDetails
평범한 UserDetails입니다.
![image](https://github.com/user-attachments/assets/2cad453a-75a2-4d85-9323-178f2fe48e31)
링크: https://github.com/jypLord/spring-plus/blob/level-2-4/src/main/java/org/example/expert/config/security/CustomUserDetails.java
