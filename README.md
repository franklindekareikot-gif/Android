# Android 实验

课程实验代码仓库，包含实验一与实验二。

## 实验一 Android 开发基础（main 分支）

1. 安装 Android Studio 与 Git 工具；
2. 创建第一个 Android 工程；
3. 使用 Android 官方忽略规则（`.gitignore`）忽略编译产物与本地配置；
4. 将工程提交并推送到 GitHub。

## 实验二 Android 界面布局（lab2-layout 分支）

用 5 种方式完成界面布局，App 首页是各实验的入口菜单：

| 目录 | 实现 | 说明 |
| --- | --- | --- |
| 1. 线性布局 | `LinearLayout` + `layout_weight` | 4×4 单元格，每行按不同权重分配宽度 |
| 2. 表格布局 | `TableLayout` + `TableRow` | 菜单表格，右侧显示快捷键列 |
| 3. 约束布局 1 | `ConstraintLayout` 链式约束 | 计算器界面，4×4 按钮平均分布 |
| 4. 约束布局 2 | `ConstraintLayout` | 订票界面：标签栏、出发地/目的地、DEPART 按钮 |
| 5. Compose | `LazyColumn` + `remember` 状态 | 课程学习任务列表：添加、勾选（删除线）、删除、已完成计数 |

说明：实验 4 的插图为课程群文件提供的图片资源，此处用矢量图 `ic_planet.xml` 代替。

## 开发环境

| 项目 | 版本 |
| --- | --- |
| Android Studio | 2026.1.4（Quail 4） |
| Android Gradle Plugin | 8.12.2 |
| Gradle | 8.13 |
| Kotlin | 2.0.21 |
| Compose BOM | 2024.09.00 |
| JDK | 21 |
| compileSdk / targetSdk | 37 |
| minSdk | 24 |
| 语言 | Java + Kotlin（Compose 界面） |

## 运行方式

1. 用 Android Studio 打开仓库根目录，等待 Gradle 同步完成；
2. 选择模拟器或真机，运行 `app` 模块。

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
│       ├── java/com/example/androidlab1
│       │   ├── MainActivity.java            入口菜单
│       │   ├── LinearLayoutActivity.java
│       │   ├── TableLayoutActivity.java
│       │   ├── Constraint1Activity.java
│       │   ├── Constraint2Activity.java
│       │   └── ComposeActivity.kt
│       └── res
│           ├── layout/                      各实验的布局文件
│           ├── drawable/                    边框、色块、矢量图标
│           └── values/                      字符串、主题、样式
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── gradle/wrapper
```
