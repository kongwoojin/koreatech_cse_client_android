import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

val localStoreFile: String = gradleLocalProperties(rootDir).getProperty("localStoreFile")
val localStorePassword: String = gradleLocalProperties(rootDir).getProperty("localStorePassword")
val localKeyAlias: String = gradleLocalProperties(rootDir).getProperty("localKeyAlias")
val localKeyPassword: String = gradleLocalProperties(rootDir).getProperty("localKeyPassword")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.android.gms.oss-licenses-plugin")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    kotlin("kapt")
    kotlin("plugin.parcelize")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.kongjak.koreatechboard"
        minSdk = 25
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file(localStoreFile)
            storePassword = localStorePassword
            keyAlias = localKeyAlias
            keyPassword = localKeyPassword
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
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
        viewBinding = true
        dataBinding = true
    }
    namespace = "com.kongjak.koreatechboard"
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.2")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation ("androidx.browser:browser:1.4.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.github.bumptech.glide:glide:4.13.0")
    implementation("com.github.bumptech.glide:okhttp3-integration:4.13.0")
    implementation("com.github.bumptech.glide:annotations:4.13.0")
    implementation("com.google.dagger:hilt-android:2.43.2")
    kapt("com.google.dagger:hilt-android-compiler:2.43.2")
    implementation("androidx.preference:preference:1.2.0")
    implementation("com.google.android.gms:play-services-oss-licenses:17.0.0")
    kapt("com.github.bumptech.glide:compiler:4.13.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
