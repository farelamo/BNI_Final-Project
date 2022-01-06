package com.mvp.gateway.filter;

import com.mvp.gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Log4j2
@Component
public class JwtAuthenticationFilter implements GatewayFilter {
    private static final String JWT_HEADER = "Authorization";
    private static final String JWT_TOKEN_PREFIX = "Bearer ";

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        final List<String> apiEndpoints = List.of("/register", "/token");

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (isApiSecured.test(request)) {
            if (!request.getHeaders().containsKey(JWT_HEADER)) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            final String token = getJWTFromRequest(request);
            log.info("{}", token);
            Claims claims = jwtUtil.getClaims(token);
            exchange.getRequest().mutate().header("username", String.valueOf(claims.get("username"))).build();
        }
        return chain.filter(exchange);
    }

    private String getJWTFromRequest(HttpRequest request){
        String bearerToken = request.getHeaders().getFirst(JWT_HEADER);
        log.info(bearerToken);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(JWT_TOKEN_PREFIX)){
            return bearerToken.substring(7);
        }
        return null;
    }
}
