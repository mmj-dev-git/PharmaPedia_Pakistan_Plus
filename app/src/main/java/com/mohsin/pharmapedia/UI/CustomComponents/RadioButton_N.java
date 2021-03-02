package com.mohsin.pharmapedia.UI.CustomComponents;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.content.res.ResourcesCompat;

import com.mohsin.pharmapedia.R;


public class RadioButton_N extends AppCompatRadioButton {


    public RadioButton_N(Context context) {
        super(context);
        init(context);
    }

    public RadioButton_N(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RadioButton_N(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Typeface tf = ResourcesCompat.getFont(context, R.font.helvetica_neue);
        this.setTypeface(tf);
    }
}
