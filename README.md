# Android News App
This is a simple android news app which uses [newsapi.org](https://newsapi.org/) API to get the news and gets back the JSON data.

### Prerequisites

You need to have [Java 8+](%28https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Android Studio](https://developer.android.com/studio) in your system.
Also you need to an api key for this app and you can get it by registering [here](https://newsapi.org/register)

### Installation

Clone the repository.
Open up Android Studio and import project and add the path of the cloned repository.
Open **NetworkUtils.java** and add your api key here:

    final  static  String apiKey =  "YOUR_API_KEY";
Also open **NetworkUnitTest.java** and add your api key here:

    URL url =  new  URL("https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=YOUR_API_KEY");
Build the project and run it. You can find help from [here](https://developer.android.com/studio/run). Also you need android api version 28.

### Testing

Run the test files individually.
1. JSONTest.java
2. Log.java
3. NetworkUnitTest.java

### Deployment

There are two ways to deploy, you can choose either one of them:
1. Create the debug apk and allow your android to install unknown apps or unknown sources from the system settings of your phone.
2. After you build and run. The run window will give you an option to run it on emulator or the phone connected via USB to your computer. Select your android phone and run. **Note: Make sure that the android phone's version is compatible to the build of the project.**
