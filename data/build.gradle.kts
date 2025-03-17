plugins {
   id("java-library")
   alias(libs.plugins.jetbrainsKotlinJvm)
   alias(libs.plugins.google.devtools.ksp)
}

java {
   sourceCompatibility = JavaVersion.VERSION_21
   targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
   implementation(project(":domain"))
   implementation(libs.kotlinx.coroutines.core)
   implementation(libs.hilt.core)
   ksp(libs.hilt.compiler)
}