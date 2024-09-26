plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.socialmediaproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.socialmediaproject"
        minSdk = 28
        targetSdk = 34
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
    configurations.all {
        resolutionStrategy.force ("androidx.annotation:annotation:1.8.2")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

    dependencies {
        implementation(platform("com.google.firebase:firebase-bom:33.3.0"))
        implementation(libs.material.v160)
        implementation(libs.appcompat.v130)
        implementation(libs.activity.v120)
        implementation(libs.constraintlayout.v204)
        implementation(libs.firebase.auth.v2101)
        implementation(libs.firebase.database.v2003)
        implementation(libs.firebase.storage)
        implementation(libs.annotation.jvm)
        testImplementation(libs.junit)
        implementation(libs.recyclerview)
        implementation(libs.circleimageview)
        implementation(libs.glide)
        annotationProcessor(libs.compiler) // Chỉ giữ một dòng này
        androidTestImplementation(libs.junit.v113)
        androidTestImplementation(libs.espresso.core.v340)

    }