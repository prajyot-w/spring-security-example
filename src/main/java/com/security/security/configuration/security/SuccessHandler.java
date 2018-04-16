package com.security.security.configuration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author prajyot on 16/4/18.
 * @project security.
 */
@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Success handler to redirect user to correct url after logging in.
     * */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        AtomicReference<Boolean> isAllowedInApplication = new AtomicReference<>(false);

        grantedAuthorities.forEach(authority -> {
            if(authority.getAuthority().equals("ROLE_ADMIN") || authority.getAuthority().equals("ROLE_USER")){
                isAllowedInApplication.set(true);
            }
        });

        if(isAllowedInApplication.get()){
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");
        }

    }
}
