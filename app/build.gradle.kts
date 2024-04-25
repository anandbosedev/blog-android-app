import com.google.gson.Gson
import com.google.gson.JsonObject

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.anandbose.blogapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.anandbose.blogapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 10
        versionName = "1.0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storeFile = File(rootDir, "keystore/debug.jks")
            storePassword = "android"
        }

        create("release") {
            val propsFile = File(rootDir, "keystore/release/credentials.json")
            if (propsFile.exists() && propsFile.canRead()) {
                val propsFileContents = propsFile.readBytes().toString(Charsets.UTF_8)
                val gson = Gson()
                val json = gson.fromJson(propsFileContents, JsonObject::class.java)
                keyPassword = json.get("keypassword").asString
                keyAlias = json.get("alias").asString
                storePassword = json.get("storepassword").asString
                storeFile = File(rootDir, "keystore/release/releasekey.jks")
            }
        }
    }
    buildTypes {
        debug {
            signingConfig = signingConfigs["debug"]
        }
        release {
            signingConfig = signingConfigs["release"]
            isMinifyEnabled = true
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material3.window)
    implementation(libs.androidx.navigation)

    implementation(libs.ktor.core)
    implementation(libs.ktor.cio)
    implementation(libs.ktor.contentNegotiation)
    implementation(libs.ktor.contentNegotiation.json)
    implementation(libs.jetbrains.kotlin.serialization.json)
    implementation(libs.koil)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}