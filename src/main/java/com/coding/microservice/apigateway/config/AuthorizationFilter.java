package com.coding.microservice.apigateway.config;

import java.util.Base64;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationFilter implements GatewayFilterFactory<AuthorizationFilter.Config> {

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			/*String[] uriParts = exchange.getRequest().getURI().toString().split("/");
			String base64Token = uriParts[uriParts.length - 1];
			byte[] decodedBytes = Base64.getDecoder().decode(base64Token);
			String decodedToken = new String(decodedBytes);*/
			ServerHttpRequest request = exchange.getRequest().mutate().header("Authorization", "").build();
			System.out.println("-----Authorization filter ----------");
			return chain.filter(exchange.mutate().request(request).build());
		});
	}

	@Override
	public Class<Config> getConfigClass() {
		return Config.class;
	}

	@Override
	public Config newConfig() {
		Config c = new Config();
		return c;
	}

	public static class Config {}
}
