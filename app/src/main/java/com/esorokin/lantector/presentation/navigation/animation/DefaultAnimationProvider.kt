package com.esorokin.lantector.presentation.navigation.animation

import com.art.alligator.*
import com.art.alligator.animations.transition.SimpleTransitionAnimation
import com.esorokin.lantector.R

class DefaultAnimationProvider : TransitionAnimationProvider {
    override fun getAnimation(transitionType: TransitionType,
                              screenClassFrom: Class<out Screen>,
                              screenClassTo: Class<out Screen>,
                              isActivity: Boolean,
                              animationData: AnimationData?): TransitionAnimation {
        return SimpleTransitionAnimation(R.anim.fade_in, R.anim.fade_out)
    }
}
