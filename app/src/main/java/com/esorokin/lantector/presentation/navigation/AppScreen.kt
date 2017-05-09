package com.esorokin.lantector.presentation.navigation

import com.art.alligator.Screen

sealed class AppScreen {
    class Splash : Screen
    class Main : Screen
    class DetectLanguage : Screen
    class History : Screen
}

