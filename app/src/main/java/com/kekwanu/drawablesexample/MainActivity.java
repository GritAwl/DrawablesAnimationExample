package com.kekwanu.drawablesexample;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
    LinearLayout mLinearLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a LinearLayout in which to add the ImageView
        mLinearLayout = new LinearLayout(this);
        mLinearLayout.setBackground(getResources().getDrawable(R.drawable.solid_background));

        // Instantiate an ImageView and define its properties
        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.bike);
        i.setAdjustViewBounds(true);        // set the ImageView bounds to match the Drawable's dimensions

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        int margins = dp2Px(16);

        lp.setMargins(margins,margins,margins,margins); //l,t,r,b

        i.setLayoutParams(lp);

        // Add the ImageView to the layout and set the layout as the content view
        mLinearLayout.addView(i);
        setContentView(mLinearLayout);


        /**********FEEL FREE TO PLAY AROUND WITH THESE ANIMATIONS BELOW****************/

        /*TextView textView = new TextView(this);
        textView.setTextSize(50);
        textView.setText("BLA BLA BLA");
        mLinearLayout.addView(textView);

        testAnimation(textView);
        //fadeInOut(textView);*/
    }

    public void testAnimation(TextView textView){
        final int RED = 0xffFF8080;
        final int BLUE = 0xff8080FF;

        ValueAnimator colorAnim = ObjectAnimator.ofInt(textView, "textColor", RED, BLUE);
        colorAnim.setDuration(1000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

    }

    public void fadeInOut(final TextView textView){
        Animation animFadeIn, animFadeOut;

        animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        animFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                continueAnim(textView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        textView.startAnimation(animFadeIn);
    }

    public void continueAnim(final TextView textView){
        Animation animFadeOut;

        animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        textView.startAnimation(animFadeOut);
    }


    public int dp2Px(int dp){

        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
