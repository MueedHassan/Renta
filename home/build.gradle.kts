
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("com.google.devtools.ksp")version "1.9.22-1.0.16"
    id("kotlin-kapt")
    id ("org.jetbrains.kotlin.plugin.serialization")

}


kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

android {
    namespace = "com.example.home"
    compileSdk = 34
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    defaultConfig {
        minSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}



//repositories {
//    google()
//    mavenCentral()
//
//}

dependencies {

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.compose.ui:ui-android:1.6.6")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.wear.compose:compose-material:1.3.1")
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation("com.google.firebase:firebase-storage-ktx:20.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Material Design 3
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    //

    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // custom design system based on Foundation)
    implementation("androidx.compose.material:material-icons-core")
    // Optional - Add full set of material icons
    implementation("androidx.compose.material:material-icons-extended")
    // Optional - Add window size utils
    implementation("androidx.compose.material3:material3-window-size-class")

    // Optional - Integration with activities
    implementation("androidx.activity:activity-compose:1.9.0")
    // Optional - Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    //navigation
    implementation  ("androidx.navigation:navigation-compose:2.7.7")

    implementation("androidx.compose.material:material:1.6.6")
    val nav_version = "2.7.6"

    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation(project(":addnewproperty"))
    //Destination Rama
    implementation("io.github.raamcosta.compose-destinations:core:1.9.63")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.9.63")
    //APPLovin
    implementation("com.applovin:applovin-sdk:12.3.0")
    implementation ("io.coil-kt:coil-compose:2.5.0")
    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
    // Declare the dependency for the Cloud Firestore library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-firestore")
    implementation(platform("io.insert-koin:koin-bom:3.6.0-wasm-alpha2"))
    implementation("io.insert-koin:koin-core")
    implementation ("io.insert-koin:koin-android")
    implementation ("io.insert-koin:koin-androidx-compose:3.5.0")
    implementation ("androidx.activity:activity-ktx:1.9.0")
    implementation ("com.aallam.openai:openai-client:3.7.1")
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.6")
    implementation("io.ktor:ktor-client-core:2.3.10")
    implementation("io.ktor:ktor-client-cio:2.3.10")
// Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")
    implementation ("com.google.firebase:firebase-core:21.1.1")
    // Also add the dependency for the Google Play services library and specify its version
    implementation("com.google.android.gms:play-services-auth:21.1.0")
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-ml-modeldownloader")
    // Also add the dependency for the TensorFlow Lite library and specify its version
    implementation("org.tensorflow:tensorflow-lite:2.3.0")

    val ktorversion="2.3.10"
    implementation("io.ktor:ktor-client-android:$ktorversion")
    implementation("io.ktor:ktor-client-serialization:$ktorversion")
    implementation("io.ktor:ktor-client-logging:$ktorversion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorversion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorversion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorversion")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("io.ktor:ktor-client-apache:$ktorversion")
    implementation("io.ktor:ktor-server-netty:1.6.5")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation ("com.google.code.gson:gson:2.10")
}
/*
}
