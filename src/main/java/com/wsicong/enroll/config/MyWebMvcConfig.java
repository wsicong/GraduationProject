package com.wsicong.enroll.config;

import com.wsicong.enroll.interceptor.UserActionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义静态资源映射路径和静态资源存放路径
 */
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * @return
     * @描述：在Spring添加拦截器之前先创建拦截器对象，这样就能在Spring映射这个拦截器前，把拦截器中的依赖注入的对象给初始化完成了。 避免拦截器中注入的对象为null问题。
     */
    @Bean
    public UserActionInterceptor userActionInterceptor() {
        return new UserActionInterceptor();
    }
    /**
     * 添加自定义静态资源映射路径和静态资源存放路径
     */
	/*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/layui/**")
				.addResourceLocations("/layui/");
		super.addResourceHandlers(registry);
	}*/

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 路径根据后期项目的扩展，进行调整
        registry.addInterceptor(userActionInterceptor())
                .addPathPatterns("/user/**", "/auth/**", "/enroll/**")
                .excludePathPatterns("/user/sendMsg", "/user/login");
        super.addInterceptors(registry);
    }

    /**
     *
     * @描述：将错误页面注册到servlet容器中
     * @创建人：wyait
     * @创建时间：2018年5月28日 下午4:42:24
     * @return
     */
	/*@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new EmbeddedServletContainerCustomizer(){
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
                container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
                container.addErrorPages(new ErrorPage(java.lang.Throwable.class,"/error/500"));
            }
        };
    }*/

}
