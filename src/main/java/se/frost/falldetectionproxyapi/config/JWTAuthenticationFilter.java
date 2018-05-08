package se.frost.falldetectionproxyapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import se.frost.falldetectionproxyapi.dto.response.ErrorResponse;
import se.frost.falldetectionproxyapi.entities.User;
import se.frost.falldetectionproxyapi.exceptions.ExceptionType;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends GenericFilterBean {

    private AuthenticationManager authenticationManager;
    private JWTTokenManager jwtTokenManager;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("JWT AuthenticationFilter do Filter");
        String token = jwtTokenManager.resolveToken((HttpServletRequest) req);

        if (token != null) {
            if (jwtTokenManager.isValid(token)) {
                User currentUser = jwtTokenManager.resolveUser(token);
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(currentUser.getUsername(), currentUser.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(currentUser, currentUser.getPassword()));
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                HttpServletResponse response = (HttpServletResponse) resp;
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getOutputStream().write(objectMapper.writeValueAsBytes(new ErrorResponse(ExceptionType.INVALID_TOKEN)));
                return;
            }
        }
        filterChain.doFilter(req, resp);
    }

    @Autowired
    public void setJwtTokenManager(JWTTokenManager jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
