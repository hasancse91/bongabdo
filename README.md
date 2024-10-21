# Bongabdo - A Bengali Calendar Converter Library

Bongabdo is a Kotlin library that allows you to convert Gregorian calendar dates to Bengali calendar dates (Bongabdo). This library provides two popular calculation methods: **Bangla Academy** (used in Bangladesh) and **Drik Shiddhanta** (used in India). You can easily integrate this library into your Android or Kotlin Multiplatform project.

<img src="https://raw.githubusercontent.com/hasancse91/bongabdo/main/sample_data/sample-app-screenshot.jpeg" width="280"  />

## Features

- Convert any Gregorian date to Bengali date (Bongabdo).
- Supports both **Bangla Academy (Bangladesh)** and **Drik Shiddhanta (India)** methods. (extendable for your own method)
- Localization support for Bengali and English (easily extendable).

## Getting Started

### 1. Installation

#### Jitpack Integration

To integrate the Bongabdo library in your project, add the following to your root `build.gradle.kts` (or `build.gradle` for Groovy):

```kotlin
allprojects {
    repositories {
        mavenCentral()
        google()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Then add the dependency to your module's build.gradle.kts:
```kotlin
dependencies {
    implementation("com.github.hasancse91:bongabdo:0.0.3")
}
```

### 2. Usage
You can start using the library by creating an instance of the Bongabdo class and calling the appropriate conversion methods.

Example: Convert a Gregorian Date to Bengali Date (Bangla Academy Method)
```kotlin
fun main() {
    val bongabdo = Bongabdo.getInstance(BongabdoMethod.BANGLA_ACADEMY)
    val result = bongabdo.getBongabdoData(year = 2014, month = 3, day = 14) // 14 Apr 2024
    
    println("Bongabdo Date: ${result.getFullDate()}")
}
```

#### License
This project is licensed under the MIT License. See the LICENSE file for details.
