// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
 /*   alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false // The version for libs.plugins.kotlin.android is specified in the /gradle/libs.versions.toml file.
    alias(libs.plugins.kotlin.compose) apply false*/



        id("com.android.application") version "8.12.2" apply false
        id("org.jetbrains.kotlin.android") version "2.2.0" apply false // Ensure this is up-to-date
        id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false // Ensure this is up-to-date



    id("com.google.dagger.hilt.android") version "2.57" apply false
    alias(libs.plugins.google.gms.google.services) apply false


}
buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.3")
    }
}
