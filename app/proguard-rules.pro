-keep class com.google.gson.reflect.TypeToken { *; }

-keep class com.mdf.test.samir.data.datasource.local.entity.** { *; }

-keep class com.mdf.test.samir.data.datasource.remote.response.** { *; }

-keepnames class androidx.navigation.fragment.NavHostFragment
-keep class * extends androidx.fragment.app.Fragment{}

-keepnames class * extends android.os.Parcelable
-keepnames class * extends java.io.Serializable

-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }