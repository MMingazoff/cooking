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

    implementation(Dependencies.KotlinVersion)
    implementation(Dependencies.LifeCycleKtx)
    implementation(Dependencies.AppCompat)
    implementation(Dependencies.ComposeUi)
    implementation(Dependencies.CompPreview)
    implementation(Dependencies.MaterialDesign)

    // Coil
    implementation(Dependencies.Coil)
    implementation(Dependencies.AccCoil)

    // Lottie
    implementation(Dependencies.Lottie)

    // Jsoup
    implementation(Dependencies.Jsoup)
}
