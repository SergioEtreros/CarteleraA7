import java.util.Properties

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

      val properties = Properties()
      properties.load(project.rootProject.file("local.properties").readText().byteInputStream())

      val tmdbApiKey = properties.getProperty("TMDB_API_KEY", "")
      buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"")
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

   buildFeatures {
      buildConfig = true
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
   implementation(libs.retrofit)
   implementation(libs.retrofit.converter.kotlinx.serialization)
   implementation(libs.okhttp)
   implementation(libs.okhttp.logging.interceptor)

   testImplementation(libs.junit)
   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)
}