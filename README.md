# AI Art Studio

<!--
  Smart WebView v7
  https://github.com/mgks/Android-SmartWebView

  A modern, open-source WebView wrapper for building advanced hybrid Android apps.
  Native features, modular plugins, and full customisation—built for developers.

  - Documentation: https://docs.mgks.dev/smart-webview  
  - Plugins: https://docs.mgks.dev/smart-webview/plugins  
  - Discussions: https://github.com/mgks/Android-SmartWebView/discussions  
  - Sponsor the Project: https://github.com/sponsors/mgks  

  MIT License — https://opensource.org/licenses/MIT  

  Mentioning Smart WebView in your project helps others find it and keeps the dev loop alive.
-->

<a href="https://github.com/mgks/Android-SmartWebView/">
  <img align="right" src="https://raw.githubusercontent.com/mgks/Android-SmartWebView/master/app/src/main/res/mipmap-xxxhdpi/ic_launcher_foreground.webp" width="175" alt="Smart WebView Logo">
</a>

<p>
  <a href="#features"><img alt="Variant" src="https://img.shields.io/badge/language-Java-red.svg"></a>
  <a href="https://github.com/mgks/Android-SmartWebView/releases"><img alt="GitHub Release" src="https://img.shields.io/github/v/release/mgks/android-smartwebview"></a>
  <a href="https://github.com/mgks/Android-SmartWebView/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/mgks/android-smartwebview"></a>
</p>

**AI Art Studio** is a modern, open-source solution for building advanced hybrid Android apps. It allows you to effortlessly extend your app with plugins, native features, and a customizable UI.

**[DOCUMENTATION](https://docs.mgks.dev/smart-webview/)** | **[GET PREMIUM PLUGINS](https://github.com/sponsors/mgks/sponsorships?sponsor=mgks&tier_id=468838)** | **[ISSUES](https://github.com/mgks/Android-SmartWebView/issues)**

## Core Features

*   **Plugin Architecture:** Extend app functionality with self-registering plugins. See `PluginInterface.java`, `PluginManager.java`, and existing plugins in `/plugins/` for details.
*   **File Uploads & Camera Access:** Support for file selection and direct camera capture in WebView.
*   **Push Notifications:** Integrated Firebase Cloud Messaging (requires `google-services.json`).
*   **Google Analytics:** Built-in support (configure GTAG ID in `swv.properties`).
*   **Custom UI Modes:** Fullscreen and drawer layouts (configurable in `swv.properties`).
*   **Location & Permissions:** Access device GPS/location and manage permissions.
*   **Content Sharing:** Receive and handle shared content from other apps via `ShareActivity.java`.
*   **Downloads & Printing:** Handle file downloads and print web content.
*   **Modern WebView:** Secure, up-to-date, and highly configurable via `swv.properties` and `MainActivity.java`.

## Plugins

AI Art Studio features a plugin system to add new features with minimal effort.

*   **Understanding Plugins:**
    *   The core contract is defined in `PluginInterface.java`.
    *   Plugin lifecycle and registration are managed by `PluginManager.java`.
    *   Example plugins (`ToastPlugin.java`, `LocationPlugin.java`) are located in `/plugins/`. These serve as excellent references for creating new plugins.

[...rest of the README...]