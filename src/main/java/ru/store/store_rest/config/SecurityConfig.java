package ru.store.store_rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET,"/thing/").permitAll()
                .mvcMatchers(HttpMethod.GET, "/thing/category").permitAll()
                .mvcMatchers(HttpMethod.GET, "/thing/brand").permitAll()
                .mvcMatchers(HttpMethod.GET, "/thing/size").permitAll()
                .mvcMatchers(HttpMethod.POST, "/thing/").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/thing/{id}").hasRole("ADMIN")
                .mvcMatchers("/user/**").hasAnyRole("ADMIN")
                .and()
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .logout(withDefaults())
                .passwordManagement(withDefaults())
                .rememberMe(withDefaults());
    }

    @Bean
    public UserDetailsManager users() {
        UserDetails user = User.builder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("admin")
                .roles("USER", "ADMIN")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setCreateUserSql("insert into users (username, password, enabled) values (?,?,?) ON CONFLICT (username) DO NOTHING");
        users.setCreateAuthoritySql("insert into authorities (username, authority) values (?,?) ON CONFLICT (username, authority) DO NOTHING");
        users.createUser(user);
        users.createUser(admin);
        return users;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
