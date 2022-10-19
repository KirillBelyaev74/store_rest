package ru.store.store_rest.config

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource

@EnableWebSecurity
open class SecurityConfig(private val dataSource: DataSource): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .mvcMatchers(HttpMethod.GET, "/thing/").permitAll()
            .mvcMatchers(HttpMethod.GET, "/thing/category").permitAll()
            .mvcMatchers(HttpMethod.GET, "/thing/brand").permitAll()
            .mvcMatchers(HttpMethod.GET, "/thing/size").permitAll()
            .mvcMatchers(HttpMethod.POST, "/thing/").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.DELETE, "/thing/{id}").hasRole("ADMIN")
            .mvcMatchers("/user/**").hasAnyRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults())
            .logout(Customizer.withDefaults())
            .passwordManagement(Customizer.withDefaults())
            .rememberMe(Customizer.withDefaults())
    }

//    @Autowired
//    fun configureGlobal(auth: AuthenticationManagerBuilder) {
//        auth.jdbcAuthentication()
//            .dataSource(dataSource)
//            .usersByUsernameQuery("select username, password, enabled from users where username = ?")
//            .authoritiesByUsernameQuery(("select username, authority from authorities where username = ?"))
//    }
//
//    @Bean
//    fun saveUsers(): UserDetailsManager? {
//        val user = User.builder()
//            .username("user")
//            .password(passwordEncoder().encode("user"))
//            .roles("USER")
//            .build()
//        val admin = User.builder()
//            .username("admin")
//            .password(passwordEncoder().encode("admin"))
//            .roles("USER", "ADMIN")
//            .build()
//        val users = JdbcUserDetailsManager(dataSource)
//        users.setCreateUserSql("insert into users (username, password, enabled) values (?,?,?) ON CONFLICT (username) DO NOTHING")
//        users.setCreateAuthoritySql("insert into authorities (username, authority) values (?,?) ON CONFLICT (username, authority) DO NOTHING")
//        users.createUser(user)
//        users.createUser(admin)
//        return users
//    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}