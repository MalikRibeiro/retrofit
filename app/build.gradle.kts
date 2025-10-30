plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.myapplication"
    // CORREÇÃO: Atualizado de 33 para 34, exigido pelas novas bibliotecas
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        // CORREÇÃO: Atualizado de 33 para 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    }
    composeOptions {
        // Atualizado para uma versão mais recente compatível com o Kotlin 1.8.10
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // --- CORREÇÕES DE VERSÃO ---
    // Atualizado para uma versão estável mais recente
    implementation("androidx.core:core-ktx:1.13.1")

    // ATUALIZADO o BOM (Bill of Materials) do Compose para uma versão recente.
    // Isso ajuda a gerenciar as versões de bibliotecas do Compose automaticamente.
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))

    // REMOVIDA a versão manual. O BOM (acima) cuidará disso.
    implementation("androidx.activity:activity-compose")

    // Dependências do Compose (sem versão, pois o BOM gerencia)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // --- CORREÇÃO DE CONFLITO LIFECYCLE ---
    // Você tinha duas versões diferentes de lifecycle-runtime-ktx (2.9.4 e 2.6.1).
    // Unifiquei todas as dependências do Lifecycle para a versão estável 2.7.0.
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // O BOM do compose já deve fornecer uma versão para runtime-livedata
    implementation("androidx.compose.runtime:runtime-livedata")

    // --- REMOVIDA DEPENDÊNCIA CONFLITANTE ---
    // Você estava importando o Material (M2) e o Material3 (M3).
    // Removi o Material 2 para evitar conflitos. Foque no Material3.
    // implementation("androidx.compose.material:material:1.6.0") // <-- REMOVIDA

    // Dependências do Retrofit (estavam corretas)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Dependências de Teste (com versões atualizadas e alinhadas)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1") // Atualizado de 1.3.0
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1") // Atualizado de 3.7.0

    // Alinhado o BOM de teste com o BOM principal
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

