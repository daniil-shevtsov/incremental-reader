buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.0-beta01")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.20")

        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.2")
    }
}
allprojects {
    repositories {
        jcenter()
        google()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>> {
        kotlinOptions {
            freeCompilerArgs = listOf(
                "-Xuse-experimental=kotlin.ExperimentalStdlibApi",
                "-Xuse-experimental=kotlin.time.ExperimentalTime"
            )
        }
    }
}
