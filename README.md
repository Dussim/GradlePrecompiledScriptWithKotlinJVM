# Gradle precompiled script with kotlin("jvm")

## My recent problems
So going right into details, recently I wanted to learn a bit of gradle and create precompiled script plugin for my kotlin modules to share dependencies, jvmTarget, repositories, plugins etc. 
I tried to add folowing block to script:
```kotlin
plugins {
    kotlin("jvm") version "1.5.0"
}

dependencies {
    kapt(/*some dependency*/)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "13"
}

// other things
```

And I was greeted with following message
```
Plugin requests from precompiled scripts must not include a version number. Please remove the version from the offending request and make sure the module containing the requested plugin 'org.jetbrains.kotlin.jvm' is an implementation dependency of project ':buildSrc'.
```
It is obvious what should be done, isn't it? 
I am total noob with Gradle and it wasn't obvious at all for me. 

## Solution to the problem
I tried many things and googled a lot and I finally figured it out. Maybe this will be useful for anyone else.
``` kotlin
// buildSrc/src/main/kotlin/kotlin-common.gradle.kts
plugins {
    kotlin("jvm")
}
// buildSrc/build.gradle.kts
plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0") // <---- This is our `kotlin("jvm")` plugin
}

repositories {
    mavenCentral()
}
```

In this repository you can find minimal example of such setup with 2 modules having nothing else than 2 stub test, one failing and one passing. Both of these modules in theirs `build.gradle.kts` have only:
```kotlin
plugins {
    `kotlin-common`
}
```
And script setups repositories, testing dependencies and usage of JUnit platform.
