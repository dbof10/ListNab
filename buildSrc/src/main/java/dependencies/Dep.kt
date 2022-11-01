package dependencies

object Dep {

    const val gson = "com.google.code.gson:gson:2.8.6"
    const val lottie = "com.airbnb.android:lottie:3.3.0"


    object Glide {
        const val core = "com.github.bumptech.glide:glide:4.11.0"
        const val compiler = "com.github.bumptech.glide:compiler:4.11.0"
    }


    object GradlePlugin {
        const val android = "com.android.tools.build:gradle:7.2.1"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
        const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2"
        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Kotlin.version}"
    }


    object Test {
        const val junit = "junit:junit:4.12"
        const val testRunner = "androidx.test:runner:1.1.0"
        const val androidJunit4 = "androidx.test.ext.junit:1.1.3"
        const val extJunitKtx = "androidx.test.ext:junit-ktx:1.1.3"
        const val archCore = "androidx.arch.core:core-testing:2.1.0"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
        const val kotlinTestAssertions = "io.kotlintest:kotlintest-assertions:3.1.10"
        const val testingKtx =
            "android.arch.navigation:navigation-testing-ktx:${AndroidX.Navigation.version}"
        const val mockk = "org.mockito.kotlin:mockito-kotlin:4.0.0"
        const val kakao =  "io.github.kakaocup:kakao:3.0.6"

    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.3.0"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.0"
        const val swipe = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
        const val constraint = "androidx.constraintlayout:constraintlayout:2.1.4"
        const val design = "com.google.android.material:material:1.2.0"
        const val coreKtx = "androidx.core:core-ktx:1.5.0"
        const val preference = "androidx.preference:preference:1.1.0"
        const val fragment = "androidx.fragment:fragment:1.4.1"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.4.1"
        const val activity = "androidx.activity:activity-ktx:1.1.0"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.2.0"
        const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata:2.2.0"
        const val sharedPref = "androidx.preference:preference-ktx:1.1.0"


        object Room {
            const val version = "2.4.2"
            const val compiler = "androidx.room:room-compiler:$version"
            const val runtime = "androidx.room:room-runtime:$version"
            const val ktx = "androidx.room:room-ktx:$version"
        }

        object Navigation {
            const val version = "2.4.2"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val runtime = "androidx.navigation:navigation-ui-ktx:$version"
        }

    }

    object Kotlin {
        const val version = "1.6.10"
        const val stdlibCommon = "org.jetbrains.kotlin:kotlin-stdlib-common:$version"
        const val stdlibJvm = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
        const val stdlibJs = "org.jetbrains.kotlin:kotlin-stdlib-js:$version"

        const val coroutinesVersion = "1.6.1"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val androidCoroutinesDispatcher =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        const val coroutinesReactive =
            "org.jetbrains.kotlinx:kotlinx-coroutines-reactive:$coroutinesVersion"
        const val coroutinesPlayServices =
            "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion"
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion"
        const val coroutinesDebug =
            "org.jetbrains.kotlinx:kotlinx-coroutines-debug:$coroutinesVersion"

    }

    object Dagger {

        const val version = "2.44"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val agp = "com.google.dagger:hilt-android-gradle-plugin:2.43.2"
        const val viewmodelCompiler = "androidx.hilt:hilt-compiler:1.0.0"
    }


    object Okhttp {
        const val version = "4.2.2"
        const val client = "com.squareup.okhttp3:okhttp:$version"
        const val log = "com.squareup.okhttp3:logging-interceptor:$version"
        const val ktor = "io.ktor:ktor-client-okhttp:1.3.1"
    }

    object Retrofit {
        const val version = "2.9.0"
        const val core = "com.squareup.retrofit2:retrofit:$version"
        const val gson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object LeakCanary {
        const val version = "2.4"
        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:$version"
        const val leakCanaryPlumber = "com.squareup.leakcanary:plumber-android:$version"
    }

    object Timber {
        const val android = "com.jakewharton.timber:timber:4.7.1"
    }

}
