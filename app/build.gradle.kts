plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dependency.guard)
    alias(libs.plugins.ksp)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "io.github.u1tramarinet.android13app"
    //noinspection GradleDependency
    compileSdk = 33

    defaultConfig {
        applicationId = "io.github.u1tramarinet.android13app"
        minSdk = 33
        //noinspection OldTargetApi
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("benchmark") {
            initWith(buildTypes.getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        aidl = true
    }
    @Suppress("UnstableApiUsage")
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.car.ui.lib)
    implementation(libs.navigation.compose)
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist.permissions)
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.robolectric)

    implementation(libs.showkase)
    implementation(libs.showkase.annotation)
    ksp(libs.showkase.processor)

    testImplementation(libs.roborazzi)
    testImplementation(libs.roborazzi.compose)
}

dependencyGuard {
    configuration("releaseRuntimeClasspath") {
        tree = true
        modules = true
        allowedFilter = {
            !it.contains("test")
        }
    }
    configuration("debugRuntimeClasspath") {
        tree = true
        allowedFilter = {
            true
        }
    }
}

afterEvaluate {
    tasks.preBuild {
        finalizedBy(tasks.dependencyGuardBaseline)
    }
}

ksp {
    arg("skipPrivatePreviews", "true")
}