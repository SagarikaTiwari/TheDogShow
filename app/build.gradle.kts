

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = App.id
    compileSdk = Versions.compilesdk

    defaultConfig {
        applicationId = App.id
        minSdk = Versions.minsdk
        targetSdk = Versions.targetsdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = TestDependencies.instrumentation_runner
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(mapOf("path" to ":features")))
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":domain")))
    //Android Dependencies
    implementation (Dependencies.appcompat)
    implementation ( Dependencies.google_material )
    // Kotlin Dependencies
    implementation ( Dependencies.core_ktx )
    implementation ( Dependencies.kotlin_standard_library )
    implementation ( Dependencies.kotlin_reflect )
    //DI
    implementation ( Dependencies.hilt )
    implementation ( Dependencies.hilt_navigation_compose )
    kapt ( Dependencies.hilt_compiler )
}