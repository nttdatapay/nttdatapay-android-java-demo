# nttdatapay-android-java-demo

## Introduction 
This is a demo project built for Android to demonstrate the integration of NTTDATA Payment Gateway into an Android application for java.


## Prerequisites
- minSdk (API level 24)
- maxSdk (API level 34)
 
## Installation 
1. Download the ndpsaipaycheckout-release.aar.
2. Copy the .aar file under the libs folder.
3. In build.gradle(Module) add the following implementation

    ```
     implementation(files('libs/ndpsaipaycheckout-release.aar'))
    ```

4. Change the minSDK version to 24 in build.gradle(Module:app)
5. Add the below user permission in the manifest file.
     `AndroidManifest.xml`:
     ```xml
     <uses-permission android:name="android.permission.INTERNET" />

6. Refer to the MainActivity.java file. This includes all merchant level settings and response handling.

## Configuration
1. Change the details from MainActivity.java file
2. Update merchantId, password, prodid, keys etc.
3. For MultiProduct transactions 
    - set prodid to Multi
        ```
        newPayIntent.putExtra("prodid", "Multi");
        ```

    - set multi_products to createMultiProductData()
        ```
        newPayIntent.putExtra("multi_products", createMultiProductData())
        ```
    - Set your products details in createMultiProductData()

4.  To show any specific payment mode 
    - set subChannel to 'NB' or 'UP' or 'UI' or 'DC' or 'CC' or 'NR' etc.
        ```
        newPayIntent.putExtra("subChannel", "UP")
        ```

