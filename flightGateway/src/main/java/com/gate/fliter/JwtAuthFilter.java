package com.gate.fliter;



import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.buffer.DataBufferFactory;

@Component
public class JwtAuthFilter extends AbstractGatewayFilterFactory<Object> {

    private final WebClient.Builder webClientBuilder;

    public JwtAuthFilter(WebClient.Builder webClientBuilder) {
        super();
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, "No Authorization header", HttpStatus.UNAUTHORIZED);
            }

            String token = authHeader.substring(7);

            return webClientBuilder.build()
                .get()
                .uri("http://identity-service/auth/role?token=" + token)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(role -> {
                    if (isAuthorized(exchange, role)) {
                        return chain.filter(exchange);
                    } else {
                        return onError(exchange, "Role not allowed to access this service", HttpStatus.FORBIDDEN);
                    }
                })
                .onErrorResume(error -> onError(exchange, "Invalid token or role not found", HttpStatus.UNAUTHORIZED));
        };
    }

    private boolean isAuthorized(ServerWebExchange exchange, String role) {
        String path = exchange.getRequest().getURI().getPath();
        role = role.toLowerCase();

        // Admin can access everything
        if ("admin".equals(role)) {
            return true;
        }

        // User can access only booking and search services
        if ("user".equals(role)) {
            return path.startsWith("/api/bookings")
                || path.startsWith("/api/search");
        }

        // All other roles: forbidden
        return false;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        DataBufferFactory bufferFactory = response.bufferFactory();
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        return response.writeWith(Mono.just(bufferFactory.wrap(bytes)));
    }

    public static class Config { 
        // Add configuration properties here if needed
    }
}