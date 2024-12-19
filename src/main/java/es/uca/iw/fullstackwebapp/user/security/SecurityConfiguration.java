package es.uca.iw.fullstackwebapp.user.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import es.uca.iw.fullstackwebapp.user.views.UserLoginView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                authorize -> authorize.requestMatchers(new AntPathRequestMatcher("/line-awesome/**/*.svg"), new AntPathRequestMatcher("/images/*.*"), new AntPathRequestMatcher("/api/**")).anonymous());


        super.configure(http);
        setLoginView(http, UserLoginView.class);
    }

}
