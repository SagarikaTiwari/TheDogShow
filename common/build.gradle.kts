plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
}