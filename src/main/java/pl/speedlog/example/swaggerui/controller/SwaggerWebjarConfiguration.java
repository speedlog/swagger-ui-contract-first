package pl.speedlog.example.swaggerui.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.speedlog.example.swaggerui.ApplicationProfiles;

import java.io.IOException;

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">mariusz@wyszomierski.pl</a>
 */
@Configuration
@Profile(ApplicationProfiles.DEV)
public class SwaggerWebjarConfiguration implements WebMvcConfigurer {

  private static final String CLASSPATH_CONTRACT = "classpath:/contract/";

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/webjars/").resourceChain(false);
    registry.addResourceHandler("/contract/**").addResourceLocations(CLASSPATH_CONTRACT);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "/swagger-ui/index.html?url=/contract/" + getContractFilename());
  }

  private String getContractFilename() {
    ClassLoader cl = this.getClass().getClassLoader();
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
    Resource[] resources;
    try {
      resources = resolver.getResources(CLASSPATH_CONTRACT + "*.yaml");
    } catch (IOException e) {
      throw new RuntimeException("I/O exception while getting contract", e);
    }
    if (resources.length < 1) {
      throw new RuntimeException("In directory 'contract' there are no *.yaml file with OpenAPI contract");
    }
    if (resources.length > 1) {
      throw new RuntimeException("In directory 'contract' may be only one *.yaml file with OpenAPI contract");
    }
    return resources[0].getFilename();
  }
}
