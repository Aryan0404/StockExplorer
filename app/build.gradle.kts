    plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.jetbrains.kotlin.android)
    }

    android {
        namespace = "com.example.stockexplorer"
        compileSdk = 35

        defaultConfig {
            applicationId = "com.example.stockexplorer"
            minSdk = 24
            targetSdk = 35
            versionCode = 1
            versionName = "1.0"
            buildConfigField(
                "String",
                "ALPHA_VANTAGE_API_KEY",
                "\"${project.properties["ALPHA_VANTAGE_API_KEY"]}\""
            )

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }


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
        buildFeatures {
            compose = true
            buildConfig = true // ✅ This enables BuildConfig generation
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.1"
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }

    dependencies {
        // Retrofit core library
        implementation("androidx.compose.material:material-icons-extended")
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        // Kotlin coroutines support (optional, but needed for suspend functions)
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
        implementation("androidx.compose.material:material-icons-extended")
        implementation("androidx.navigation:navigation-compose:2.7.7")
        implementation("com.github.PhilJay:MPAndroidChart:v3.1.0") // Or the latest version
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)

        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.ui)                   // usually maps to androidx.compose.ui:ui
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.material3)
    // Paging
        implementation("androidx.paging:paging-runtime:3.2.1")
        implementation("androidx.paging:paging-compose:3.2.1")
        // Add this line for ViewModel + Compose integration
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
    }
