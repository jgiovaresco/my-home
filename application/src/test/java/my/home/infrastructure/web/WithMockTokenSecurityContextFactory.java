package my.home.infrastructure.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public final class WithMockTokenSecurityContextFactory implements WithSecurityContextFactory<WithToken> {

    @Override
    public SecurityContext createSecurityContext(WithToken withToken) {
        String email = withToken.email();
        String subject = withToken.sub();

        Jwt jwt = Jwt
            .withTokenValue("{}")
            .header("alg", "HS256")
            .header("typ", "JWT")
            .claim("aud", "audience")
            .claim("email", email)
            .subject(subject)
            .build();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new JwtAuthenticationToken(jwt, grantedAuthorities));
        return context;
    }
}
