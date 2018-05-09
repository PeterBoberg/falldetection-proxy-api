package se.frost.falldetectionproxyapi.service.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import se.frost.falldetectionproxyapi.entities.User;
import se.frost.falldetectionproxyapi.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;

@Configuration
public class JWTTokenService {

    private String secretKey = "MySecretKey";
    private UserRepository userRepository;

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("user", user);

        Date now = new Date();
        Date validityDate = new Date(now.getTime() + 1000000000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validityDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    @SuppressWarnings("unchecked")
    public Authentication getAuthentication(String token) {
        String username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User does not exsist");
        return new UsernamePasswordAuthenticationToken(user, user.getUsername(), Collections.emptyList());
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
