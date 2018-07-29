package pl.niewiel.weekopspring_thymeleaf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/user/new").hasAnyRole()
                .and()
                .formLogin().successForwardUrl("/user/index")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.
//                inMemoryAuthentication()
//                .withUser("user")
//                .password("{noop}user")
//                .authorities("USER");
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("SELECT user_name, password, is_active " +
                        "FROM users " +
                        "WHERE user_name=?")
                .authoritiesByUsernameQuery("SELECT u.user_name, auth.authority " +
                        "FROM users u, authorities auth, user_authorities ua " +
                        "WHERE ua.user_fk=u.id AND ua.authority_fk=auth.authority AND u.user_name=?");
    }

}

