//package com.wangzai.gateway.filter;
//
//import com.wangzai.common.core.utils.StringUtils;
//import com.wangzai.gateway.properties.WhiteUrlPoperties;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//@Component
//@AllArgsConstructor
//public class AuthFilter implements GlobalFilter, Ordered {
//
//    private final WhiteUrlPoperties whiteUrlPoperties;
//
//    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        String path = request.getURI().getPath();
//
//        // 跳过不需要验证的路径
//        if (StringUtils.matches(path, whiteUrlPoperties.getWhiteUrls())) {
//            return chain.filter(exchange);
//        }
//
//        String token = getToken(request);
//        if (StringUtils.isEmpty(token)) {
//
//        }
//
//
//        return null;
//    }
//
//    private String getToken(ServerHttpRequest request) {
//        String token = request.getHeaders().getFirst("");
//
//        return token;
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
