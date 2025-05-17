package org.example.expert.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class SecurityJwtFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authorization = request.getHeader("Authorization");

    if (authorization == null || !authorization.startsWith("Bearer ")) {
      // 비로그인 사용자 일 수도 있기 때문에 그냥 넘김
      filterChain.doFilter(request, response);
      return;
    }

    String token = jwtUtil.substringToken(authorization);

    if (jwtUtil.extractClaims(token).getExpiration().before(new Date())) {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.getWriter().write("토큰이 만료되었습니다.");
      return;
    }
    // 토큰에서 유저 정보 추출하기
    String id = jwtUtil.extractClaims(token).getSubject();
    String email = jwtUtil.extractClaims(token).get("email", String.class);
    UserRole userRole = jwtUtil.extractClaims(token).get("userRole", UserRole.class);
    String nickname = jwtUtil.extractClaims(token).get("nickname", String.class);

    // 토큰 정보를 토대로 엔티티 객체 생성
    User user = new User(id, email, userRole, nickname);

    // userDetails wrap
    CustomUserDetails userDetails = new CustomUserDetails(user);

    // 식별자와 비밀번호 매칭을 위한 토큰 생성
    Authentication authentication = new EmailPasswordAuthenticationToken(userDetails, null,
        userDetails.getAuthorities());

    // 컨텍스트 홀더에 정보 저장
    SecurityContextHolder.getContext().setAuthentication(authentication);

    filterChain.doFilter(request, response);
  }
}
