plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.5.0"
}

android {
    compileSdk = 32  // should be 26? no? everything breaks so I guess we're stuck on this one then

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }

    defaultConfig {
        applicationId = "com.gruppe14_in2000_v22.vaerklar"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.gruppe14_in2000_v22.vaerklar"
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.6.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("com.github.kittinunf.fuel:fuel:2.3.1")
    implementation("com.github.kittinunf.fuel:fuel-coroutines:2.3.1")
    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("androidx.fragment:fragment-ktx:1.4.1")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.1.1")
    implementation("com.google.android.gms:play-services-location:19.0.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")


    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    // Compose
    implementation("androidx.compose.ui:ui:1.1.1")
    implementation("androidx.compose.material:material:1.1.1")
    implementation("androidx.compose.ui:ui-tooling:1.1.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.1.1")
    implementation ("androidx.compose.material:material:1.1.1")
    implementation("androidx.navigation:navigation-compose:2.4.2")
    // Splash screen
    implementation("androidx.core:core-splashscreen:1.0.0-rc01")


    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.4.1")

    implementation("androidx.activity:activity-ktx:1.6.0-alpha04")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.1")

    implementation("com.google.accompanist:accompanist-pager:0.15.0")
    implementation ("com.google.accompanist:accompanist-pager:0.15.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.15.0")

}