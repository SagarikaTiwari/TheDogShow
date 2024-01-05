plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = App.id
    compileSdk = Versions.compilesdk

    defaultConfig {
        minSdk = Versions.minsdk

        testInstrumentationRunner = TestDependencies.instrumentation_runner
        consumerProguardFiles("consumer-rules.pro")
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

    implementation(project(mapOf("path" to ":common")))
    implementation(project(mapOf("path" to ":domain")))
    //Android Dependencies
    implementation(Dependencies.appcompat)
    implementation(Dependencies.google_material)
    // Kotlin ( Dependencies
    implementation(Dependencies.core_ktx)
    implementation(Dependencies.kotlin_standard_library)
    implementation(Dependencies.kotlin_reflect)
    implementation(Dependencies.kotlin_coroutines_core)
    implementation(Dependencies.kotlin_coroutines_android)
    implementation(Dependencies.viewmodel_ktx)
    // Networking
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_gson)
    implementation(Dependencies.logging_interceptor)
    //DI
    implementation(Dependencies.hilt)
    kapt(Dependencies.hilt_compiler)
    // Test Dependencies
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    //testImplementation ( TestDependencies.jupiter )
    testImplementation(TestDependencies.coroutines_test)
}