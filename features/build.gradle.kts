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

//    implementation("androidx.core:core-ktx:1.9.0")
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.10.0")
//
//    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    implementation ("androidx.core:core-ktx:1.12.0")
//    implementation ("androidx.compose.ui:ui:1.5.1")
//    implementation ("androidx.compose.material:material:1.5.1")
//    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.1")
//    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
//    implementation ("androidx.activity:activity-compose:1.7.2")
//    testImplementation ("junit:junit:4.13.2")
//    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
//     debugImplementation ("androidx.compose.ui:ui-tooling:1.5.1")
//
//    implementation ("androidx.compose.material:material:1.5.1")
//
//    implementation ("androidx.compose.material:material-icons-core:1.5.1")
//    implementation ("androidx.compose.material:material-icons-extended:1.5.1")
//
//    // ViewModel
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0-alpha02")
//    // ViewModel utilities for Compose
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0-alpha02")
//
//    // Coroutines
//    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
//    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
//
//
//    // Coil
//    implementation("io.coil-kt:coil:2.4.0")
//    implementation("io.coil-kt:coil-compose:2.4.0")
//    // Coroutine Lifecycle Scopes
//    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
//    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
//
//
//    // hilt dependencies
//
//    implementation("com.google.dagger:hilt-android:2.48")
//    kapt("com.google.dagger:hilt-android-compiler:2.48")
//
//    implementation ("androidx.palette:palette-ktx:1.0.0")
//
//    implementation ("androidx.palette:palette:1.0.0")
//
//    implementation ("androidx.navigation:navigation-compose:2.7.2")
//
//    kapt ("androidx.hilt:hilt-compiler:1.0.0")
//    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
//    // Local unit tests
//    testImplementation ("androidx.test:core:1.4.0")
//    testImplementation  ("androidx.arch.core:core-testing:2.1.0")
//    testImplementation  ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1")
//    testImplementation ("com.google.truth:truth:1.1.3")
//    testImplementation ("com.squareup.okhttp3:mockwebserver:4.9.1")
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
//     androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.1")
//     debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.1")
//
//    testImplementation ("junit:junit:4.13.2")
//    testImplementation ("org.mockito:mockito-core:2.25.0")
//    testImplementation ("org.mockito:mockito-inline:2.13.0")
//    testImplementation ("io.mockk:mockk:1.12.5")
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
            testImplementation ( TestDependencies.jupiter )
            androidTestImplementation ( TestDependencies.compose_ui )
            debugImplementation ( TestDependencies.compose_ui_manifest )

}