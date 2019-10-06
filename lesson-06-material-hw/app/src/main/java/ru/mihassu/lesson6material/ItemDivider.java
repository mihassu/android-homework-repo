package ru.mihassu.lesson6material;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemDivider extends RecyclerView.ItemDecoration {

    private final Drawable divider;

    public ItemDivider(Context context) {
        int[] attrs = {android.R.attr.listDivider};
        divider = context.obtainStyledAttributes(attrs).getDrawable(0);
    }

    //Рисование разделителей в RecyclerView
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        //Вычисление координат x для всех разделителей
        int left = parent.getPaddingLeft() + 50;
        int right = parent.getWidth() - parent.getPaddingRight();

        //Для каждого элемента, кроме последнего, нарисовать линию
        for (int i = 0; i < parent.getChildCount() - 1; i++) {

            View item = parent.getChildAt(i); //получиьт i-й элемент RecyclerView

            //Вычисление координат у текущего разделителя
            int top = item.getBottom() + ((RecyclerView.LayoutParams)item.getLayoutParams()).bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();

            //Рисование разделителя
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }
}
