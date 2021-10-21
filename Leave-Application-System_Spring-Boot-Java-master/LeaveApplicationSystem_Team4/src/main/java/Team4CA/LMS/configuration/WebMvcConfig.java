package Team4CA.LMS.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
 
 @Bean //is a class. to allow to use this class in other places/other packages
 public BCryptPasswordEncoder passwordEncoder() {
  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
  return bCryptPasswordEncoder;
 }
}