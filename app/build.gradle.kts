plugins {
    id(Plugin.ANDROID_APP)

    with(Plugin.Kotlin) {
        kotlin(ANDROID)
        kotlin(EXTENSIONS)
        kotlin(KAPT)
    }

    id(Plugin.SAFE_ARGS)

    id(Plugin.DEPENDENCY_UPDATE_DETECTION) version Plugin.Version.DEPENDENCY_UPDATE_DETECTION
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

}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", "1.5.0"))

    appDependencies()

    unitTestDependencies()

    instrumentationTestDependencies()
}
