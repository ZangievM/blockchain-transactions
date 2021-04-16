object Plugins {
    // Plugin level
    const val CLASSPATH_KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersion.KOTLIN_VERSION}"
    const val CLASSPATH_GRADLE = "com.android.tools.build:gradle:${PluginVersion.GRADLE_VERSION}"
    const val CLASSPATH_DAGGER_HILT =
        "com.google.dagger:hilt-android-gradle-plugin:${Version.DAGGER_HILT_VERSION}"


    //   Module Level

    const val DAGGER_HILT_PLUGIN = "dagger.hilt.android.plugin"
    const val ANDROID_APPLICATION_PLUGIN = "com.android.application"
    const val ANDROID_DYNAMIC_FEATURE_PLUGIN = "com.android.dynamic-feature"
    const val ANDROID_LIBRARY_PLUGIN = "com.android.library"

    const val KOTLIN_ANDROID_PLUGIN = "kotlin-android"
    const val KOTLIN_ANDROID_EXTENSIONS_PLUGIN = "kotlin-android-extensions"
    const val KOTLIN_KAPT_PLUGIN = "kotlin-kapt"
    const val NAVIGATION_SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
}
