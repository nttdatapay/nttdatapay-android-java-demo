# nttdatapay-android-java-demo

## Introduction 
This is a demo project built with Android to demonstrate the integration of NTTDATA Payment Gateway in a android application for java.


## Prerequisites
- minSdk (API level 24)
- maxSdk (API level 34)
 
## Installation 
1. Download the ndpsaipaycheckout-release.aar.
2. Copy the .aar file under libs folder.
3. In build.gradle(Module) add the following implementation

    ```
     implementation(files('libs/ndpsaipaycheckout-release.aar'))
    ```

4. Change the minSDK version to 24 in build.gradle(Module:app)
5. Add the below user permission in manifest file
    ```xml
     <uses-permission android:name="android.permission.INTERNET">
    ``` 

6. Refere the MainActivity.java file, this includes all merchant level settings and response handling


## Configuration
1. Change the details from MainActivity.java file
2. Update merchantId, password, prodid, keys etc.



