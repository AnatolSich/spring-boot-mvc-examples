package com.test.hplus.config;

import com.test.hplus.convertors.StringToEnumConvertor;
import com.test.hplus.interceptors.LoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

import java.util.Locale;

@Configuration
@ComponentScan(basePackages = "com.test.hplus")
public class ApplicationConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("css/**", "images/**")
                .addResourceLocations("classpath:/static/css/", "classpath:/static/images/");
    }

    // InternalResourceViewResolver implementation
    //Only resolves error page and profile page
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setOrder(3);
        return viewResolver;
    }

    // XmlViewResolver implementation
    @Bean
    public XmlViewResolver xmlViewResolver() {
        XmlViewResolver viewResolver = new XmlViewResolver();
        viewResolver.setLocation(new ClassPathResource("views.xml"));
        viewResolver.setOrder(1);
        return viewResolver;
    }

    // ResourceBundleViewResolver implementation
    @Bean
    public ResourceBundleViewResolver resourceBundleViewResolver() {
        ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
        viewResolver.setBasename("views");
        viewResolver.setOrder(2);
        return viewResolver;
    }

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConvertor());
    }

    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(5000);
        configurer.setTaskExecutor(mvcTaskExecutor());
    }

    @Bean
    public AsyncTaskExecutor mvcTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("hplusapp-thread-");
        return threadPoolTaskExecutor;

    }

    //interceptor for all url pattern
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/*");
        //in-built interceptor to catch value from url for ThemeResolver
        //e.g. http://localhost:8080/home?theme=client-theme2
        //to check cookie - F12/Application/Storage/Cookies
        registry.addInterceptor(new ThemeChangeInterceptor());  //default query param: theme
        //e.g. http://localhost:8080/home?localeParam=sv
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("localeParam");  //default query param: locale
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Bean
    public ThemeResolver themeResolver() {
        CookieThemeResolver cookieThemeResolver = new CookieThemeResolver();
        //id for cookie which contains theme
        cookieThemeResolver.setCookieName("theme");
        //default theme
        //properties file name
        cookieThemeResolver.setDefaultThemeName("client-theme1");
        return cookieThemeResolver;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.US);
        //id for cookie which contains locale
        cookieLocaleResolver.setCookieName("localeCookie");
        return cookieLocaleResolver;
    }

}
