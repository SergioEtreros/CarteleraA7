plugins {
   id("java-library")
   alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
   sourceCompatibility = JavaVersion.VERSION_21
   targetCompatibility = JavaVersion.VERSION_21
}