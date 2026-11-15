# 实验一 Android 开发基础

Android 开发基础实验：创建第一个 Android 工程，并将工程同步到 GitHub。

## 实验内容

1. 安装 Android Studio 与 Git 工具；
2. 创建第一个 Android 工程（本仓库）；
3. 使用 Android 官方 [Android.gitignore](https://github.com/github/gitignore/blob/master/Android.gitignore) 忽略编译产物与本地配置；
4. 将工程提交并推送到 GitHub 仓库。

## 工程说明

- 包名：`com.example.androidlab1`
- 界面：`activity_main.xml`，包含一个 `TextView` 与一个 `Button`，点击按钮后更新文字并弹出 `Toast`，用于验证工程可以正常编译运行。

## 开发环境

| 项目 | 版本 |
| --- | --- |
| Android Studio | 2026.1.4（Quail 4） |
| Android Gradle Plugin | 8.12.2 |
| Gradle | 8.13 |
| JDK | 21 |
| compileSdk / targetSdk | 37 |
| minSdk | 24 |
| 语言 | Java |

## 运行方式

1. 用 Android Studio 打开本仓库根目录，等待 Gradle 同步完成；
2. 选择模拟器或真机，点击 Run 运行 `app` 模块。

命令行编译：

```bash
./gradlew assembleDebug
```

生成的 APK 位于 `app/build/outputs/apk/debug/app-debug.apk`。

## 目录结构

```
.
├── app
│   ├── build.gradle.kts
│   └── src/main
│       ├── AndroidManifest.xml
│       ├── java/com/example/androidlab1/MainActivity.java
│       └── res
│           ├── layout/activity_main.xml
│           ├── values/strings.xml
│           └── mipmap*/ic_launcher*
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── gradle/wrapper
```
