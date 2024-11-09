package courtBookingSystemFinal.security;

import courtBookingSystemFinal.security.filters.CustomAuthenticationFilter;
import courtBookingSystemFinal.security.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * This is the main configuration class for security in the application. It enables web security,
 * sets up the password encoder, and sets up the security filter chain.
 */
@Configuration
@EnableWebSecurity // indicates it is a security config class using spring web security
@RequiredArgsConstructor
public class SecurityConfig {

    // UserDetailsService is an interface provided by Spring Security that defines a way to retrieve user information
    private final UserDetailsService userDetailsService;

    // Autowired instance of the AuthenticationManagerBuilder (provided by Spring Security)
    private final AuthenticationManagerBuilder authManagerBuilder;


    /**
     * Bean definition for AuthenticationManager
     *
     * @param authenticationConfiguration the instance of AuthenticationConfiguration
     * @return an instance of the AuthenticationManager
     * @throws Exception if there is an issue getting the instance of the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean definition for SecurityFilterChain
     *
     * @param http the instance of HttpSecurity
     * @return an instance of the SecurityFilterChain
     * @throws Exception if there is an issue building the SecurityFilterChain
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CustomAuthenticationFilter instance created
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authManagerBuilder.getOrBuild());

        // set the URL that the filter should process
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");

        // disable CSRF protection because we are using tokens, not session
        http.csrf().disable();

        // set the session creation policy to stateless, to not maintain sessions in the server
        http.sessionManagement().sessionCreationPolicy(STATELESS);

        // set up authorization for different request matchers and user roles
        http.authorizeHttpRequests((requests) -> requests
//                                       NO AUTH public endpoint
                .requestMatchers("/api/login/**").permitAll()
//                                       USER ALLOWED REQUESTS
                .requestMatchers(POST, "/api/bookings/createNew").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .anyRequest().hasAuthority("ROLE_ADMIN")); // any other endpoints require authentication
//               ADMIN REQUESTS they are all combined in .anyRequest() and require auth so they do not need to be declared one by one
//                .requestMatchers(GET, "/api/users/all").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(POST, "/api/courts/createNew").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(POST, "/api/roles/createNew").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(POST, "/api/surface/createNew").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(POST, "/api/users/createNew").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(GET, "/api/bookings/all").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(GET, "/api/courts/all").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(GET, "/api/roles/all").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(GET, "/api/surface/all").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(GET, "/api/users/all").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(PUT, "/api/bookings/confirm").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(PUT, "/api/courts/surfaceToCourt").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(PUT, "/api/roles/addToUser").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(PUT, "/api/roles/removeRoleFromUser").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(PATCH, "/api/courts/{id}").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(PATCH, "/api/users/{id}").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(PATCH, "/api/roles/{id}").hasAuthority("ROLE_ADMIN")
//                .requestMatchers(PATCH, "/api/surface/{id}").hasAuthority("ROLE_ADMIN")

//      This way all tasks that require DELETE can be only performed by admin, we dont need to declare them separately
//                .requestMatchers(DELETE).hasAuthority("ROLE_ADMIN")

        // add the custom authentication filter to the http security object
        http.addFilter(customAuthenticationFilter);

        // Add the custom authorization filter before the standard authentication filter.
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        // Build the security filter chain to be returned.
        return http.build();
    }

}
