plugins {
    id("java-library")
    id("maven-publish")
    id("io.freefair.lombok") version "6.5.1"
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
    testImplementation("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.test {
    useJUnitPlatform()
}

group = "lol.pyr"
version = "1.3.0"

tasks.compileJava {
    options.release.set(16)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            versionMapping {
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }

            pom {
                name.set("extendedcommands")
                description.set("Extension of the Bukkit command framework")
                url.set("https://github.com/Pyr2115/ExtendedBukkitCommands")
            }

            from(components["java"])
        }
    }
    repositories {
        maven {
            credentials {
                username = properties["DIST_USERNAME"] as String?
                password = properties["DIST_PASSWORD"] as String?
            }
            url = uri("https://repo.pyr.lol/releases/")
        }
    }
}