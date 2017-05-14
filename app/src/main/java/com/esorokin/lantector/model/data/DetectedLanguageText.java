package com.esorokin.lantector.model.data;

import java.util.Date;

import com.esorokin.lantector.ui.adapter.DisplayableItem;

import io.requery.Entity;
import io.requery.Persistable;

@Entity
public interface DetectedLanguageText extends DisplayableItem, Persistable {
	String getText();
	String getLanguage();
	Date getDate();
}
