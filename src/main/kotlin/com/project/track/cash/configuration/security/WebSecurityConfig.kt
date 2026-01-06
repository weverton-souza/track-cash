package com.project.track.cash.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    private val jwtSecurityFilter: JwtSecurityFilter,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint
) : WebMvcConfigurer {

    @Bean
    fun securityWebFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors {
                it.disable()
                it.configurationSource(corsConfigurationSource())
            }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/security-manager/**",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/webjars/**",
                    "/swagger-resources/**",
                    "/actuator/**",
                    "/favicon.ico",
                    "/error"
                ).permitAll()
                it.anyRequest().authenticated()
            }
            .addFilterBefore(this.jwtSecurityFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                it.authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
            }
            .build()
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowedHeaders("*")
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.allowedOrigins = arrayListOf(
            "*",
            "http://localhost:3000",
            "http://localhost:4200",
            "http://neurohub-frontend-hml:3000",
            "http://neurohub-frontend:3000",
            "http://neurohub-frontend:80"
        )
        corsConfiguration.allowedMethods = arrayListOf("*", "GET", "POST", "PUT", "DELETE", "PATCH")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration)

        return source
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}