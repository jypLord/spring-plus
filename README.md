# SPRING PLUS
## LEVEL 1-2 JWT의 이해
USER 도메인에 닉네임 필드를 추가하였습니다. 그에 따라 JWT에도 추가 정보를 넣기 위해 JWT.builder() 의 claim()을 사용하였습니다.

```java
public String createToken(Long userId, String email, UserRole userRole, String nickName) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(String.valueOf(userId))
                        .claim("email", email)
                        .claim("userRole", userRole)
                        .claim("nickName", nickName)
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME))
                        .setIssuedAt(date) 
                        .signWith(key, signatureAlgorithm) 
                        .compact();
    }
```

- 프론트 뿐만 아니라 백엔드에서도 토큰 정보를 활용하기 위해 JWT 필터에 있는 JWT 토큰 정보 저장 로직에 아래를 추가했습니다.
```httpRequest.setAttribute("nickName" , nickName)```
링크:  https://github.com/jypLord/spring-plus/blob/level-1-2/src/main/java/org/example/expert/config/JwtFilter.java


- 또, 로그인한 유저의 정보를 자동으로 가져오기 위해 HandlerMethodArgumentResolver의 구현체와 커스텀 어노테이션 @Auth를 연동하여 같이 닉네임을 가져오기 위해 resolveArgument 에 다음을 추가했습니다.
   ```String nickName = (String) request.getAttribute("nickName");```
링크:  https://github.com/jypLord/spring-plus/blob/level-1-2/src/main/java/org/example/expert/config/AuthUserArgumentResolver.java


- 나머지 기타 dto에도 nickName을 추가하였습니다.




