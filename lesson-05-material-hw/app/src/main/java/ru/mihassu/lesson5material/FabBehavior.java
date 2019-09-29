package ru.mihassu.lesson5material;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class FabBehavior extends FloatingActionButton.Behavior {

    public FabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull FloatingActionButton child,
                               @NonNull View target, int dxConsumed,
                               int dyConsumed, int dxUnconsumed,
                               int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed, type);

        //при прокрутке fab
        //Движение вниз
        if(dyConsumed > 0) {

            child.animate().rotationY(90f).setInterpolator(new LinearInterpolator()).start();

            //движение вверх
        } else if (dyConsumed < 0) {
            child.animate().rotationY(0f).setInterpolator(new LinearInterpolator()).start();
        }

        //при прокрутке fab сдвигается вниз с анимацией
        //Движение вниз
//        if(dyConsumed > 0) {
//            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
//            int fab_bottomMargin = layoutParams.bottomMargin;
//            //переместить fab в новое положение
//            child.animate().translationX(child.getHeight() + fab_bottomMargin).
//                    translationY(child.getHeight() + fab_bottomMargin)
//                    .setInterpolator(new LinearInterpolator()).start();
//
//            //движение вверх
//        } else if (dyConsumed < 0) {
//            //вернуть fab в старое положение
//            child.animate().translationX(0).translationY(0).
//                    setInterpolator(new LinearInterpolator()).start();
//        }


        //при прокрутке fab просто исчезает
//        if (child.getVisibility() == View.VISIBLE && dyConsumed > 0) {
//            child.setVisibility(View.INVISIBLE);
//        } else if(child.getVisibility() == View.INVISIBLE && dyConsumed < 0) {
//            child.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull FloatingActionButton child,
                                       @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
//        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }


}
