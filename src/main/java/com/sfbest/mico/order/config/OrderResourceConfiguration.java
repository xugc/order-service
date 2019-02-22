package com.sfbest.mico.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableResourceServer
public class OrderResourceConfiguration extends ResourceServerConfigurerAdapter {
	@Autowired
	RedisConnectionFactory redisConnectionFactory;
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(new RedisTokenStore(redisConnectionFactory));
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.anonymous().disable();
		http.authorizeRequests().antMatchers("/create/**").access("#oauth2.hasScope('createOrder')");
	}

}
