package com.esorokin.lantector.presentation.navigation.animation;

import com.art.alligator.AnimationData;
import com.art.alligator.Screen;
import com.art.alligator.TransitionAnimation;
import com.art.alligator.TransitionAnimationProvider;
import com.art.alligator.TransitionType;
import com.art.alligator.animations.transition.SimpleTransitionAnimation;
import com.esorokin.lantector.R;

public final class DefaultAnimationProvider implements TransitionAnimationProvider {
	@Override
	public TransitionAnimation getAnimation(TransitionType transitionType,
	                                        Class<? extends Screen> screenClassFrom,
	                                        Class<? extends Screen> screenClassTo,
	                                        boolean isActivity,
	                                        AnimationData animationData) {
		if (isActivity) {
			return TransitionAnimation.DEFAULT;
		} else {
			return new SimpleTransitionAnimation(R.anim.stay, R.anim.fade_out);
		}
	}
}
