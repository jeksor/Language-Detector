package com.esorokin.lantector.utils.ext

import android.app.Activity
import android.support.v4.app.Fragment

import com.art.alligator.Screen
import com.art.alligator.ScreenResolver
import com.art.alligator.navigationfactories.RegistryNavigationFactory
import com.esorokin.lantector.di.DependencyManager

private val appScreenResolver: ScreenResolver
    get() = DependencyManager.appComponent.screenResolver()

fun <T : Screen> Activity.getScreen(screen: Class<T>): T {
    return appScreenResolver.getScreen(this, screen)
}

fun <T : Screen> Fragment.getScreen(screen: Class<T>): T {
    return appScreenResolver.getScreen(this, screen)
}

inline fun <reified ScreenType, reified Implementation> RegistryNavigationFactory.registerActivity() where ScreenType : Screen, Implementation : Activity {
    registerActivity(ScreenType::class.java, Implementation::class.java)
}

inline fun <reified ScreenType, reified Implementation> RegistryNavigationFactory.registerFragment() where ScreenType : Screen, Implementation : Fragment {
    registerFragment(ScreenType::class.java, Implementation::class.java)
}
