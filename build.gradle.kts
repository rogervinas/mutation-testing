import info.solidsoft.gradle.pitest.PitestPluginExtension
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED

plugins {
  kotlin("jvm") version "2.2.10"
  id("info.solidsoft.pitest") version "1.15.0"
  id("org.jlleitschuh.gradle.ktlint") version "13.0.0"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  testImplementation(platform("org.junit:junit-bom:5.9.3"))
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testImplementation("org.junit.jupiter:junit-jupiter-params")

  testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.28.1")
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

kotlin {
  compilerOptions {
    freeCompilerArgs.addAll("-Xjsr305=strict")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events(PASSED, SKIPPED, FAILED)
    exceptionFormat = FULL
    showExceptions = true
    showCauses = true
    showStackTraces = true
  }
}

configure<PitestPluginExtension> {
  junit5PluginVersion.set("1.0.0")
  avoidCallsTo.set(setOf("kotlin.jvm.internal"))
  mutators.set(setOf("STRONGER"))
  targetClasses.set(setOf("org.rogervinas.*"))
  targetTests.set(setOf("org.rogervinas.*"))
  threads.set(Runtime.getRuntime().availableProcessors())
  outputFormats.set(setOf("XML", "HTML"))
  mutationThreshold.set(75)
  coverageThreshold.set(60)
}

tasks.named("check") {
  dependsOn(":pitest")
}
