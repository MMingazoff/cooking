plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "ru.itis.cooking.core.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
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

    implementation(libs.androidx.ktx)
    implementation(libs.lifecycle.ktx)
    implementation(libs.activity.compose)

    implementation(project(":core:domain"))

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.gson.converter)

    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.compiler)

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.viewmodel)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Preferences DataStore
    implementation(libs.datastore)
}
