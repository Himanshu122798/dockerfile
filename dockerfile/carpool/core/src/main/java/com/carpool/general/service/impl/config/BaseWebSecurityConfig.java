package com.carpool.general.service.impl.config;

import javax.inject.Inject;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.carpool.general.common.impl.security.CsrfRequestMatcher;

import io.oasp.module.security.common.impl.rest.AuthenticationSuccessHandlerSendingOkHttpStatusCode;
import io.oasp.module.security.common.impl.rest.JsonUsernamePasswordAuthenticationFilter;
import io.oasp.module.security.common.impl.rest.LogoutSuccessHandlerReturningOkHttpStatusCode;

/**
 * This type serves as a base class for extensions of the {@code WebSecurityConfigurerAdapter} and provides a default
 * configuration. <br/>
 * Security configuration is based on {@link WebSecurityConfigurerAdapter}. This configuration is by purpose designed
 * most simple for two channels of authentication: simple login form and rest-url.
 */
public abstract class BaseWebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${security.cors.enabled}")
  boolean corsEnabled = true;

  @Inject
  private UserDetailsService userDetailsService;

  private CorsFilter getCorsFilter() {

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("HEAD");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("DELETE");
    config.addAllowedMethod("PATCH");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

  /**
   * Configure spring security to enable a simple webform-login + a simple rest login.
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {

    String[] unsecuredResources = new String[] { "/login", "/info", "/loggers", "/dump", "/trace", "/auditevents",
    "/refresh", "/env", "/metrics", "/logfile", "/heapdump", "/jolokia", "/flyway", "/liquibase", "/health",
    "/hystrix.stream", "/security/**", "/services/rest/login", "/services/rest/logout", "/data/**" };

    http
        //
        .userDetailsService(this.userDetailsService)
        // define all urls that are not to be secured
        .authorizeRequests().antMatchers(unsecuredResources).permitAll().anyRequest().authenticated().and()

        // activate crsf check for a selection of urls (but not for login & logout)
        .csrf().requireCsrfProtectionMatcher(new CsrfRequestMatcher()).and()

        // configure parameters for simple form login (and logout)
        .formLogin().successHandler(new SimpleUrlAuthenticationSuccessHandler()).defaultSuccessUrl("/")
        .failureUrl("/login.html?error").loginProcessingUrl("/j_spring_security_login").usernameParameter("username")
        .passwordParameter("password").and()
        // logout via POST is possible
        .logout().logoutSuccessUrl("/login.html").and()

        // register login and logout filter that handles rest logins
        .addFilterAfter(getSimpleRestAuthenticationFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(getSimpleRestLogoutFilter(), LogoutFilter.class).csrf().disable();

    if (this.corsEnabled) {
      http.addFilterBefore(getCorsFilter(), CsrfFilter.class);
    }
  }

  /**
   * Create a simple filter that allows logout on a REST Url /services/rest/logout and returns a simple HTTP status 200
   * ok.
   *
   * @return the filter.
   */
  protected Filter getSimpleRestLogoutFilter() {

    LogoutFilter logoutFilter =
        new LogoutFilter(new LogoutSuccessHandlerReturningOkHttpStatusCode(), new SecurityContextLogoutHandler());

    // configure logout for rest logouts
    logoutFilter.setLogoutRequestMatcher(new AntPathRequestMatcher("/services/rest/logout"));

    return logoutFilter;
  }

  /**
   * Create a simple authentication filter for REST logins that reads user-credentials from a json-parameter and returns
   * status 200 instead of redirect after login.
   *
   * @return the {@link JsonUsernamePasswordAuthenticationFilter}.
   * @throws Exception if something goes wrong.
   */
  protected JsonUsernamePasswordAuthenticationFilter getSimpleRestAuthenticationFilter() throws Exception {

    JsonUsernamePasswordAuthenticationFilter jsonFilter =
        new JsonUsernamePasswordAuthenticationFilter(new AntPathRequestMatcher("/services/rest/login"));
    jsonFilter.setPasswordParameter("j_password");
    jsonFilter.setUsernameParameter("j_username");
    jsonFilter.setAuthenticationManager(authenticationManager());
    // set failurehandler that uses no redirect in case of login failure; just HTTP-status: 401
    jsonFilter.setAuthenticationManager(authenticationManagerBean());
    jsonFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler());
    // set successhandler that uses no redirect in case of login success; just HTTP-status: 200
    jsonFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandlerSendingOkHttpStatusCode());
    return jsonFilter;
  }

  @SuppressWarnings("javadoc")
  @Inject
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    auth.inMemoryAuthentication().withUser("waiter").password("waiter").roles("Waiter").and().withUser("cook")
        .password("cook").roles("Cook").and().withUser("barkeeper").password("barkeeper").roles("Barkeeper").and()
        .withUser("chief").password("chief").roles("Chief");
  }

}
