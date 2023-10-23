plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
//    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.biteam.compose"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.biteam.compose"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

//    implementation(libs.ui)
    implementation(libs.core.ktx)
    implementation(libs.ui.compose.material3)
    implementation(libs.ui.compose.graphics)
    debugImplementation(libs.ui.compose.tooling)
    implementation(libs.ui.compose)
    implementation(libs.ui.compose.lottie)
    implementation(libs.ui.compose.tooling.preview)
    implementation(libs.ui.compose.navigation)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(platform(libs.ui.compose.bom))
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.ui.compose.bom))

    // Test
    testImplementation(libs.junit)
    testImplementation(libs.test.mockito)
    testImplementation(libs.test.hamcrest)
    testImplementation(libs.test.googleTruth)
    testImplementation(libs.test.robolectric)
    debugImplementation(libs.ui.test.manifest)
    testImplementation(libs.test.core.testing)
    testImplementation(libs.test.coroutineTest)
    testImplementation(libs.test.mockitoInline)
    testImplementation(libs.test.mockwebserver)
    testImplementation(libs.test.mockitoKotlin)
    testImplementation(libs.test.core.ktx.test)
    testImplementation(libs.test.junit.ktx.test)
    testImplementation(libs.test.mockitoAndroid)
    testImplementation(libs.test.hamcrest.library)
    implementation("androidx.work:work-runtime-ktx:2.7.1")

    // Android Test
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.test.navigation.compose)

    // Dagger Hilt
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.work)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)

    // Retrofit
    implementation (libs.retrofit2)
    implementation (libs.retrofit2.gson)
    implementation (libs.retrofit2.logging)

    // Coil
    implementation (libs.coil)
    implementation (libs.coil.compose)

    // Accompanist
    implementation(libs.ui.compose.accompanist.pager)
    implementation(libs.ui.compose.accompanist.insets)
}