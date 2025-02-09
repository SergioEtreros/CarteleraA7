plugins {
   id("com.android.application")
   id("org.jetbrains.kotlin.android")
}

android {
   namespace = "com.senkou.tv"
   compileSdk = 34

   defaultConfig {
      applicationId = "com.senkou.tv"
      minSdk = 26
      targetSdk = 34
      versionCode = 1
      versionName = "1.0"
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
      sourceCompatibility = JavaVersion.VERSION_17
      targetCompatibility = JavaVersion.VERSION_17
   }
   kotlinOptions {
      jvmTarget = "17"
   }
   buildFeatures {
      compose = true
   }
   composeOptions {
      kotlinCompilerExtensionVersion = "1.5.14"
   }
   packaging {
      resources {
         excludes += "/META-INF/{AL2.0,LGPL2.1}"
      }
   }
}

dependencies {

   implementation(project(":data"))
   implementation(project(":framework"))
   implementation(project(":domain"))
   implementation(project(":usecases"))
   implementation("androidx.core:core-ktx:1.13.1")
   implementation("androidx.appcompat:appcompat:1.7.0")
   implementation(platform("androidx.compose:compose-bom:2024.06.00"))
   implementation("androidx.compose.ui:ui-tooling-preview")
   implementation("androidx.tv:tv-foundation:1.0.0-alpha10")
   implementation("androidx.tv:tv-material:1.0.0-beta01")
   implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.2")
   implementation("androidx.activity:activity-compose:1.9.0")
   implementation("androidx.navigation:navigation-compose:2.7.7")

   implementation("io.coil-kt:coil-compose:2.6.0")
   implementation("androidx.lifecycle:lifecycle-runtime-compose-android:2.8.2")

   androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
   androidTestImplementation("androidx.compose.ui:ui-test-junit4")
   debugImplementation("androidx.compose.ui:ui-tooling")
   debugImplementation("androidx.compose.ui:ui-test-manifest")
}