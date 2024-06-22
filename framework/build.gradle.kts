plugins {
   id("com.android.library")
   id("org.jetbrains.kotlin.android")
   id("org.jetbrains.kotlin.plugin.serialization")
}

android {
   namespace = "com.senkou.framework"
   compileSdk = 34

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
   }
   compileOptions {
      sourceCompatibility = JavaVersion.VERSION_17
      targetCompatibility = JavaVersion.VERSION_17
   }
   kotlinOptions {
      jvmTarget = "17"
   }
}

dependencies {

   implementation(project(":data"))
   implementation(project(":domain"))
//   implementation(project(":usecases"))
   implementation("androidx.core:core-ktx:1.13.1")
   implementation("androidx.appcompat:appcompat:1.7.0")
   implementation("com.google.android.material:material:1.12.0")
   implementation("org.apache.commons:commons-text:1.12.0")
   implementation("com.squareup.retrofit2:converter-gson:2.11.0")
   implementation("org.jsoup:jsoup:1.17.2")
   implementation("com.google.code.gson:gson:2.11.0")
   implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")


//   testImplementation("junit:junit:4.13.2")
   androidTestImplementation("androidx.test.ext:junit:1.1.5")
   androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}