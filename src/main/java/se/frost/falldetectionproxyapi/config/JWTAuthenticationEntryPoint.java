package se.frost.falldetectionproxyapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import se.frost.falldetectionproxyapi.dto.response.ErrorResponse;
import se.frost.falldetectionproxyapi.exceptions.ExceptionType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class gets called when the user tries to access a secure resource without credentials
 */
@ControllerAdvice
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
        ErrorResponse errorResponse = new ErrorResponse(ExceptionType.INVALID_CREDENTIALS);
        resp.getOutputStream().write(new ObjectMapper().writeValueAsBytes(errorResponse));
    }
}
