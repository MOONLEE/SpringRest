package com.moon.rest.main.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;


@Configuration
public class SinglePageAppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		
		// static resource Handler
		registry
			.addResourceHandler("/**")
			.addResourceLocations("classpath:/static/")
			.resourceChain(false)
			.addResolver(new PushStateResourceResolver());
		
		
		// rest docs resource Handler
		registry
			.addResourceHandler("/rest/**")
			.addResourceLocations("classpath:/templates/rest/");
				
	}

	
	public class PushStateResourceResolver implements ResourceResolver {

		private Resource index = new ClassPathResource("templates/index.html");
		private List<String> ignoredPaths = Arrays.asList("api");
		private List<String> handledExtensions = Arrays.asList("html", "js", "json", "csv", "css", "png", "svg", "eot", "ttf", "woff", "appcache", "jpg", "jpeg", "gif", "ico");
		
		
		@Override
		public Resource resolveResource(HttpServletRequest request, String requestPath,
				List<? extends Resource> locations, ResourceResolverChain chain) {
			return resolve(requestPath, locations);
		}

		private Resource resolve(String requestPath, List<? extends Resource> locations) {
			if (isIgnored(requestPath)) {
				return null;
			}

			if (isHandled(requestPath)) {
				return locations
							.stream()
							.map(loc -> createReletive(loc, requestPath))
							.filter(resource -> resource != null && resource.exists())
					        .findFirst()
					        .orElseGet(null);
				}
			
			return index;
		}

		private Resource createReletive(Resource resource, String relativePath) {
			try {
				return resource.createRelative(relativePath);
			} catch (IOException e) {
				return null;
			}
		}

		@Override
		public String resolveUrlPath(String resourcePath, List<? extends Resource> locations,
				ResourceResolverChain chain) {
			Resource resolvedResource = resolve(resourcePath, locations);
		      if (resolvedResource == null) {
		        return null;
		      }
		      try {
		        return resolvedResource.getURL().toString();
		      } catch (IOException e) {
		        return resolvedResource.getFilename();
		      }
		}
		


	    private boolean isIgnored(String path) {
	      return ignoredPaths.contains(path);
	    }

	    private boolean isHandled(String path) {
	      String extension = StringUtils.getFilenameExtension(path);
	      return handledExtensions.stream().anyMatch(ext -> ext.equals(extension));
	    }

	}
	
}
