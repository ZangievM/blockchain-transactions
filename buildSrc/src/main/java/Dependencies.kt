object Deps {

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${PluginVersion.KOTLIN_VERSION}"

    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Version.CORE_KTX_VERSION}"

    // Fragments
    const val ANDROIDX_FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Version.FRAGMENT_KTX_VERSION}"

    // AppCompat
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.APPCOMPAT_VERSION}"

    // Material
    const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL_VERSION}"

    // ConstraintLayout
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT_VERSION}"

    // RecyclerView
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Version.RECYCLER_VIEW_VERSION}"

    // ViewPager2
    const val VIEWPAGER2 = "androidx.viewpager2:viewpager2:${Version.VIEWPAGER2_VERSION}"

    // SwipeRefreshLayout
    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Version.SWIPE_REFRESH_LAYOUT_VERSION}"



    // ViewModel
    const val LIFECYCLE_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE_VERSION}"
    // LiveData
    const val LIFECYCLE_LIVEDATA_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Version.LIFECYCLE_VERSION}"

    // ProcessLifecycleOwner provides a lifecycle for the whole application process
    const val LIFECYCLE_PROCESS =
        "androidx.lifecycle:lifecycle-process:${Version.LIFECYCLE_VERSION}"

    // Navigation Components
    const val NAVIGATION_FRAGMENT =
        "androidx.navigation:navigation-fragment-ktx:${Version.NAVIGATION_VERSION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Version.NAVIGATION_VERSION}"
    const val NAVIGATION_RUNTIME =
        "androidx.navigation:navigation-runtime-ktx:${Version.NAVIGATION_VERSION}"

    // Dynamic Feature Module Support
    const val NAVIGATION_DYNAMIC =
        "androidx.navigation:navigation-dynamic-features-fragment:${Version.NAVIGATION_VERSION}"

    // Dagger Core dependencies
    const val DAGGER = "com.google.dagger:dagger:${Version.MATERIAL_VERSION}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Version.DAGGER_VERSION}"
    const val DAGGER_ANNOTATION_PROCESSOR =
        "com.google.dagger:dagger-android-processor:${Version.DAGGER_VERSION}"

    // Dagger Hilt
    const val DAGGER_HILT_ANDROID = "com.google.dagger:hilt-android:${Version.DAGGER_HILT_VERSION}"
    const val DAGGER_HILT_COMPILER =
        "com.google.dagger:hilt-android-compiler:${Version.DAGGER_HILT_VERSION}"

    // Dagger Hilt AndroidX
    const val DAGGER_HILT_VIEWMODEL =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Version.DAGGER_HILT_ANDRIODX}"
    const val DAGGER_HILT_ANDROIDX_HILT_COMPILER =
        "androidx.hilt:hilt-compiler:${Version.DAGGER_HILT_ANDRIODX}"
    const val JAVA_X_INJECT = "javax.inject:javax.inject:${Version.JAVA_X_INJECT_VERSION}"
    const val JAVA_X_ANNOTATION = "javax.annotation:jsr250-api:${Version.JAVA_X_ANNOTATION_VERSION}"

    // Coroutines
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES_VERSION}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES_VERSION}"

    // Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT_VERSION}"
    const val RETROFIT_GSON_CONVERTER =
        "com.squareup.retrofit2:converter-gson:${Version.RETROFIT_VERSION}"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Version.OKHTTP_LOGGING_VERSION}"

    // Gson
    const val GSON = "com.google.code.gson:gson:${Version.GSON_VERSION}"

    // glide
    const val GLIDE = "com.github.bumptech.glide:glide:${Version.GLIDE_VERSION}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Version.GLIDE_VERSION}"


    const val ENCRYPTED_SHARED_PREF = "androidx.security:security-crypto:${Version.ENCRYPTED_SHARED_PREF_VERSION}"

    const val LEAK_CANARY =
        "com.squareup.leakcanary:leakcanary-android:${Version.LEAK_CANARY_VERSION}"
}

object TestDeps {
    const val junit = "junit:junit:${TestVersions.junit}"
    const val androidx_test_ext_junit = "androidx.test.ext:junit:${TestVersions.androidx_test_ext_junit}"
    const val androidx_test_espresso = "androidx.test.espresso:espresso-core:${TestVersions.androidx_test_espresso}"
}

