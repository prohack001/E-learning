package com.example.monprojet;


import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageItemDecoration extends RecyclerView.ItemDecoration {
    private final int verticalSpacing;

    public MessageItemDecoration(Context context, int verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = verticalSpacing;
    }
}
