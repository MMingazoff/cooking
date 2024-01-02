plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "ru.itis.cooking.core.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33
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

    implementation(Dependencies.KotlinVersion)
    implementation(Dependencies.LifeCycleKtx)
    implementation(Dependencies.AppCompat)

    implementation(project(":core:domain"))

    // Retrofit
    implementation(Dependencies.Retrofit)
    implementation(Dependencies.GsonConverter)
    implementation(Dependencies.Okhttp)
    implementation(Dependencies.Interceptor)

    // Room
    implementation(Dependencies.RoomRun)
    implementation(Dependencies.RoomKtx)
    annotationProcessor(Dependencies.RoomAn)
    kapt(Dependencies.RoomComp)

    // Hilt
    implementation(Dependencies.Hilt)
    kapt(Dependencies.KaptHilt)
    implementation(Dependencies.HiltVM)
    kapt(Dependencies.CompHilt)
    implementation(Dependencies.HiltNavigation)

    // Preferences DataStore
    implementation(Dependencies.DataStore)
}
