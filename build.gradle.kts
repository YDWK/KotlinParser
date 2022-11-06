import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
    id("com.diffplug.spotless") version "6.11.0"
    `maven-publish`
    signing
}

//TODO : update
extra.apply {
    set("name", "YDWK")
    set("description", "YDWK (Yusuf's Discord Wrapper Kotlin) My own Discord Wrapper in Kotlin")
    set("group", "io.github.realyusufismail")
    set("version", "0.0.4")
    set("dev_id", "yusuf")
    set("dev_name", "Yusuf Ismail")
    set("dev_email", "yusufgamer222@gmail.com")
    set("dev_organization", "YDWK")
    set("dev_organization_url", "https://github.com/YDWK")
    set("gpl_name", "Apache-2.0 license")
    set("gpl_url", "https://github.com/YDWK/YDWK/blob/master/LICENSE")
}

group = "io.github.ydwk.kotlinparser"
version = "1.0-SNAPSHOT"
val releaseVersion by extra(!version.toString().endsWith("-SNAPSHOT"))

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

spotless {
    kotlin {
        // Excludes build folder since it contains generated java classes.
        targetExclude("build/**")
        ktfmt("0.39").dropboxStyle()

        licenseHeader(
            """/*
 * Copyright 2022 YDWK inc.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ """)
    }

    kotlinGradle {
        target("**/*.gradle.kts")
        ktfmt("0.39").dropboxStyle()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}

application {
    mainClass.set("MainKt")
}



publishing {
    val isReleaseVersion = !version.toString().endsWith("SNAPSHOT")
    publications {
        create<MavenPublication>("kotlinParser") {
            from(components["java"])
            // artifactId = project.artifactId // or maybe archiveBaseName?
            pom {
                name.set(extra["name"] as String)
                description.set(extra["description"] as String)
                url.set("https://www.ydwk.org")
                issueManagement {
                    system.set("GitHub")
                    url.set("https://github.com/YDWK/YDWK/issues")
                }
                licenses {
                    license {
                        name.set(extra["gpl_name"] as String)
                        url.set(extra["gpl_url"] as String)
                    }
                }
                ciManagement { system.set("GitHub Actions") }
                inceptionYear.set("2022")
                developers {
                    developer {
                        id.set(extra["dev_id"] as String)
                        name.set(extra["dev_name"] as String)
                        email.set(extra["dev_email"] as String)
                        organization.set(extra["dev_organization"] as String)
                        organizationUrl.set(extra["dev_organization_url"] as String)
                    }
                }
                scm {
                    connection.set("https://github.com/YDWK/YDWK.git")
                    developerConnection.set("scm:git:ssh://git@github.com/YDWK/YDWK.git")
                    url.set("github.com/YDWK/YDWK")
                }
            }
        }
    }
    repositories {
        maven {
            name = "MavenCentral"
            val releaseRepo = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotRepo = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            url = uri((if (isReleaseVersion) releaseRepo else snapshotRepo))
            credentials {
                // try to get it from system gradle.properties
                println("Trying to get credentials from system properties")
                username =
                    when {
                        systemHasEnvVar("MAVEN_USERNAME") -> {
                            System.getenv("MAVEN_USERNAME")
                        }
                        project.hasProperty("MAVEN_USERNAME") -> {
                            project.property("MAVEN_USERNAME") as String
                        }
                        else -> {
                            logger.warn(
                                "MAVEN_USERNAME not found in system properties, meaning if you are trying to publish to maven central, it will fail")
                            null
                        }
                    }

                password =
                    when {
                        systemHasEnvVar("MAVEN_PASSWORD") -> {
                            System.getenv("MAVEN_PASSWORD")
                        }
                        project.hasProperty("MAVEN_PASSWORD") -> {
                            project.property("MAVEN_PASSWORD") as String
                        }
                        else -> {
                            logger.warn(
                                "MAVEN_PASSWORD not found in system properties, meaning if you are trying to publish to maven central, it will fail")
                            null
                        }
                    }
            }
        }
    }
}

fun systemHasEnvVar(varName: String): Boolean {
    return System.getenv(varName) != null
}

signing {
    afterEvaluate {
        // println "sign: " + isReleaseVersion
        val isRequired =
            releaseVersion &&
                    (tasks.withType<PublishToMavenRepository>().find { gradle.taskGraph.hasTask(it) } !=
                            null)
        setRequired(isRequired)
        sign(publishing.publications["kotlinParser"])
    }
}
