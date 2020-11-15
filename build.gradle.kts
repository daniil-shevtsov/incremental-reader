buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")

        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0")
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
