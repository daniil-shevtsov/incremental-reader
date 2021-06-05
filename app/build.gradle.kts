plugins {
    id(Plugin.ANDROID_APP)


    with(Plugin.Kotlin) {
        kotlin(ANDROID)
        kotlin(KAPT)
    }
    id("kotlin-parcelize")
    id("com.vanniktech.android.junit.jacoco") version "0.16.0"
    id ("com.xcporter.metaview") version "0.0.5"
    id(Plugin.SAFE_ARGS)
//    id(Plugin.JACOCO)
//    id("mergedJacocoReport")

    id(Plugin.DEPENDENCY_UPDATE_DETECTION) version Version.DEPENDENCY_UPDATE_DETECTION
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK)

    defaultConfig {
        with(AndroidConfig) {
            applicationId = APP_ID

            minSdkVersion(MIN_SDK)
            targetSdkVersion(TARGET_SDK)

            versionCode = VERSION_CODE
            versionName = VERSION_NAME

            testInstrumentationRunner = INSTRUMENTATION_RUNNER
        }

    }

    buildTypes {
        getByName("debug") {
            isTestCoverageEnabled = true
        }

        getByName("release") {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }

    generateUml {
        classTree {}
    }

}



dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", "1.5.0"))

    appDependencies()

    unitTestDependencies()

    instrumentationTestDependencies()
}

tasks.withType<Test> {
    useJUnitPlatform()
}