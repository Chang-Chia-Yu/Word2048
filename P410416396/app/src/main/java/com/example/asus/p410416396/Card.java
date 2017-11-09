package com.example.asus.p410416396;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by toreal on 2017/10/12.
 */

public class Card extends FrameLayout {


    public Card(@NonNull Context context, int w) {
        super(context);

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(w - 10, w - 10);
        lp.setMargins(5, 5, 5, 5);

        View view = new View(context);
        view.setBackgroundColor(0x33ff00ff);
        addView(view, lp);

        tv = new TextView(context);
        // tv.setText(Integer.toString(0));
        tv.setTextSize(48);
        tv.setGravity(Gravity.CENTER);
        addView(tv, lp);


    }

    TextView tv;
    int value;
    String item;
    public String getWord() {
        return item;
    }

    public void setWord(String word) {
        item = word;

        if (!item.equals(""))
            tv.setText(item);
        else
            tv.setText("");
        switch(word)
        {
            case "":
                tv.setBackgroundColor(0xffcdc1b4);
                break;

            case "A":
                tv.setBackgroundColor(0xffeee4da);
                tv.setTextColor(0xff000000);

                break;
            case "B":
                tv.setBackgroundColor(0xffede0c8);
                tv.setTextColor(0xff000000);

                break;
            case "C":
                tv.setBackgroundColor(0xfff2b179);
                tv.setTextColor(0xffffffff);

                break;
            case "D":
                tv.setBackgroundColor(0xfff59563);
                tv.setTextColor(0xffffffff);

                break;
            case "E":
                tv.setBackgroundColor(0xfff67c5f);
                tv.setTextColor(0xffffffff);

                break;
            case "F":
                tv.setBackgroundColor(0xfff67c5f);
                tv.setTextColor(0xffffffff);

                break;

        }
    }


    public int getNum()
    {
        return value;
    }
    public void setNum( int num )
    {
        value = num;

      /*  if (num > 0)
            tv.setText(Integer.toString(num));
        else
            tv.setText("");
        */

        switch(num)
        {
            case 0:
                tv.setBackgroundColor(0xffcdc1b4);
                tv.setText("");
                break;

            case 2:
                tv.setBackgroundColor(0xffeee4da);
                tv.setTextColor(0xff000000);
                tv.setText("A");

                break;
            case 4:
                tv.setBackgroundColor(0xffede0c8);
                tv.setTextColor(0xff000000);
                tv.setText("B");
                break;
            case 8:
                tv.setBackgroundColor(0xfff2b179);
                tv.setTextColor(0xffffffff);
                tv.setText("C");
                break;
            case 16:
                tv.setBackgroundColor(0xfff59563);
                tv.setTextColor(0xffffffff);
                tv.setText("D");
                break;
            case 32:
                tv.setBackgroundColor(0xfff67c5f);
                tv.setTextColor(0xffffffff);
                tv.setText("E");
                break;
            case 64:
                tv.setBackgroundColor(0xfff67c5f);
                tv.setTextColor(0xffffffff);
                tv.setText("F");
                break;
            case 128:
                tv.setBackgroundColor(0xfff67c5f);
                tv.setTextColor(0xffffffff);
                tv.setText("G");
                break;
            case 256:
                tv.setBackgroundColor(0xfff67c5f);
                tv.setTextColor(0xffffffff);
                tv.setText("H");
                break;

        }

        //   tv.setTextColor(0xffffffff);

    }
}

