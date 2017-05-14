package com.esorokin.lantector.ui.plugins.base;

public abstract class DependencyPlugin<T> implements Plugin {
	private final T dependency;

	public DependencyPlugin(final T dependency) {
		this.dependency = dependency;
	}

	public T getDependency() {
		return dependency;
	}
}
