package com.mohsin.baseapp.UI.CustomComponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;

import com.mohsin.baseapp.R;


public class TextView_N extends AppCompatTextView {

    Boolean isSpannable= false;
    TypedArray ta;


    public TextView_N(Context context) {
        super(context);

    }

    public TextView_N(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        ta= context.obtainStyledAttributes(attrs, R.styleable.TextView);
        init(context,ta);
    }

    public TextView_N(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ta= context.obtainStyledAttributes(attrs, R.styleable.TextView);
        init(context,ta);
    }

    private void init(Context context, TypedArray taButton) {
       // setTextSize(20);


        if (ta != null) {

            Log.i("ButtonCheck", "init: ");
            int text_color  = ta.getInteger(R.styleable.TextView_text_color, 0);

            if(text_color==0) {
                setTextColor(getResources().getColor(R.color.colorblack));

            }
            else if(text_color==1)
            {
                setTextColor(getResources().getColor(R.color.colorPruple));
            }
            else if(text_color==2)
            {
                setTextColor(getResources().getColor(R.color.colorWhite));
            }


        }
        Typeface tf = ResourcesCompat.getFont(context, R.font.gothic);
        setTypeface(tf);

        isSpannable= taButton.getBoolean(R.styleable.TextView_is_spannable, false);
        isSpannable= taButton.getBoolean(R.styleable.TextView_is_spannable, false);




    }



}