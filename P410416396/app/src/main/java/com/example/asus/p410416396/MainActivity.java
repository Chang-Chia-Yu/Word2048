package com.example.asus.p410416396;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.ListPopupWindow.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler h = new Handler();
        h.postDelayed(r,1000);

    }

    Runnable r = new Runnable() {
        @Override
        public void run() {

            myfun();
        }
    };


    float sx,sy;//startX startY

    void myfun(){

        ConstraintLayout myview = (ConstraintLayout) findViewById(R.id.myview);
        myview.setBackgroundColor(0xffffffff);//1026


        LinearLayout li = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);

        li.setOrientation(LinearLayout.VERTICAL);
        //  addContentView(li, params);
        myview.addView(li,params);


        myview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                float ex,ey;//endX  endY
                switch (motionEvent.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        sx= motionEvent.getX();
                        sy = motionEvent.getY();

                        break;

                    case MotionEvent.ACTION_UP:

                        ex = motionEvent.getX() - sx;//減掉一開始的X值
                        ey = motionEvent.getY() - sy;//減掉一開始的Y值


                        if ( Math.abs(ex) > Math.abs(ey) )//X位移比Y位移大的話 代表左右移動 否則為上下
                        {//左右
                            if (ex > 0 )//右
                                mright();
                            else
                                mleft();

                        }else
                        {//上下
                            if ( ey> 0 )
                                mdown();
                            else
                                mup();

                        }


                        break;

                }




                return true;
            }
        });


        int w=myview.getWidth();

        int n = 4;
        nwidth = w/n;

        for (int j = 0; j < n; j++) {

            LinearLayout row = new LinearLayout(getApplicationContext());

            row.setOrientation(LinearLayout.HORIZONTAL);

            for ( int i = 0 ; i < n ; i++ ) {
                Card fl = new Card(getApplicationContext(), nwidth);
                cards[i][j]=fl;
                fl.setNum( 0 );

                row.addView(fl);
            }
            li.addView(row);


        }



        layout = new FrameLayout(getApplicationContext());//1026

        myview.addView(layout);//1026

        //Card obj = new Card(getApplicationContext());

        // initGame();
        addNum();
        addNum();

        addNum();
        addNum();

    }


   void  mright(){

        boolean merge= false;//
        for ( int j = 0 ; j < nrows; j++)// j是一列一列
            for ( int i =  nrows -1 ; i >= 0 ; i -- )//從最右邊檢查
            {
                for ( int ni = i-1; ni >=0 ; ni--)//ni = next i
                {
                    int curri = cards[i][j].getNum();//
                    int checki = cards[ni][j].getNum();//

                    if ( checki > 0)//代表有非空的東西
                    {
                        if (curri ==0 )//代表目前是空的
                        {
                            cards[i][j].setNum(checki);//把非空的值設到目前的位置
                            cards[ni][j].setNum(0);//非空的值設為零

                            animate(ni, i,j,checki , cards[i][j],cards[ni][j]) ;//1026日期
                            //
                            merge = true;
                            i++;//讓下一次可以再做
                            break;

                        }else if (checki == curri)//代表目前不為空且相等
                        {
                            cards[i][j].setNum(checki*2);//加起來
                            cards[ni][j].setNum(0);

                            animate(ni, i,j,checki , cards[i][j],cards[ni][j]) ;//1026
                            merge=true;

                        }else{//checki > 0 && curri !=0
                            break;
                        }



                    }


                }

            }

        if ( merge)
            addNum();

        checkComplete();

        Toast.makeText(getApplicationContext(),"right", Toast.LENGTH_SHORT  ).show();
    }

    void mleft(){
        boolean merge= false;//
        for ( int j = 0 ; j < nrows; j++)// j是一列一列
            for ( int i =  0 ; i <= nrows -1 ; i ++ )//從最左邊檢查
            {
                for ( int ni = i+1; ni <= nrows -1 ; ni++)//ni = next i
                {
                    int curri = cards[i][j].getNum();//
                    int checki = cards[ni][j].getNum();//

                    if ( checki > 0)//代表有非空的東西
                    {
                        if (curri ==0 )//代表目前是空的
                        {
                            cards[i][j].setNum(checki);//把非空的值設到目前的位置
                            cards[ni][j].setNum(0);//非空的值設為零

                            animate(ni, i,j,checki , cards[i][j],cards[ni][j]) ;

                            merge = true;
                            i--;//讓下一次可以再做
                            break;

                        }else if (checki == curri)//代表目前不為空
                        {
                            cards[i][j].setNum(checki*2);//加起來
                            cards[ni][j].setNum(0);

                            animate(ni, i,j,checki , cards[i][j],cards[ni][j]) ;
                            merge=true;

                        }else{//checki > 0 && curri !=0
                            break;
                        }


                    }


                }

            }

        if ( merge)
            addNum();

        checkComplete();

        Toast.makeText(getApplicationContext(),"left", Toast.LENGTH_SHORT  ).show();

    }

    void mdown(){

        boolean merge= false;
        for ( int i = 0 ; i < nrows; i++)//i是一欄一欄
            for ( int j=  nrows -1 ; j >= 0 ; j -- )//從最下面檢查
            {
                for ( int nj = j-1; nj >=0 ; nj--)
                {
                    int curri = cards[i][j].getNum();
                    int checki = cards[i][nj].getNum();

                    if ( checki > 0)
                    {
                        if (curri ==0 )
                        {
                            cards[i][j].setNum(checki);
                            cards[i][nj].setNum(0);

                            animate2(nj, j,i,checki , cards[i][j],cards[j][nj]) ;

                            merge = true;
                            j++;
                            break;

                        }else if (checki == curri)
                        {
                            cards[i][j].setNum(checki*2);
                            cards[i][nj].setNum(0);

                            animate2(nj, j,i,checki , cards[i][j],cards[j][nj]) ;

                            merge=true;
                        }else{//checki > 0 && curri !=0
                            break;
                        }


                    }


                }

            }

        if ( merge)
            addNum();

        checkComplete();

        Toast.makeText(getApplicationContext(),"down", Toast.LENGTH_SHORT  ).show();

    }

    void mup(){

        boolean merge= false;
        for ( int i = 0 ; i < nrows; i++)//i是一欄一欄
            for ( int j= 0  ; j <= nrows -1 ; j ++ )//從最上面檢查
            {
                for ( int nj = j+1; nj <=nrows -1 ; nj ++)
                {
                    int curri = cards[i][j].getNum();
                    int checki = cards[i][nj].getNum();

                    if ( checki > 0)
                    {
                        if (curri ==0 )
                        {
                            cards[i][j].setNum(checki);
                            cards[i][nj].setNum(0);

                            animate2(nj, j,i,checki , cards[i][j],cards[j][nj]) ;

                            merge = true;
                            j++;
                            break;

                        }else if (checki == curri)
                        {
                            cards[i][j].setNum(checki*2);
                            cards[i][nj].setNum(0);

                            animate2(nj, j,i,checki , cards[i][j],cards[j][nj]) ;
                            merge=true;

                        }else{//checki > 0 && curri !=0
                            break;
                        }


                    }


                }

            }

        if ( merge)
            addNum();

        checkComplete();

        Toast.makeText(getApplicationContext(),"up", Toast.LENGTH_SHORT  ).show();

    }

    void checkComplete()
    {
        boolean bend = true;


        for ( int i = 0 ; i < 4 ; i ++)
            for ( int j = 0 ; j<4; j++)
            {
                int curr=cards[i][j].getNum();
                int a =-1;
                if ( i > 0 )
                    a=cards[i-1][j].getNum();
                int b = -1;
                if (i <3)
                    b=cards[i+1][j].getNum();


                int c =-1;
                if ( j>0)
                    c=cards[i][j-1].getNum();


                int d =-1;
                if (j < 3)
                    d=cards[i][j+1].getNum();


                if ( curr==0 || curr == a || curr ==b   || curr== c || curr==d )
                {
                    bend = false;

                    break;

                }

            }


        if ( bend)
        {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("Game Over")
                    .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "restart", Toast.LENGTH_SHORT).show();
                        }
                    })

                    .show();

        }


    }

    void animate(int fromx  , int tox , int row , int num  , final Card ca1, final Card ca2)
    {

        ca1.setVisibility(View.INVISIBLE);
        // ca2.setVisibility(View.INVISIBLE);
        final Card ca = new Card(getApplicationContext(),nwidth);

        ca.setNum(num);


        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(nwidth,nwidth);

        lp.setMargins(fromx*nwidth, row*nwidth,0,0);

        layout.addView(ca,lp);


        final Card cat = new Card(getApplicationContext(),nwidth);

        cat.setNum(0);


        FrameLayout.LayoutParams lpt = new FrameLayout.LayoutParams(nwidth,nwidth);

        lpt.setMargins(tox*nwidth, row*nwidth,0,0);//setMargins(left, top, right, bottom).

        layout.addView(cat,lpt);



        TranslateAnimation ani= new TranslateAnimation(0, nwidth *(tox-fromx),0, 0);


        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                ca1.setVisibility(View.VISIBLE);
                ca.setVisibility(View.INVISIBLE);
                cat.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        ani.setDuration(300);
        ca.startAnimation(ani);

    }
    void animate2(int fromy  , int toy , int column , int num  , final Card ca1, final Card ca2)//上下
    {

        ca1.setVisibility(View.INVISIBLE);
        // ca2.setVisibility(View.INVISIBLE);
        final Card ca = new Card(getApplicationContext(),nwidth);

        ca.setNum(num);


        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(nwidth,nwidth);

        lp.setMargins(column*nwidth, fromy*nwidth,0,0);

        layout.addView(ca,lp);


        final Card cat = new Card(getApplicationContext(),nwidth);

        cat.setNum(0);


        FrameLayout.LayoutParams lpt = new FrameLayout.LayoutParams(nwidth,nwidth);

        lpt.setMargins(column*nwidth, toy*nwidth,0,0);//setMargins(left, top, right, bottom).

        layout.addView(cat,lpt);



        TranslateAnimation ani= new TranslateAnimation(0, 0 ,0, nwidth *(toy-fromy));
        //TranslateAnimation(float x1,  float x2,  float y1,  float y2)
        //(x1,y1)移動到(x2,y2)

        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                ca1.setVisibility(View.VISIBLE);
                ca.setVisibility(View.INVISIBLE);
                cat.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        ani.setDuration(300);
        ca.startAnimation(ani);

    }
    int nwidth;
    int nrows= 4;
    FrameLayout layout;
    Card [][] cards =new Card[nrows][nrows];

    ArrayList<Point>  emptyList= new ArrayList<Point>();

    void initGame()
    {
        for ( int j = 0 ; j < nrows; j++)
            for ( int i = 0 ; i < nrows; i++)
            {
                //emptyList.add(new Point(i,j));
                cards[i][j].setNum(0);//1026

            }

        addNum();//1026
        addNum();//1026


    }


    void addNum()
    {
        emptyList.clear();

        for ( int j = 0 ; j < nrows; j++)
            for ( int i = 0 ; i < nrows; i++)
            {

                int v=cards[i][j].getNum();
                if (v==0)
                    emptyList.add(new Point(i,j));

            }
        if ( emptyList.size()>0)
        {
            int r=(int)( Math.random()*emptyList.size());
            Point p=emptyList.remove(r);

            cards[p.x][ p.y].setNum(2);



        }


    }


}