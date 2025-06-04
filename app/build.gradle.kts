plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.hello"
    compileSdk = 34

    viewBinding {
        enable = true
    }

    // 设置主工程目录；可扩展设置资源目录
//    sourceSets["main"].java.srcDirs(
//        "src/main/java",
//        "../customTools",
//        "../customUtils"
//    )

    defaultConfig {
        applicationId = "com.example.hello"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":feature:product"))
    // 自有的

    // 系统的
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // 三方的
    // RxJava2
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    // RxBinding for EditText
    implementation("com.jakewharton.rxbinding2:rxbinding:2.2.0")

    // kotlin （我这里用的是Kotlin语言）
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation("androidx.appcompat:appcompat:1.2.0")

    // lifecycle
    implementation("androidx.lifecycle:lifecycle-common-java8:2.3.0-alpha01")

    // liveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0-alpha01")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0-alpha01")

    // 协程
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.4")

    implementation(kotlin("script-runtime"))
}