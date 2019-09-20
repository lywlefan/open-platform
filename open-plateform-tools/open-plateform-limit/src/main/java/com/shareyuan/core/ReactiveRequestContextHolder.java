package com.shareyuan.core;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/**
 * @Author : kent
 * @Email : 330334064@qq.com
 * @Description : Webflux Holder
 * @Date : 12:02 2019/9/19
 */
public class ReactiveRequestContextHolder {
    private static final Class<ServerWebExchange> CONTEXT_KEY = ServerWebExchange.class;

    /**
     * Gets the {@code Mono<ServerWebExchange>} from Reactor {@link Context}
     *
     * @return the {@code Mono<ServerWebExchange>}
     */
    public static Mono<ServerWebExchange> getExchange() {
        return Mono.subscriberContext()
                .map(ctx -> ctx.get(CONTEXT_KEY));
    }

    /**
     * Gets the {@code Mono<ServerHttpRequest>} from Reactor {@link Context}
     *
     * @return the {@code Mono<ServerHttpRequest>}
     */
    public static Mono<ServerHttpRequest> getRequest() {
        return ReactiveRequestContextHolder.getExchange()
                .map(ServerWebExchange::getRequest);
    }

    /**
     * Put the {@code ServerWebExchange} to Reactor {@link Context}
     *
     * @param context  Context
     * @param exchange ServerWebExchange
     * @return the Reactor {@link Context}
     */
    public static Context put(Context context, ServerWebExchange exchange) {
        return context.put(CONTEXT_KEY, exchange);
    }
}
