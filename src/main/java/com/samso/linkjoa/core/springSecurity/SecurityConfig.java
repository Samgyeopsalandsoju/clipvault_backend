package com.samso.linkjoa.core.springSecurity;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private JwtUtil jwtTokenProvider;
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, userDetailsServiceImpl), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/v1/member/sign-up","/v1/member/login","/v1/auth/*"
                        ,"/v1/clip/public/*").permitAll()
                        .anyRequest().authenticated())
//                .oauth2Login(oauth2 -> oauth2
//                        .defaultSuccessUrl("/main/index.do")//TODO 로그인 성공 후 페이지
//                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return httpSecurity.build();
    }

    @Bean
    public static PasswordEncoder passWordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception{
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(passWordEncoder());
//
//        return daoAuthenticationProvider;
//    }
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) throws Exception{
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passWordEncoder());

        return new ProviderManager(provider);
    }
//
//    @Bean
//    public JsonMemberAuthenticationFilter jsonMemberAuthenticationFilter() throws Exception{
//        JsonMemberAuthenticationFilter jsonMemberAuthenticationFilter = new JsonMemberAuthenticationFilter(objectMapper);
//        jsonMemberAuthenticationFilter.setAuthenticationManager(authenticationManager());
//        return jsonMemberAuthenticationFilter;
//    }

//    @Bean
//    public LoginSuccessJWTProviedHandler loginSuccessJWTProviedHandler(){
//        return new LoginSuccessJWTProviedHandler();
//    }
//
//    @Bean
//    public LoginFailureHandler loginFailureHandler(){
//        return new LoginFailureHandler();
//    }
}
