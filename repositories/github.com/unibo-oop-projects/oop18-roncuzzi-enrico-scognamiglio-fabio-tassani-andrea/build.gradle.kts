import com.github.spotbugs.SpotBugsTask
import de.aaschmid.gradle.plugins.cpd.Cpd
import java.awt.SystemColor.text

plugins {
    java
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
    checkstyle
    pmd
    id("de.aaschmid.cpd") version "2.0"
    id("com.github.spotbugs") version Versions.com_github_spotbugs_gradle_plugin
    `build-dashboard`
}

sourceSets {
    main {
        java {
            srcDirs("src")
        }
    }
}

repositories {
    mavenCentral()
    flatDir {
        dirs("lib")
    }
}

dependencies {
    implementation("junit:junit:4.12")
    implementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    File("lib")
        .takeIf { it.exists() }
        ?.takeIf { it.isDirectory }
        ?.listFiles()
        ?.filter { it.extension == "jar" }
        ?.forEach { implementation(files("lib/${it.name}")) }
}

spotbugs {
    effort = "max"
    reportLevel = "low"
    isShowProgress = true
    val excludeFile = File("${project.rootProject.projectDir}/config/spotbugs/excludes.xml")
    if (excludeFile.exists()) {
        excludeFilter = excludeFile
    }
    isIgnoreFailures = true
}

tasks.withType<SpotBugsTask> {
    reports {
        xml.setEnabled(false)
        html.setEnabled(true)
    }
}

pmd {
    ruleSets = listOf()
    ruleSetConfig = resources.text.fromFile("${project.rootProject.projectDir}/config/pmd/pmd.xml")
    isIgnoreFailures = true
}

cpd {
    isIgnoreFailures = true
}

tasks.withType<Cpd> {
    reports {
        xml.setEnabled(false)
        text.setEnabled(true)
    }
    language = "java"
    minimumTokenCount = 50
    ignoreFailures = true
    source = sourceSets["main"].allJava
}

checkstyle {
    isIgnoreFailures = true
}
