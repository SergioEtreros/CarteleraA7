plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.jetbrains.kotlin.android)
   alias(libs.plugins.google.devtools.ksp)
   alias(libs.plugins.kotlin.serialization)
   alias(libs.plugins.compose.compiler)
   alias(libs.plugins.hilt)
}

android {
   namespace = "com.senkou.carteleraa7"
   compileSdk = 35

   defaultConfig {
      applicationId = "com.senkou.carteleraa7"
      minSdk = 25
      targetSdk = 35
      versionCode = 8
      versionName = "1.9"

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

         applicationVariants.all {
            val variant = this
            variant.outputs.map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
               .forEach { output ->
                  output.outputFileName = "CarteleraA7.apk"
               }
         }
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
      compose = true
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

   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.lifecycle.runtime.ktx)
   implementation(libs.androidx.activity.compose)
   implementation(platform(libs.androidx.compose.bom))
   implementation(libs.androidx.ui)
   implementation(libs.androidx.ui.graphics)
   implementation(libs.androidx.ui.tooling.preview)
   implementation(libs.androidx.material3)
   implementation(libs.coil.compose)
   implementation(libs.navigation.compose)
   implementation(libs.kotlinx.serialization.json)
   implementation(libs.room.ktx)
   implementation(libs.hilt.android)
   ksp(libs.room.compiler)

   implementation(libs.hilt.core)
   implementation(libs.hilt.android)
   implementation(libs.androidx.hilt.navigation.compose)
   ksp(libs.hilt.compiler)

   testImplementation(libs.junit)
   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)
   androidTestImplementation(platform(libs.androidx.compose.bom))
   androidTestImplementation(libs.androidx.ui.test.junit4)
   debugImplementation(libs.androidx.ui.tooling)
   debugImplementation(libs.androidx.ui.test.manifest)

}

