package com.github.swiperecyclerlayout;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wzhiqiang on 2018/12/6.
 */
public class SwipeRecyclerView extends RecyclerView {

    private static final String TAG = "CustomRecyclerView";

    public SwipeRecyclerView(@NonNull Context context) {
        super(context);
    }

    public SwipeRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        switch (action){
            // 手指按下的时候，如果有开启的菜单，只要手指不是落在该Item上，落在了其他Item上，则关闭菜单, 并且不分发事件。
            case MotionEvent.ACTION_DOWN:
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                View openItem = findOpenItem();
                if (openItem != null && openItem != getTouchItem(x,y)) {
                    SwipeItemLayout swipeItemLayout = (SwipeItemLayout) openItem;
                    swipeItemLayout.closeMenu();
                    return false;
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                return  false;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return super.onTouchEvent(e);
    }

    /**
     * 获取触碰的Item
     * @param x
     * @param y
     * @return
     */
    private View getTouchItem(int x,int y){
        Rect frame = new Rect();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == VISIBLE) {
                child.getHitRect(frame);
                if (frame.contains(x,y)) {
                    return child;
                }
            }
        }
        return null;
    }

    /**
     * 查找已经打开的menu的Item
     * @return
     */
    private View findOpenItem(){
        int childCount = getChildCount();
        for (int i = 0;i< childCount;i++){
            View child = getChildAt(i);
            if (child instanceof SwipeItemLayout){
                SwipeItemLayout swipeItemLayout = (SwipeItemLayout) child;
                if (swipeItemLayout.isShow()){
                    return getChildAt(i);
                }
            }
        }
        return null;
    }

//    private void initDb2(){
//        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "mt40");
//        Database db = openHelper.getWritableDb();
//        DaoMaster daoMaster = new DaoMaster(db);
//        DaoSession mDaoSessing = daoMaster.newSession();
//    }
}
