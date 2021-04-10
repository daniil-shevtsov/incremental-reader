import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.appDependencies() {
    android()

    recyclerView()

    coroutines()

    navigation()

    network()

    rxJava()

    dependencyInjection()

    database()

    misc()
}

private fun DependencyHandlerScope.recyclerView() {
    implementation ("com.xwray:groupie:${Version.GROUPIE}")
    implementation ("com.xwray:groupie-viewbinding:${Version.GROUPIE}")
}

fun DependencyHandlerScope.coroutines() {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
}

fun DependencyHandlerScope.unitTestDependencies() {
    testImplementation("androidx.arch.core:core-testing:${Version.CORE_TESTING}")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Version.JUPITER}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${Version.JUPITER}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${Version.JUPITER}")
    testImplementation("io.mockk:mockk:${Version.MOCKK}")
}

fun DependencyHandlerScope.instrumentationTestDependencies() {
    androidTestImplementation("androidx.test.ext:junit:${Version.JUNIT}")
    androidTestImplementation("androidx.test:runner:${Version.JUNIT}")
    androidTestImplementation("androidx.test:rules:${Version.TEST_RULES}")

    androidTestImplementation("androidx.test.espresso:espresso-core:${Version.ESPRESSO}")
    androidTestImplementation("com.agoda.kakao:kakao:${Version.KAKAO}")
    androidTestImplementation("io.mockk:mockk-android:${Version.MOCKK}")

    androidTestImplementation("androidx.navigation:navigation-testing:${Version.NAVIGATION}")
    debugImplementation("androidx.fragment:fragment-testing:${Version.FRAGMENT_TESTING}")

    androidTestImplementation("com.squareup.okhttp3:mockwebserver:${Version.OK_HTTP}")
}

fun DependencyHandlerScope.android() {
    implementation("androidx.appcompat:appcompat:${Version.APP_COMPAT}")
    implementation("androidx.core:core-ktx:${Version.CORE_KTX}")
    implementation("androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT}")
}

fun DependencyHandlerScope.navigation() {
    implementation("androidx.navigation:navigation-fragment-ktx:${Version.NAVIGATION}")
    implementation("androidx.navigation:navigation-ui-ktx:${Version.NAVIGATION}")

    implementation("androidx.lifecycle:lifecycle-common-java8:${Version.LIFECYCLE}")
}

fun DependencyHandlerScope.network() {
    implementation("com.squareup.retrofit2:retrofit:${Version.RETROFIT}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Version.OK_HTTP}")

    implementation("com.google.code.gson:gson:${Version.GSON}")
    implementation("com.squareup.retrofit2:converter-gson:${Version.RETROFIT}")

    implementation("com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Version.RETROFIT_RX_ADAPTER}")
}

fun DependencyHandlerScope.rxJava() {
    implementation("io.reactivex.rxjava2:rxjava:${Version.RX_JAVA}:")
    implementation("io.reactivex.rxjava2:rxandroid:${Version.RX_ANDROID}")
    implementation("io.reactivex.rxjava2:rxkotlin:${Version.RX_KOTLIN}")
}

fun DependencyHandlerScope.dependencyInjection() {
    implementation("com.google.dagger:dagger:${Version.DAGGER}")
    kapt("com.google.dagger:dagger-compiler:${Version.DAGGER}")
}

fun DependencyHandlerScope.database() {
    implementation("androidx.room:room-ktx:${Version.ROOM}")
    kapt("androidx.room:room-compiler:${Version.ROOM}")
    androidTestImplementation("androidx.room:room-testing:${Version.ROOM}")
}

fun DependencyHandlerScope.misc() {
    debugImplementation("com.wajahatkarim3:roomexplorer:0.0.2")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:${Version.LEAK_CANARY}")
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
