plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "ru.itis.cooking.core.domain"
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
    testImplementation(Dependencies.JUnitTest)

    // Hilt
    implementation(Dependencies.Hilt)
    kapt(Dependencies.KaptHilt)
    implementation(Dependencies.HiltVM)
    kapt(Dependencies.CompHilt)
    implementation(Dependencies.HiltNavigation)
    implementation(Dependencies.GsonConverter)

    // Room
    implementation(Dependencies.RoomRun)
    implementation(Dependencies.RoomKtx)
    annotationProcessor(Dependencies.RoomAn)
    kapt(Dependencies.RoomComp)

    // Testing
    testImplementation(Dependencies.JUnitTest)
    testImplementation(Dependencies.ArchTest)
    testImplementation(Dependencies.Truth)
    testImplementation(Dependencies.HiltTest)
    kaptTest(Dependencies.HiltAndroidTest)
}
