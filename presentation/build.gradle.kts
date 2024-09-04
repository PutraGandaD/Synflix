plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.putragandad.synflix.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BUILD_TYPE", "\"release\"")
        }

        debug {
            buildConfigField("String", "BUILD_TYPE", "\"debug\"")
        }
    }

    flavorDimensions += "memberTier"

    productFlavors {
        create("free") {
            dimension = "memberTier"
        }
        create("paid") {
            dimension = "memberTier"
        }
    }

    sourceSets {
        getByName("main") {
            setRoot("src/main")
        }

        getByName("free") {
            setRoot("src/free")
        }

        getByName("paid") {
            setRoot("src/paid")
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
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))

    // ViewModel
    implementation(libs.lifecycle.viewmodel.ktx)
    // ViewModel utilities for Compose
    implementation(libs.lifecycle.viewmodel.compose)
    // LiveData
    implementation(libs.lifecycle.livedata.ktx)
    // Lifecycles only (without ViewModel or LiveData)
    implementation(libs.lifecycle.runtime.ktx)
    // Lifecycle utilities for Compose
    implementation(libs.lifecycle.runtime.compose)
    // Saved state module for ViewModel
    implementation(libs.lifecycle.viewmodel.savedstate)
    // Annotation processor
    implementation(libs.lifecycle.compiler)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material.design)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.serialization.json)

    // Glide
    implementation(libs.glide)

    // CircleImageView
    implementation(libs.circle.image.view)

    // Facebook Shimmer Loading
    implementation(libs.facebook.shimmer)

    // Koin (Dependency Injection)
    // Declare koin-bom version
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    // Declare the koin dependencies that you need
    implementation(libs.koin.android)
    implementation(libs.koin.core.coroutines)
    implementation(libs.koin.androidx.workmanager)

    // Work Manager
    implementation(libs.work.manager.ktx)

    // Firebase
    // Import the Firebase BoM
    implementation(platform(libs.firebase.bom))
    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.perf)
}