package com.viewflipper_viewpager_fragment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LinePagerIndicatorDecoration extends RecyclerView.ItemDecoration {
    private final int colorActive;
    private final int colorInactive;

    // Kích thước của indicator
    private static final int INDICATOR_HEIGHT = 16;
    private static final int INDICATOR_RADIUS = 6;
    private static final int INDICATOR_PADDING = 16;

    private final Paint paint;

    public LinePagerIndicatorDecoration() {
        // Khởi tạo Paint
        paint = new Paint();
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.FILL);

        // Màu sắc
        colorActive = 0xFF000000;    // Màu đen
        colorInactive = 0x33000000;  // Màu xám nhạt
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int itemCount = parent.getAdapter().getItemCount();
        if (itemCount == 0) return;

        int indicatorStartX = (parent.getWidth() - (INDICATOR_RADIUS * 2 * itemCount + INDICATOR_PADDING * (itemCount - 1))) / 2;
        int indicatorPosY = parent.getHeight() - INDICATOR_HEIGHT;

        drawInactiveIndicators(canvas, indicatorStartX, indicatorPosY, itemCount);
        drawActiveIndicator(canvas, indicatorStartX, indicatorPosY, parent);
    }

    // Vẽ các indicator chưa active
    private void drawInactiveIndicators(Canvas canvas, int startX, int posY, int itemCount) {
        paint.setColor(colorInactive);
        float itemWidth = INDICATOR_RADIUS * 2 + INDICATOR_PADDING;
        float start = startX;

        for (int i = 0; i < itemCount; i++) {
            canvas.drawCircle(start, posY, INDICATOR_RADIUS, paint);
            start += itemWidth;
        }
    }

    // Vẽ indicator đang active
    private void drawActiveIndicator(Canvas canvas, int startX, int posY, RecyclerView parent) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int activePosition = layoutManager.findFirstVisibleItemPosition();
        if (activePosition == RecyclerView.NO_POSITION) return;

        View activeChild = layoutManager.findViewByPosition(activePosition);
        if (activeChild == null) return;

        int itemWidth = INDICATOR_RADIUS * 2 + INDICATOR_PADDING;
        float progress = (float) -activeChild.getLeft() / activeChild.getWidth();

        paint.setColor(colorActive);
        float highlightStart = startX + itemWidth * (activePosition + progress);
        canvas.drawCircle(highlightStart, posY, INDICATOR_RADIUS, paint);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = INDICATOR_HEIGHT;
    }
}
