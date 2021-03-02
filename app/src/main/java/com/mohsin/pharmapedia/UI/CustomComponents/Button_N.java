package com.mohsin.pharmapedia.UI.CustomComponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.res.ResourcesCompat;

import com.mohsin.pharmapedia.R;


public class Button_N extends AppCompatButton {




    public Button_N(Context context) {
        super(context);
        // init();
    }

    public Button_N(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray taButton = context.obtainStyledAttributes(attrs, R.styleable.Button);
        init(taButton,context);
    }

    public Button_N(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray taButton = context.obtainStyledAttributes(attrs, R.styleable.Button);
        init(taButton,context);




    }

    public void setBackgroundMagenta()
    {
        setBackground(getResources().getDrawable(R.drawable.button_magenta));
        setTextColor(Color.WHITE);
    }

    private void init(TypedArray taButton, Context context) {

        setAllCaps(false);
        setTextSize(20);
        if (taButton != null) {

            Log.i("ButtonCheck", "init: ");
            int backColor  = taButton.getInteger(R.styleable.Button_background_color, 0);

            if(backColor == -1) {
                setBackgroundColor(Color.TRANSPARENT);
                setTextColor(getResources().getColorStateList(R.color.textcolor_grey_to_dark_grey));
            }
            else if(backColor == 0) {
                setBackground(getResources().getDrawable(R.drawable.button_magenta));
                setTextColor(Color.WHITE);
            }
            else if(backColor == 1)
            {
                setBackground(getResources().getDrawable(R.drawable.button_cyan));
                setTextColor(Color.WHITE);
            }
            else if(backColor == 2)
            {
                setBackgroundColor(Color.TRANSPARENT);
                setTextColor(getResources().getColorStateList(R.color.textcolor_white_to_dark_grey));
            }
            else if(backColor == 3)
            {
                setBackgroundColor(Color.TRANSPARENT);
                setTextColor(getResources().getColor(R.color.colorCyan));
//               setTextColor(getResources().getColor(R.color.BlueHexCode));
            }
            else if(backColor == 4)
            {
                setBackgroundColor(Color.TRANSPARENT);
                setTextColor(getResources().getColor(R.color.colorGrey));
//               setTextColor(getResources().getColor(R.color.BlueHexCode));
            }
            else if(backColor == 5)
            {
                setBackgroundColor(Color.TRANSPARENT);
                setTextColor(getResources().getColor(R.color.colorBlue));
//               setTextColor(getResources().getColor(R.color.BlueHexCode));
            }

        }


        Typeface tf = ResourcesCompat.getFont(context, R.font.helvetica_neue);

        setTypeface(tf);
    }
}