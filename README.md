# Instagram clone

An Instagram clone for Android, written in Kotlin with XML layouts. I built it for my Instagram series "Building Instagram from scratch" on [@itchallenges](https://www.instagram.com/itchallenges/).

The app has five tabs:

- **Home**: the stories row and a feed of posts. Double tap a photo to like it.
- **Reels**: full screen videos on a vertical pager, played with Media3 ExoPlayer.
- **Messages**: the notes row and an inbox with unread conversations.
- **Explore**: a search field over a grid of photos.
- **Profile**: a header with posts, followers and following, the story highlights and a post grid with grid, reels and tagged tabs.

All the content is hardcoded in `FakeData.kt` and bundled with the app. There is no backend, no login and no network access.

## Open and run

1. Open the project folder in Android Studio with **File > Open**. It was built with Android Studio 2026.2.2, so use that version or a newer one.
2. Let the first Gradle sync finish. It downloads Gradle 9.6.1 and the libraries. The app compiles against Android API 37, so install that SDK platform if Android Studio asks for it.
3. Pick an emulator, or a phone with USB debugging turned on, and press **Run**.

The app runs on Android 7.0 (API 24) and newer.

To build from a terminal, set `JAVA_HOME` to the JDK that comes with Android Studio (its `jbr` folder) and `ANDROID_HOME` to your Android SDK, then run `./gradlew assembleDebug`, or `gradlew.bat assembleDebug` on Windows. The APK ends up in `app/build/outputs/apk/debug/`.

## Credits

- **Photos**: [Lorem Picsum](https://picsum.photos), which serves photos by [Unsplash](https://unsplash.com) photographers under the [Unsplash License](https://unsplash.com/license). They are the avatars, posts, explore grid and highlight covers, and the four photos that reels 5 to 8 slowly zoom across.
- **Videos**: reels 1 to 4 and the itchallenges logo are from [@itchallenges](https://www.instagram.com/itchallenges/).
- **Icons**: [Material Symbols](https://fonts.google.com/icons) by Google, under the Apache License 2.0, converted to Android vector drawables. The new message icon was drawn to match them.
- **Font**: [Grand Hotel](https://fonts.google.com/specimen/Grand+Hotel) by Astigmatic, under the SIL Open Font License 1.1, used for the wordmark.

## License

The code is under the [MIT License](LICENSE). The photos, videos, logo, icons and font keep their own terms, and the notices their licenses require are in [NOTICE](NOTICE).

This project is not affiliated with, endorsed by or connected to Instagram or Meta. Instagram is a trademark of Meta Platforms, Inc.
