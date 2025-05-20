plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "org.sound.hive.android"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.sound.hive.android"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        manifestPlaceholders.putAll(
            mapOf(
                "redirectSchemeName" to "spotify-sdk",
                "redirectHostName" to "auth"
            )
        )

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.mviKotlin.main)
    implementation(libs.mviKotlin.extensions.coroutines)

    implementation(libs.auth.v212)
    implementation(libs.gson)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.room:room-testing:2.5.0")

    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.junit.platform.launcher)
    testImplementation(libs.junit.platform.engine)
    testImplementation("androidx.room:room-testing:2.7.1")
    testImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("org.jetbrains.kotlin:kotlin-test:${libs.versions.kotlin.get()}")

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.mviKotlin.main)
}

kapt {
    correctErrorTypes = true
}

tasks.register<Test>("runUnitTests") {
    description = "Runs unit tests without using the worker process"
    group = "verification"

    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath

    useJUnitPlatform()

    maxHeapSize = "1g"

    systemProperty("java.awt.headless", "true")
    systemProperty("file.encoding", "UTF-8")

    forkEvery = 0

    jvmArgs(
        "-Xmx1g",
        "-XX:MaxMetaspaceSize=512m",
        "-Dfile.encoding=UTF-8"
    )

    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }

    outputs.upToDateWhen { false }
}

tasks.register("directTest") {
    description = "Runs tests directly using Java command"
    group = "verification"

    doLast {
        val testClasspath = sourceSets["test"].runtimeClasspath.asPath

        val testClassesDir = sourceSets["test"].output.classesDirs.asPath

        val testClass = "org.sound.hive.android.api.SongApiClientTest"

        exec {
            executable = "java"
            args = listOf(
                "-cp", testClasspath,
                "-Djava.awt.headless=true",
                "-Dfile.encoding=UTF-8",
                "org.junit.platform.console.ConsoleLauncher",
                "execute",
                "--scan-classpath",
                "--classpath=$testClassesDir",
                "--select-class=$testClass"
            )
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()

    maxHeapSize = "1g"

    systemProperty("java.awt.headless", "true")
    systemProperty("file.encoding", "UTF-8")

    systemProperty("org.gradle.daemon", "false")

    systemProperty("org.gradle.workers.max", "1")
    systemProperty("org.gradle.workers.classpath.isolation", "none")

    forkEvery = 0
    maxParallelForks = 1

    jvmArgs(
        "-Xmx1g",
        "-XX:MaxMetaspaceSize=512m",
        "-Dfile.encoding=UTF-8",
        "-XX:+HeapDumpOnOutOfMemoryError"
    )

    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
        showExceptions = true
        showCauses = true
        showStackTraces = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }

    outputs.upToDateWhen { false }
}
