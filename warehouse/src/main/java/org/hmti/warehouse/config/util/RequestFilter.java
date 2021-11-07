/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.config.util;

import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.hmti.warehouse.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author asdin
 */
@Component
@NoArgsConstructor
public class RequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtil jWTUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {
        final String jwtToken = request.getHeader("Authorization");
        String username = null;

        try {
            username = jWTUtil.getUsernameFromToken(jwtToken);
        } catch (IllegalArgumentException e) {
            logger.warn("Unable to get JWT Token, " + e.toString());
        } catch (ExpiredJwtException e) {
            logger.warn("JWT Token has expired, " + e.toString());
        } catch (Exception e) {
            logger.warn("JWT Token validity error, " + e.toString());
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication()
                == null) {
            UserDetails userDetails = this.userService.loadUserByUsername(
                    username);
            if (jWTUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(
                                request));
                SecurityContextHolder.getContext().setAuthentication(
                        usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

}
