package com.mohsin.pharmapedia.UI.CustomComponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

import com.mohsin.pharmapedia.R;


public class EditText_N extends AppCompatEditText {

    public EditText_N(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray taEditText = context.obtainStyledAttributes(attrs, R.styleable.EditText);
        init(context,taEditText);

    }
    public EditText_N(Context context) {
        super(context);


    }

    public EditText_N(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray taEditText= context.obtainStyledAttributes(attrs, R.styleable.EditText);
        init(context,taEditText);
    }
    private void init(Context context, TypedArray taEditText) {
        Typeface tf = ResourcesCompat.getFont(context, R.font.helvetica_neue);

        boolean backgroundRadius  = taEditText.getBoolean(R.styleable.EditText_background_radius, true) ;

        setMaxLines(1);
        setTypeface(tf);

        if (backgroundRadius) {

            setBackground(getResources().getDrawable(R.drawable.shape_for_edit_text));
            setTextColor(getResources().getColor(R.color.textColorEt));
            setHintTextColor(getResources().getColor(R.color.hintColorEt));

        }
        else {

            setTextColor(getResources().getColor(R.color.textColorEt));
            setHintTextColor(getResources().getColor(R.color.hintColorEt));
          //  setBackground(R.color.mdtp_transparent_black);
        }

        setPadding(30,0,30,0);
        //   measure(0, 0);
     //   setPadding(        ((int)(getMeasuredWidth()*.10)),0,((int)(getMeasuredWidth()*.10)),0);
     //   setHintTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey));

       // setTextColor(ContextCompat.getColor(getContext(),R.color.colorBlack));
    }
}
