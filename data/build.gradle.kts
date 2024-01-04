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


//    implementation("androidx.core:core-ktx:1.9.0")
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.10.0")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    // Retrofit
//    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
//    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
//    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
//    androidTestImplementation ("com.squareup.okhttp3:mockwebserver:4.9.6")
//
//    // hilt dependencies
//
//    implementation("com.google.dagger:hilt-android:2.48")
//    kapt("com.google.dagger:hilt-android-compiler:2.48")
//
//    // Local unit tests
//    testImplementation ("androidx.test:core:1.4.0")
//    testImplementation  ("androidx.arch.core:core-testing:2.1.0")
//    testImplementation  ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1")
//    testImplementation ("com.google.truth:truth:1.1.3")
//    testImplementation ("com.squareup.okhttp3:mockwebserver:4.9.1")
//    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.1.0-alpha04")
//
//    // Instrumentation tests
//    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.37")
//    kaptAndroidTest ("com.google.dagger:hilt-android-compiler:2.37")
//    androidTestImplementation ("junit:junit:4.13.2")
//    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1")
//    androidTestImplementation ("androidx.arch.core:core-testing:2.1.0")
//    androidTestImplementation ("com.google.truth:truth:1.1.3")
//    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation ("androidx.test:core-ktx:1.4.0")
//    androidTestImplementation ("com.squareup.okhttp3:mockwebserver:4.9.6")
//    androidTestImplementation ("io.mockk:mockk-android:1.12.5")
//    androidTestImplementation ("androidx.test:runner:1.4.0")
//
//    testImplementation ("junit:junit:4.13.2")
//    testImplementation ("org.mockito:mockito-core:2.25.0")
//    testImplementation ("org.mockito:mockito-inline:2.13.0")
//    testImplementation ("io.mockk:mockk:1.12.5")
//    testImplementation("pl.pragmatists:JUnitParams:1.1.1")

    //Android Dependencies
    implementation ( Dependencies.appcompat )
            implementation ( Dependencies.google_material )

            // Kotlin ( Dependencies
            implementation ( Dependencies.core_ktx )
            implementation ( Dependencies.kotlin_standard_library )
            implementation ( Dependencies.kotlin_reflect )
            implementation ( Dependencies.kotlin_coroutines_core )
            implementation ( Dependencies.kotlin_coroutines_android )
            implementation ( Dependencies.viewmodel_ktx )

            // Networking
            implementation ( Dependencies.retrofit )
            implementation ( Dependencies.retrofit_gson )
            implementation ( Dependencies.logging_interceptor )

            //DI
            implementation ( Dependencies.hilt )
            kapt ( Dependencies.hilt_compiler )


            // Test Dependencies
            testImplementation ( TestDependencies.junit )
            testImplementation ( TestDependencies.mockk )
            testImplementation ( TestDependencies.jupiter )
            testImplementation ( TestDependencies.coroutines_test )




}