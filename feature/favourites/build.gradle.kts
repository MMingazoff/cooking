plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

@Suppress("UnstableApiUsage")
android {
    namespace = "ru.itis.cooking.favourites"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

dependencies {

    implementation(libs.androidx.ktx)
    implementation(libs.lifecycle.ktx)
    implementation(libs.lifecycle.compose)
    implementation(libs.activity.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material)

    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:architecture"))

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.viewmodel)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
}
