package com.example.apigateway.filter;

import com.example.apigateway.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;
    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if(validator.isSecured.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw  new RuntimeException();
                }
                String authHeader =exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader!=null&&authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);
                }
                try {
//                    restTemplate.getForObject("http://security-service/validate?token="+authHeader, String.class);
                    jwtUtil.validateToken(authHeader);
                }
                catch (Exception e){

                }
            }
            return chain.filter(exchange);
        };
    }

    public static class Config{

    }
}