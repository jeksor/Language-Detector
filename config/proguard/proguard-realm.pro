# Proguard Configuration for Realm (http://realm.io)
# For detailed discussion see: https://groups.google.com/forum/#!topic/realm-java/umqKCc50JGU
-keep class io.realm.annotations.RealmModule
-keep @io.realm.annotations.RealmModule class *
-keep class io.realm.internal.Keep
-keep @io.realm.internal.Keep class *
-dontwarn javax.**
-dontwarn io.realm.**

#Shut up
-dontnote javax.**
-dontnote io.realm.**

# TODO Additionally you need to keep your Realm Model classes as well
-keep class com.esorokin.lantector.model.data.** { *; }
