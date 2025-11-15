plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.viewapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.viewapp"
        minSdk = 33
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.core)
    implementation(libs.recyclerview)
    implementation(libs.firebase.inappmessaging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0")
// основная библиотека
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.0")
// ядро
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.0")
// аннотации
    implementation("com.google.code.gson:gson:2.8.9")
}