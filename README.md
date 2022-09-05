# Gamely Android Sdk

![Onmobile: Logo](http://t0.gstatic.com/images?q=tbn:ANd9GcQ7a6C5baa2f_3KA2zVpouH29tMGgRfcCn1PGuubySgbFbKuMxg)

# Android SDK Start Guide
Gamely provides a Android library to add Gamifications in your app. This section shows how to set up a Gamely Android library in your app.


## Integrate SDK with App
The Gamely SDK library can be integrated in any Android project by following steps mentioned in the sections below. SDK handles the artifacts remotely and resolves the dependencies at build level in integrating environment. This is JitPack based private artifacts library.



### Add Authentication Token
The authentication token identifies the validity of dependent packages in the integrating environment. 
Add the following line in the gradle.properties file of your Android application to add token:

```groovy
authToken= Add your token here. (Authentication token will be shared offline)
```

### Authenticate Maven Build Signatures
Add it in your root build.gradle at the end of repositories: authToken added in the gradle.propeties

```groovy
allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url https://jitpack.io //this is required to define as a
jitpack//
            credentials { username authToken } ///authToken which is shared in the SDK document//
        }
     }
  }
```
The Android build environment will now validate the authentication token and download the dependencies.

### Import SDK
You can import the SDK file by adding the following dependency in the build.gradle file at the application level.

```groovy
implementation 'path to lib'
```

### Configurations to Support Third Party Libraries
Gamely SDK module has dependencies with the following third-party libraries.

```groovy
implementation 'androidx.core:core-ktx:1.7.0'
implementation 'androidx.appcompat:appcompat:1.4.2'
implementation 'com.google.android.material:material:1.6.1'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0'
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3'
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.3'
implementation 'com.github.bumptech.glide:glide:4.11.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
```
**Note:**

There is no need to add the above dependencies.
In case the project also uses some or any of the above dependencies and libraries then exclude those by adding below lines along with **implementation 'org.bitbucket.onmobile-rbtsdk:latest_version'**
{exclude group: ‘com.x.y’ , module: ‘module X’} 

Example: To exclude 'androidx.appcompat:appcompat:1.2.0'

<sub>(implementation 'org.bitbucket.onmobile-rbtsdk:onmo_dialer:latest_version') 
{exclude group: 'androidx.appcompat', module: ' appcompat'}</sub>


This step is…  | If your app is…
-------------- | -------------
Required       | using a different version of AppCompat library.SDK will use the same version of library in the app.
Not required	|  not using AppCompat library.
Not required	| using same version of AppCompat library. Android OS will keep only one version of library for both app and SDK.


### Initialize SDK
You can do sdk intialisation in application/activity class
```groovy
try {
           val gamelySDKClient = GamelySdkClient.Builder(this)
                .setUserId("user id value") //Mandatory
                .setApiKey("api key value")//Mandatory
                .setLogEnabled(false) //false by default | Optional
                .build()
        } catch (gamelySdkInitialisationException: GamelySdkInitialisationException) {
            //Catch the exception here
            Toast.makeText(this, gamelySdkInitialisationException.message, Toast.LENGTH_SHORT)
                .show()
            //gamelySdkInitialisationException.printStackTrace()
        }
```
Possible Exceptions in case of any failure  | Type
------------- | -------------
Exception                   | GamelySdkInitialisationException


### Get Template
Use following lines to get template

```kotlin
//Use only activity context here
gamelySDKClient?.getTemplate(activityContext, TemplateType, object : IResponseListener {
            override fun onSuccess() {}

            override fun onFailure(message: String) {
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        })
```

TemplateType  ||
------------- | -------------
Template.SPIN_WHEEL
Template.OPINION_POLL


### Get Reward
Use following lines to get reward

```kotlin
//Use only activity context here
 gamelySDKClient?.getReward(this@MainActivity, object : IResponseListener {
                override fun onSuccess() {}

                override fun onFailure(message: String) {
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }

            })
```




See the [`Gamely-android-sdk` project](https://www.onmobile.com/) for more details.



#### Copyright

##### ©2022 OnMobile Global Limited All Rights Reserved.
