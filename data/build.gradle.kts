@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.koreatechboard.library)
    alias(libs.plugins.koreatechboard.firebase)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.serialization)
    id("app.cash.sqldelight") version "2.0.2"
    kotlin("kapt")
}

android {
    defaultConfig {
        targetSdk = 34

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "proguard-debug.pro"
            )
        }
    }
    namespace = "com.kongjak.koreatechboard.data"
}

dependencies {

    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.paging.common)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.negotiation)
    implementation(libs.ktor.kotlinx.serialization)
    implementation(libs.kotlinx.serialization)

    implementation(libs.koin.core)

    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)

    implementation(libs.sqldelight.coroutines.extensions)

    testImplementation(libs.junit)

    androidTestImplementation(libs.esspresso)
    androidTestImplementation(libs.junit.test)
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.kongjak.koreatechboard.data")
        }
    }
}
