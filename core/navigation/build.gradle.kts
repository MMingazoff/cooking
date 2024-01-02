plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = "ru.itis.cooking.core.navigation"
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
}

dependencies {

    implementation(Dependencies.KotlinVersion)
}
