plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.jetbrains.kotlin.android)
   alias(libs.plugins.compose.compiler)
   alias(libs.plugins.kotlin.serialization)
}

android {
   namespace = "com.senkou.tv"
   compileSdk = 35

   defaultConfig {
      applicationId = "com.senkou.tv"
      minSdk = 26
      targetSdk = 35
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

      applicationVariants.all {
         val variant = this
         variant.outputs.map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
               output.outputFileName = "CarteleraA7-TV.apk"
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
   implementation(libs.androidx.appcompat)
   implementation(platform(libs.androidx.compose.bom))
   implementation(libs.androidx.ui.tooling.preview)
   implementation(libs.androidx.tv.foundation)
   implementation(libs.androidx.tv.material)
   implementation(libs.androidx.lifecycle.runtime.ktx)
   implementation(libs.androidx.activity.compose)
   implementation(libs.navigation.compose)
   implementation(libs.coil.compose)
   implementation(libs.kotlinx.serialization.json)

   androidTestImplementation(platform(libs.androidx.compose.bom))
   debugImplementation(libs.androidx.ui.tooling)
   debugImplementation(libs.androidx.ui.test.manifest)
}

composeCompiler {
   enableStrongSkippingMode = true
}