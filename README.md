# TouchMouse

---

🕹 It's a cursor that follows the life cycle of an application.



<br/>

## 🚀 Quick Start

### Gradle

Add it in your **root** ```build.gradle``` at the end of repositories:

```groovy
allprojects {
  repositories {
    ...
      maven { url 'https://jitpack.io' }
  }
}
```

And add a this code to your **module**`s ```build.gradle``` file

<br/>

## 👀 Preview



<br/>

## ⚡️ Options

If you want to change the color of the cursor or want to change the cursor to a custom drawble, you can set options.

```kotlin
TouchMouseManager.setOptions(
  TouchMouseOption(
    cursorColor = R.color.my_color,
    cursorDrawable = R.drawable.my_custom_drawable
  )
)
```

It can be initialized anywhere.

```kotlin
TouchMouseManager.clear()
```

<br/>

## 🌟 Find this project useful?

Support it by joining [stargazers](https://github.com/hongbeomi/TouchMouse/stargazers) for this repository

<br/>

## 📝 License

```
Copyright 2020 Hongbeom Ahn

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

			http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```