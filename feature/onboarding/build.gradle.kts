plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

@Suppress("UnstableApiUsage")
android {
    namespace = "ru.itis.cooking.onboarding"
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

    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))

    // Hilt
    implementation(Dependencies.Hilt)
    kapt(Dependencies.KaptHilt)
    implementation(Dependencies.HiltVM)
    kapt(Dependencies.CompHilt)
    implementation(Dependencies.HiltNavigation)

    // Paging Compose
    implementation(Dependencies.PagerIndicator)
}
