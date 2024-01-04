plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = "ru.itis.cooking.core.ui"
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
    implementation(libs.activity.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material)

    // Coil
    implementation(libs.coil)
    implementation(libs.coil.accompanist)

    // Lottie
    implementation(libs.lottie)

    // Jsoup
    implementation(libs.jsoup)
    implementation(project(":core:domain"))
}
