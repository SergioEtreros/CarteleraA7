plugins {
   id("com.android.library")
   alias(libs.plugins.jetbrains.kotlin.android)
   alias(libs.plugins.kotlin.serialization)
}

android {
   namespace = "com.senkou.framework"
   compileSdk = 35

   defaultConfig {
      minSdk = 25

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
      }
      create("applicationVariants") {
      }
   }
   compileOptions {
      sourceCompatibility = JavaVersion.VERSION_21
      targetCompatibility = JavaVersion.VERSION_21
   }
   kotlinOptions {
      jvmTarget = "21"
   }
}

dependencies {

   implementation(project(":data"))
   implementation(project(":domain"))

   implementation(libs.androidx.core.ktx)
   implementation(libs.kotlinx.serialization.json)
   implementation(libs.jsoup)
   implementation(libs.commons.text)
   implementation(libs.gson)

   testImplementation(libs.junit)
   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)
}