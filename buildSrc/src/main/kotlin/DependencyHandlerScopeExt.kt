import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.appDependencies() {
    android()

    navigation()

    network()

    rxJava()

    debugImplementation(Dependency.LEAK_CANARY)

    dependencyInjection()

}

fun DependencyHandlerScope.unitTestDependencies() {
    testImplementation(Dependency.CORE_TESTING)

    testRuntimeOnly(Dependency.JUPITER_ENGINE)

    testImplementation(Dependency.JUPITER_API)
    testImplementation(Dependency.JUPITER_PARAMETERS)
    testImplementation(Dependency.MOCKK)
}

fun DependencyHandlerScope.instrumentationTestDependencies() {
    androidTestImplementation(Dependency.JUNIT_EXT)
    androidTestImplementation(Dependency.TEST_RUNNER)
    androidTestImplementation(Dependency.TEST_RULES)

    androidTestImplementation(Dependency.ESPRESSO)
    androidTestImplementation(Dependency.KAKAO)
    androidTestImplementation(Dependency.MOCKK_ANDROID)

    androidTestImplementation(Dependency.NAVIGATION_TESTING)
    debugImplementation(Dependency.FRAGMENT_TESTING)

    androidTestImplementation(Dependency.MOCK_WEB_SERVER)
}

fun DependencyHandlerScope.android() {
    implementation(Dependency.APP_COMPAT)
    implementation(Dependency.CORE_KTX)
    implementation(Dependency.CONSTRAINT_LAYOUT)
}

fun DependencyHandlerScope.navigation() {
    implementation(Dependency.NAVIGATION_FRAGMENT)
    implementation(Dependency.NAVIGATION_UI)

    implementation(Dependency.LIFECYCLE)
}

fun DependencyHandlerScope.network() {
    implementation(Dependency.RETROFIT)
    implementation(Dependency.LOGGING_INTERCEPTOR)

    implementation(Dependency.GSON)
    implementation(Dependency.GSON_CONVERTER)

    implementation(Dependency.RETROFIT_RX_ADAPTER)
}

fun DependencyHandlerScope.rxJava() {
    implementation(Dependency.RX_JAVA)
    implementation(Dependency.RX_ANDROID)
    implementation(Dependency.RX_KOTLIN)
}

fun DependencyHandlerScope.dependencyInjection() {
    implementation(Dependency.DAGGER)
    kapt(Dependency.DAGGER_COMPILER)
}

//TODO: Do something about these weird hacks
fun DependencyHandlerScope.implementation(dependency: String) {
    "implementation"(dependency)
}

fun DependencyHandlerScope.kapt(dependency: String) {
    "kapt"(dependency)
}

fun DependencyHandlerScope.testImplementation(dependency: String) {
    "testImplementation"(dependency)
}

fun DependencyHandlerScope.testRuntimeOnly(dependency: String) {
    "testRuntimeOnly"(dependency)
}

fun DependencyHandlerScope.androidTestImplementation(dependency: String) {
    "androidTestImplementation"(dependency)
}

fun DependencyHandlerScope.debugImplementation(dependency: String) {
    "debugImplementation"(dependency)
}
