package br.com.dashboard.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.dashboard.controllers.HomeController;
import br.com.dashboard.daos.MensagemDAO_Audit;
import br.com.dashboard.daos.Msg_ReportDAO_Audit;
import br.com.dashboard.daos.ObjItemDAO_JC3;
import br.com.dashboard.daos.OlapDAO_Audit;
import br.com.dashboard.repositories.ListaOperacoes;

@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackageClasses={HomeController.class,Msg_ReportDAO_Audit.class, MensagemDAO_Audit.class, ObjItemDAO_JC3.class, OlapDAO_Audit.class, ListaOperacoes.class})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver internal = new InternalResourceViewResolver();
		internal.setPrefix("/WEB-INF/views/");
		internal.setSuffix(".jsp");
		return internal;
	}
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}