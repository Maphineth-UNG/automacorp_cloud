plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.emse.spring"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa") // libs to use JPA in your project
	implementation("com.h2database:h2") // libs to use a H2 database
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

	implementation("org.springframework:spring-context-support:6.0.11")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
	testImplementation("org.mockito:mockito-junit-jupiter:5.4.0")
	testImplementation("org.assertj:assertj-core:3.24.2")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")


}



tasks.withType<Test> {
	useJUnitPlatform()
}
