package com.myproject.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

public class CustomScanPackage {

	private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

	public static Set<Class<?>> doScanByPackageName(String... packageName) {
		Set<Class<?>> classSet = new HashSet<>();
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
		for (String string : packageName) {
			String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
					+ ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(string)) + "/"
					+ DEFAULT_RESOURCE_PATTERN;
			try {
				Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
				for (Resource resource : resources) {
					if (resource.isReadable()) {
						MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
						if (metadataReader != null) {
							ClassMetadata classMetadata = metadataReader.getClassMetadata();
							Class<?> forName = Class.forName(classMetadata.getClassName());
							classSet.add(forName);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return classSet;
	}

	public static Set<Class<?>> scanEntityClassFromProject() {
		return CustomScanPackage.doScanByPackageName("com.myproject.entity");
	}
}