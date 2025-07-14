package com.example.DemoSpring.security.jwt;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName="JWTFilter",urlPatterns  = "/api/*")
public class JWTFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader("Authorization");

        if(token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if(isValidToken(token)) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                filterChain.doFilter(request, response);
            }else{
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }else{
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

    }

    private boolean isValidToken(String token) {
        return true;
    }

    @Override
    public void destroy() {}
}
