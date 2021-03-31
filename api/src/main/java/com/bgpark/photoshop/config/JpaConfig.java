package com.bgpark.photoshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 테스트는 @SpringBootApplication 어노테이션까지 디펜던시를 가져오지 못하므로 @EnableJpaAuditing을 인식하지 못한다
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
