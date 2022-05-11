plugins {
  id("org.jetbrains.kotlin.jvm").version("1.6.0")
  id("org.jetbrains.kotlin.plugin.spring").version("1.6.0")
  id("org.jetbrains.kotlin.plugin.serialization").version("1.6.0")
  id("org.springframework.boot").version("2.6.7")
  id("com.google.cloud.tools.appengine").version("2.4.2")
  id("org.springframework.experimental.aot").version("0.11.5")
}

dependencies {
  implementation("com.expediagroup:graphql-kotlin-spring-server:6.0.0-SNAPSHOT")
  implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
  compileOnly("org.graalvm.nativeimage:svm:22.0.0.2")
  //implementation("org.graalvm.nativeimage:svm-hosted-native-darwin-amd64:22.1.0")
  implementation("io.github.classgraph:classgraph:4.8.146")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.4.32")
  testImplementation("com.squareup.okhttp3:okhttp:4.9.3")
}

kotlin {
  sourceSets.all {
    languageSettings {
      optIn("kotlin.RequiresOptIn")
    }
  }
}

appengine {
  stage {
    setArtifact(tasks.named("bootJar").flatMap { (it as Jar).archiveFile })
  }
  deploy {
    projectId = "kiki-conf"
    version = "GCLOUD_CONFIG"
  }
}

nativeBuild {
  buildArgs.add("--initialize-at-build-time=io.github.classgraph.,nonapi.io.github.classgraph.")
}