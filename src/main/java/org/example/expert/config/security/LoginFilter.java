package org.example.expert.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {

    String email = obtainEmail(request);
    String password = obtainPassword(request);

    Authentication authRequest = new EmailPasswordAuthenticationToken(email, password);

    return authenticationManager.authenticate(authRequest);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {

    SecurityContextHolder.getContext().setAuthentication(authResult);

    CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();

    Long id = userDetails.getId();
    String email = userDetails.getEmail();
    String nickName = userDetails.getNickName();
    String role = userDetails.getAuthorities().iterator().next().getAuthority();

    String token = jwtUtil.createToken(id, email, role, nickName);

    response.addHeader("Authorization", "Bearer " + token);

  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException failed) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
  }

  public String obtainEmail(HttpServletRequest request) {
    return request.getParameter("email");
  }
}
