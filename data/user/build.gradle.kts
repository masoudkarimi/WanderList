@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.com.android.library)
  alias(libs.plugins.kotlin.android)
  kotlin("kapt")
  alias(libs.plugins.hilt.android)
}

android {
  namespace = "ir.masoudkarimi.wanderlist.data.user"
  compileSdk = 34

  defaultConfig {
    minSdk = 27

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
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
  implementation(projects.data.model)
  implementation(projects.data.core)

  implementation(libs.hilt.android)
  kapt(libs.hilt.android.compiler)
  implementation(libs.datastore.preferences)
  implementation(libs.core.ktx)
  implementation(libs.appcompat)
  implementation(libs.material)
  testImplementation(libs.junit)
  testImplementation(libs.turbine)
  testImplementation(libs.kotlinx.coroutines.test)
}