# bayonet
Bayonet provides a framework to selectively inject mock or real class instances. It is meant to work with Android applications using Dagger 2 for dependency injection.

Check out our [blog post](https://medium.com/yodle-tech-blog/android-testing-with-dagger-and-mockito-3a6e93e7c3cd#.2b7swsq02) on Medium to learn more.

## Usage
Our `sample-app` contains Dagger modules for the Robolectric and Espresso contexts which inject most classes as a Mockito mock by default. Simply annotate a Dagger injected field with the `@RealClass` to prevent mock behavior.

We deliberately have not published this project to something like maven-central since it is small enough to be copied. We look forward to adding more features and publishing based on community feedback.

For more information, check out the `sample-app` provided in this repository.

## Building And Running

Bayonet is built using Gradle. Both IntelliJ and Android Studio can import the top-level `build.gradle` file and will automatically generate their project files from it.

You will need to have portions of the Android SDK available in your local Maven artifact repository in order to run the `sample-app`.

To run Unit & Robolectric tests:

    ./gradlew clean test

To run Espresso tests:

    ./gradlew clean connectedAndroidTest
