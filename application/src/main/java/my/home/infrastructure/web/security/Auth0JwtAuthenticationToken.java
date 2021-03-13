package my.home.infrastructure.web.security;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class Auth0JwtAuthenticationToken {

    private final JwtAuthenticationToken token;

    public Auth0JwtAuthenticationToken(JwtAuthenticationToken token) {
        this.token = token;
    }

    public String getEmail() {
        return (String) token.getTokenAttributes().get("email");
    }

    public String getSub() {
        return (String) token.getTokenAttributes().get("sub");
    }
}
