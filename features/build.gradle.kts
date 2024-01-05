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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
    }


}

dependencies {
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":common")))
    //Android Dependencies
    implementation ( Dependencies.appcompat )
    implementation ( Dependencies.google_material )
    // Kotlin dependencies
    implementation ( Dependencies.core_ktx )
    implementation ( Dependencies.kotlin_standard_library )
    implementation ( Dependencies.kotlin_reflect )
    implementation ( Dependencies.kotlin_coroutines_core )
    implementation ( Dependencies.kotlin_coroutines_android )
    implementation ( Dependencies.viewmodel_ktx )
    //DI
    implementation ( Dependencies.hilt )
    implementation ( Dependencies.hilt_navigation_compose )
    kapt ( Dependencies.hilt_compiler )
    //Compose
    implementation ( platform(Dependencies.compose_bom ))
    implementation ( Dependencies.compose_foundation )
    implementation ( Dependencies.compose_ui )
    implementation ( Dependencies.compose_preview )
    implementation ( Dependencies.compose_tooling )
    implementation ( Dependencies.compose_livedata )
    implementation ( Dependencies.compose_material )
    implementation ( Dependencies.coil_compose )
    implementation ( Dependencies.coil_svg )
    implementation ( Dependencies.navigation_compose )
    // Test dependencies
    testImplementation ( TestDependencies.junit )
    testImplementation ( TestDependencies.mockk )
    testImplementation ( TestDependencies.coroutines_test )
    androidTestImplementation ( TestDependencies.compose_ui )
    debugImplementation ( TestDependencies.compose_ui_manifest )
}