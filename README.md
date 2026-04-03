# Shoppe Android App

Shoppe 是一个基于 Jetpack Compose 开发的电商应用，采用 Material Design 3 设计规范。

## 项目信息

- **包名**: com.shoppe.android
- **最低 SDK**: 24 (Android 7.0)
- **目标 SDK**: 35 (Android 15)
- **Kotlin**: 2.0.21
- **Jetpack Compose**: BOM 2024.09.00

## 技术栈

### 核心库
- **UI 框架**: Jetpack Compose
- **Material Design**: Material3
- **导航**: Navigation Compose 2.8.4
- **图片加载**: Coil 2.7.0

### 架构
- 单 Activity 架构
- Jetpack Navigation 进行页面导航
- Composable 函数构建 UI

## 项目结构

```
app/
├── src/main/
│   ├── java/com/shoppe/android/
│   │   ├── MainActivity.kt              # 主 Activity
│   │   ├── navigation/
│   │   │   └── Screen.kt                # 导航路由定义
│   │   ├── screens/
│   │   │   ├── StartPage.kt             # 启动页
│   │   │   ├── CreateAccountPage.kt     # 注册页
│   │   │   ├── LoginScreen.kt           # 登录页
│   │   │   └── PasswordPage.kt          # 密码输入页
│   │   ├── ui/
│   │   │   ├── components/
│   │   │   │   ├── StatusBar.kt         # 状态栏组件
│   │   │   │   └── CommonComponents.kt  # 通用组件
│   │   │   └── theme/
│   │   │       ├── Color.kt             # 颜色定义
│   │   │       ├── Type.kt              # 排版定义
│   │   │       └── Theme.kt             # 主题配置
│   │   └── res/                         # 资源文件
│   └── AndroidManifest.xml
├── src/test/                            # 单元测试
└── src/androidTest/                     # 仪器测试
```

## 已实现页面

| 页面 | 说明 | Figma Node |
|------|------|------------|
| StartPage | 启动页 | 0:12855 |
| CreateAccountPage | 注册页 | 0:12779 |
| LoginScreen | 登录页（邮箱验证） | 0:12718 |
| PasswordPage | 密码输入页 | 0:12649 |

## 快速开始

### 环境要求
- Android Studio Hedgehog (2023.1.1) 或更高版本
- JDK 11+
- Android SDK 35

### 构建步骤

1. 克隆或下载项目
2. 使用 Android Studio 打开项目
3. 等待 Gradle 同步完成
4. 运行 `./gradlew build` 构建项目
5. 点击 Run 按钮部署到模拟器或真机

### 配置 SDK 路径

如果编译时提示找不到 SDK，请修改 `local.properties` 文件：

```properties
sdk.dir=你的 SDK 路径
```

## 运行测试

### 单元测试
```bash
./gradlew test
```

### 仪器测试
```bash
./gradlew connectedAndroidTest
```

## Figma 设计稿

项目基于 Figma 设计稿进行开发：
- **File Key**: HPtpuBt4RrvXdzDLy4vUN1
- **设计系统**: Shoppe Design System

## 页面流转

```
StartPage (启动页)
├── Let's get started → CreateAccountPage (注册页)
│                        └── Done → LoginScreen (登录页)
│
└── I already have an account → LoginScreen (登录页)
                                 └── Next → PasswordPage (密码页)
                                              └── 登录成功 → 商城首页
```

## 设计规范

### 颜色
- **Primary**: #1a73e8 (蓝色)
- **Primary Dark**: #1557b0 (深蓝)
- **Accent**: #ff6d00 (橙色)
- **Background**: #fafafa (浅灰)
- **Surface**: #ffffff (白色)
- **Error**: #d32f2f (红色)

### 字体
- 主要字体：Nunito Sans
- 标题字体：Raleway
- 正文字体：Poppins

### 圆角
- 按钮：16dp
- 输入框：60dp (胶囊形)
- 卡片：8dp

## 下一步计划

1. 完善密码找回流程页面
2. 实现引导轮播页 (Onboarding)
3. 实现商城首页
4. 实现商品详情页
5. 实现购物车功能
6. 实现个人中心

## 许可证

MIT License
