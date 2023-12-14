buildscript {
  // https://github.com/google/dagger/issues/3068
  configurations.all {
    resolutionStrategy.eachDependency {
      when (requested.name) {
          "javapoet" -> useVersion("1.13.0")
      }
    }
  }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.com.android.application) apply false
  alias(libs.plugins.com.android.library) apply false
  alias(libs.plugins.org.jetbrains.kotlin.android) apply false
  alias(libs.plugins.spotless) apply false
  alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
}
true // Needed to make the Suppress annotation work for the plugins block

subprojects {
  apply(plugin = "com.diffplug.spotless")
  configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
      target("**/*.kt", "**/*.kts")
      targetExclude("$buildDir/**/*.kt", "bin/**/*.kt", "buildSrc/**/*.kt")
      // by default the target is every '.kt' and '.kts` file in the java sourcesets
      ktlint() // has its own section below
        .userData(mapOf("android" to "true"))
        .editorConfigOverride(
          mapOf(
            "ktlint_standard_function-naming" to "disabled",
          ),
        )
    }

    kotlinGradle {
      target("*.gradle.kts") // default target for kotlinGradle
      ktlint()
    }
  }
}