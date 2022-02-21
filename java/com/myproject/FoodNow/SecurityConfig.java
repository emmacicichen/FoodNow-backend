package com.laioffer.onlineOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity//we got spring security and mvc integration support
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //WebSecurityConfigurerAdapter是个配置文件的class，定义你去db哪个表查，或者什么url可以访问

    @Autowired
    private DataSource dataSource;//需要访问DB，要注入DataSource

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//CSRF support，因为我们是单机的，所以把跨域请求的解决方法给disable了
                .formLogin()//前端会发来表单形式的请求//前端让用户去输入username和pw，相当于给一个/login的API，然后调用查询数据库验证的程序（AuthenticationManagerBuilder那个method）attempAuthentiation
                .failureForwardUrl("/login?error=true");//当你登陆fail的时候，会redirect到另一个页面//direct到dispatch servlet，然后发给login这个controller
        http
                .authorizeRequests()
                .antMatchers("/order/*", "/cart", "/checkout").hasAuthority("ROLE_USER")//访问/order/*", "/cart", "/checkout"这几个api的时候你必须要有"ROLE_USER"这个权限
                .anyRequest().permitAll();//剩下其他操作，permit all
    }
    //formLogin()除了login身份验证，失败成功的跳转，他还会产生一个session。 formLogin默认的是session-based -》 见attempAuthentiation方法：从request中读取username和pw，然后调用AuthenticationManager

    @Override//authorization
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {//jdbcAuthentication()//链接数据库的authentication

        auth
                .jdbcAuthentication()
                .dataSource(dataSource)//connect database
                .usersByUsernameQuery("SELECT email, password, enabled FROM customers WHERE email=?")//在验证用用户名和密码是否正确
                .authoritiesByUsernameQuery("SELECT email, authorities FROM authorities WHERE email=?");//use '?' to avoid SQL injection
            //到email password，authorities这些表里找email，返回db里存的密码及一系列信息的 去match，然后保存到session里
        //去db里找到用户名密码，与浏览器发来的核对 -> if匹配，验证成功，会把这些信息存在session里面
    }
    //谁接收login的请求来验证，然后调用数据库数据的呢？AuthenticationManager


    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {//给密码加密，我们没做，我们密码是明文存在db的，也是明文进行匹配的
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
        //也可以在configure中创建一个bean，用一种加密方法加密。框架会把browser发来的明文密码进行匹配，可以自己改这部分
        //如果想要加密，我们就把NoOpPasswordEncoder变成一种加密方法：比如BCryptPasswordEncoder（见staybooking项目）
    }
}
