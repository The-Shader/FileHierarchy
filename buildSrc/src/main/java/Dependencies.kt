object Versions {

    // Release info
    const val minSdk = 26
    const val targetSdk = 33
    const val compileSdk = 33

    // Build tools and languages
    const val kotlin = "1.7.10"

    // Coroutines
    const val coroutines = "1.6.4"

    const val kotlinJvmTarget = "1.8"

    // Androidx Libraries
    const val annotations = "1.2.0"
    const val lifecycle = "2.4.1"

    // Networking
    const val retrofit = "2.9.0"
    const val okHttp = "4.10.0"
    const val kotlinJson = "1.3.2"
    const val kotlinJsonConverter = "0.8.0"

    // Arrow
    const val arrowCore = "1.1.2"
    const val arrowCoreRetrofit= "1.1.4-rc.2"

    const val koin = "3.2.2"
    const val koinAndroid = "3.3.0"
    const val glide = "4.13.1"
    const val glideCompose = "1.0.0-alpha.1"

    // Compose
    const val compose = "1.2.0"
    const val composeActivity = "1.5.0"
    const val constraintLayoutCompose = "1.0.1"
    const val composeMaterialTheme = "1.2.1"
    const val composeNavigation = "2.5.3"

    // Testing
    const val coroutinesTest = "1.6.0"
    const val turbine = "0.8.0"
    const val mockk = "1.12.2"
    const val junit = "4.13.2"
}

object Libraries {

    // Build tools and languages
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    // Support Libraries
    const val lifecycleViewModelExtensions = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    // Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitKotlinJsonConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.kotlinJsonConverter}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val kotlinJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinJson}"

    // Arrow
    const val arrowCore = "io.arrow-kt:arrow-core:${Versions.arrowCore}"
    const val arrowCoreRetrofit = "io.arrow-kt:arrow-core-retrofit:${Versions.arrowCoreRetrofit}"

    const val koin = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinAndroid}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koinAndroid}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotations = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // Compose
    const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeUiUtil = "androidx.compose.ui:ui-util:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val constraintLayoutCompose =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayoutCompose}"
    const val composeMaterialTheme = "com.google.android.material:compose-theme-adapter:${Versions.composeMaterialTheme}"

    // Glide
    const val glideCompose = "com.github.bumptech.glide:compose:${Versions.glideCompose}"

    // Accompanist
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"

    // Testing
    const val kotlinJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
    const val kotlinCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val junit = "junit:junit:${Versions.junit}"
}