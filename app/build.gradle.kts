plugins {
    id("com.android.application")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

    // FIRBASE
    id ("com.google.gms.google-services")
}

android {
    namespace = "com.example.monprojet"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.monprojet"
        minSdk = 24
        targetSdk = 34
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("androidx.annotation:annotation:1.7.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("com.google.firebase:firebase-firestore:24.11.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-database:20.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.cardview:cardview:1.0.0")


    // ALL COUNTRIES
    implementation ("com.hbb20:ccp:2.7.3")

    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")
    implementation ("com.google.code.gson:gson:2.10.1")

    // GALLERY ET CAMERA
    implementation ("com.github.Drjacky:ImagePicker:2.3.22")
    implementation ("com.github.dhaval2404:imagepicker:2.1")

    // FIRBASE
    implementation (platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation ("com.google.firebase:firebase-analytics")

    // FIRBASE UI
    implementation ("com.google.firebase:firebase-auth")
    implementation ("com.google.android.gms:play-services-auth:21.0.0")

    // CIRCLE IMAGE VIEW
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // PICASSO
    implementation ("com.squareup.picasso:picasso:2.8")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // MAPS
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.android.libraries.places:places:3.4.0")
    implementation("com.google.maps:google-maps-services:0.15.0")
}