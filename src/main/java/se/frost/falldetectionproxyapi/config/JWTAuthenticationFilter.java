package se.frost.falldetectionproxyapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import se.frost.falldetectionproxyapi.service.token.JWTTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends GenericFilterBean {

    private AuthenticationManager authenticationManager;
    private JWTTokenService jwtTokenService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException, AuthenticationException {
        HttpServletRequest request = (HttpServletRequest) req;
        String token = jwtTokenService.resolveToken(request);

        if (isSecurePathAndTokenNotNull(request, token))
            if (jwtTokenService.isValid(token)) {
            SecurityContextHolder.getContext().setAuthentication(jwtTokenService.getAuthentication(token));
        }
        filterChain.doFilter(req, resp);
    }

    // TODO: See if you can find another solution for this
    private boolean isSecurePathAndTokenNotNull(HttpServletRequest req, String token) {
        return token != null && !req.getRequestURI().contains("/login") && !req.getRequestURI().contains("/register");
    }

    @Autowired
    public void setJwtTokenService(JWTTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
