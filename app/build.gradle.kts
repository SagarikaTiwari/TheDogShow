

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

//    implementation("androidx.core:core-ktx:1.9.0")
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.10.0")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation(project(mapOf("path" to ":features")))
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":domain")))

    // hilt Dependencies
//
//    implementation("com.google.dagger:hilt-android:2.48")
//    kapt("com.google.dagger:hilt-android-compiler:2.48")


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