package com.github.swiperecyclerlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import java.util.Locale;

/**
 * Created by wzhiqiang on 2018/11/28.
 */

public class SwipeItemLayout extends ViewGroup {

    private static final String TAG = "CustomLayout";
    //Minimum velocity that will be detected as a fling
    private static final int MIN_FLING_VELOCITY = 400; // 用于加速度

    //默认从左边弹出
    public static final int EDGE_DIRECTION_LEFT = 0;
    public static final int EDGE_DIRECTION_RIGHT = 1;

    //默认drawer占父布局的百分比
    public static final float DEFAULT_DRAWER_WIDTH_PERCENT = 60;
    //menu离父容器右边的最小外边距
    private int mMinDrawerMargin;
    //menu显示出来的占自身的百分比,0-100
    private float mMenuFliPercent;
    //menu的总宽度占父布局的百分比
    private float percent;
    //菜单View
    private View mMenuView;
    //内容View
    private View mContentView;
    //自身的宽度
    private  int parentWidth;
    //自身的高度
    private  int parentHeight;
    //内容的宽度
    private int mContentWidth;
    //菜单的宽度
    private int mMenuWidth;
    //侧滑方向
    private int mEdgeDirection;
    //触发移动事件的最小距离
    private int mTouchSlop;
    //是否已经拖动
    private boolean mIsDragged;

    private ViewDragHelper mDragHelper;

    private float mDownX;
    private float mDownY;

    public SwipeItemLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwipeItemLayout);
        percent = typedArray.getFraction(R.styleable.SwipeItemLayout_menuPercent,1,1,DEFAULT_DRAWER_WIDTH_PERCENT);
        mEdgeDirection = typedArray.getInt(R.styleable.SwipeItemLayout_childGravity, EDGE_DIRECTION_RIGHT);
        mEdgeDirection = getAbsoluteGravity(mEdgeDirection);
        mDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback()
        {
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx)
            {
                if (child == mContentView) {
                    //如果是从左边滑出来的，那左顶点左边是从0到mMenuWidth
                    if (isEdgeLeft()) {
                        if (left >= mMenuWidth) {
                            left = mMenuWidth;
                        }else if (left <= 0){
                            return 0;
                        }
                    }else {
                        //如果是从右边滑出来的，那左顶点左边是-mMenuWidth到0
                        if (left > 0){
                            return 0;
                        }else if (left <= -mMenuWidth) {
                            return  -mMenuWidth;
                        }
                    }
                }else if (child == mMenuView) {
                    //如果是从左边滑出来的，那左顶点左边是从-mMenuWidth到0
                    if (isEdgeLeft()){
                        if (left <= -mMenuWidth){
                            return -mMenuWidth;
                        }else if (left >= 0){
                            return 0;
                        }
                    }else {
                        //如果是从左边滑出来的，那左顶点左边是从mContentWidth - mMenuWidth到mContentWidth
                        if (left >= mContentWidth){
                            return mContentWidth;
                        }else if (left <= mContentWidth - mMenuWidth){
                            return mContentWidth - mMenuWidth;
                        }
                    }

                }
                return left;
            }

            @Override
            public boolean tryCaptureView(View child, int pointerId)
            {
                //true代表都子View都可以滑动
                return true;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel)
            {
                //获取移动距离
                int changeRange = 0;
                if (releasedChild == mContentView) {
                    //父布局的移动距离就是-mMenuWidth到mMenuWidth。
                    changeRange = Math.abs(releasedChild.getLeft());
                }else if (releasedChild == mMenuView) {
                    if (isEdgeLeft()) {
                       changeRange = mMenuWidth - Math.abs(releasedChild.getLeft());
                    }else {
                        changeRange = mContentWidth - releasedChild.getLeft();
                    }
                }
                //活动移动距离占mMenu整个宽度的百分比，大于50%才可以画出来
                float offset = changeRange * 1.0f / mMenuWidth;
                if (isEdgeLeft()) {
                    //从左边滑出，xvel速度是大于0的。
                    if (xvel > 0 || xvel == 0 && offset > 0.5f) {
                        mDragHelper.smoothSlideViewTo(mContentView,mMenuWidth,mContentView.getTop());
                        mDragHelper.smoothSlideViewTo(mMenuView,-0,mMenuView.getTop());
                        mMenuFliPercent = 0;
                    }else {
                        mDragHelper.smoothSlideViewTo(mContentView,0,mContentView.getTop());
                        mDragHelper.smoothSlideViewTo(mMenuView,-mMenuWidth,mMenuView.getTop());
                        mMenuFliPercent = 0;
                    }
                }else {
                    //从右边滑出，xvel速度是小于0的。
                    if (xvel < 0 || xvel == 0 && offset > 0.5f) {
                        mDragHelper.smoothSlideViewTo(mContentView, -mMenuWidth, mContentView.getTop());
                        mDragHelper.smoothSlideViewTo(mMenuView,mContentWidth - mMenuWidth,mMenuView.getTop());
                        mMenuFliPercent = 1.0f;
                    }else {
                        mDragHelper.smoothSlideViewTo(mContentView,0,mContentView.getTop());
                        mDragHelper.smoothSlideViewTo(mMenuView,mContentWidth,mMenuView.getTop());
                        mMenuFliPercent = 0;
                    }
                }

                ViewCompat.postInvalidateOnAnimation(SwipeItemLayout.this);
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy)
            {
                //dx和dy是移动距离
                super.onViewPositionChanged(changedView,left,top,dx,dy);
                //如果mContentView改变了，那mMenuView也要同时改变，这样才能一起滑动。如果mMenuView改变了，mContentView也要改变
                if (changedView == mContentView) {
                    mMenuView.offsetLeftAndRight(dx);
                } else if (changedView == mMenuView) {
                    mContentView.offsetLeftAndRight(dx);
                }
                ViewCompat.postInvalidateOnAnimation(SwipeItemLayout.this);
            }

            @Override
            public int getViewHorizontalDragRange(View child)
            {
                //移动距离
                return mMenuWidth;
            }
        });

        //设置edge方向
        mDragHelper.setEdgeTrackingEnabled(mEdgeDirection == 0?ViewDragHelper.EDGE_LEFT:ViewDragHelper.EDGE_RIGHT);
        //设置minVelocity，加速度
        float density = getResources().getDisplayMetrics().density;
        float minVel = MIN_FLING_VELOCITY * density;
        mDragHelper.setMinVelocity(minVel);
        //为了按返回键时有响应。
        setFocusable(true);
        setFocusableInTouchMode(true);
        //记得及时回收TypedArray
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = getChildAt(0);
        mMenuView = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(parentWidth, parentHeight);
        mMinDrawerMargin = (int) (parentWidth*(1-percent));
        MarginLayoutParams lp;
        //计算contentView的宽度
        mContentView = getChildAt(0);
        lp = (MarginLayoutParams) mContentView.getLayoutParams();
        final int contentWidthSpec = MeasureSpec.makeMeasureSpec(
                parentWidth - lp.leftMargin - lp.rightMargin, MeasureSpec.EXACTLY);
        final int contentHeightSpec = MeasureSpec.makeMeasureSpec(
                parentHeight - lp.topMargin - lp.bottomMargin, MeasureSpec.EXACTLY);
        mContentView.measure(contentWidthSpec, contentHeightSpec);

        //计算menuView的宽度
        mMenuView = getChildAt(1);
        lp = (MarginLayoutParams) mMenuView.getLayoutParams();
        final int drawerWidthSpec = getChildMeasureSpec(widthMeasureSpec,
                mMinDrawerMargin + lp.leftMargin + lp.rightMargin, lp.width);
        final int drawerHeightSpec = getChildMeasureSpec(heightMeasureSpec,
                lp.topMargin + lp.bottomMargin, lp.height);
        mMenuView.measure(drawerWidthSpec, drawerHeightSpec);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //onSizeChanged是从onMeasure之后调用的，onSizeChanged之后才是onLayout
        super.onSizeChanged(w, h, oldw, oldh);
        mContentWidth = mContentView.getMeasuredWidth();
        mMenuWidth = mMenuView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        View menuView = mMenuView;
        View contentView = mContentView;

        MarginLayoutParams lp = (MarginLayoutParams) contentView.getLayoutParams();
        contentView.layout(lp.leftMargin, lp.topMargin,
                lp.leftMargin + contentView.getMeasuredWidth(),
                lp.topMargin + contentView.getMeasuredHeight());

        lp = (MarginLayoutParams) menuView.getLayoutParams();
        final int childWidth = menuView.getMeasuredWidth();
        final int width = r - l;
        int childLeft;
        //左顶点的坐标
        if (isEdgeLeft()) {
            childLeft = -childWidth;
        }else {
            childLeft = width;
        }
        menuView.layout(childLeft, lp.topMargin, childLeft + childWidth,
                lp.topMargin + menuView.getMeasuredHeight());

    }



    /**
     * 打开侧滑菜单
     */
    public void openMenu()
    {
        mMenuFliPercent = 1.0f;
        if (isEdgeLeft()){
            mDragHelper.smoothSlideViewTo(mContentView, mMenuWidth, mContentView.getTop());
            mDragHelper.smoothSlideViewTo(mMenuView,0,mMenuView.getTop());
        }else {
            mDragHelper.smoothSlideViewTo(mContentView, -mMenuWidth, mContentView.getTop());
            mDragHelper.smoothSlideViewTo(mMenuView,mContentWidth - mMenuWidth,mMenuView.getTop());
        }
        ViewCompat.postInvalidateOnAnimation(this);

    }

    /**
     * 关闭侧滑菜单
     */
    public void closeMenu()
    {
        if (isEdgeLeft()) {
            mDragHelper.smoothSlideViewTo(mContentView,0,mContentView.getTop());
            mDragHelper.smoothSlideViewTo(mMenuView,-mMenuWidth,mMenuView.getTop());
        }else {
            mDragHelper.smoothSlideViewTo(mContentView,0,mContentView.getTop());
            mDragHelper.smoothSlideViewTo(mMenuView,mContentWidth,mMenuView.getTop());
        }
        mMenuFliPercent = 0;
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private boolean isEdgeLeft(){
        return mEdgeDirection == EDGE_DIRECTION_LEFT;
    }

    /**
     * 根据系统语言获取menu真正应该做哪里滑出来，因为阿拉伯语等国家的方向是不一样的。
     * @param edgeDirection
     * @return
     */
    private int getAbsoluteGravity(int edgeDirection){
        if (isLeftToRight()){
            return edgeDirection;
        }else {
            return edgeDirection == EDGE_DIRECTION_LEFT? EDGE_DIRECTION_RIGHT : EDGE_DIRECTION_LEFT;
        }
    }

    /**
     * 判断界面是默认从左到右，还是从右到左
     * @return
     */
    private boolean isLeftToRight(){
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage().toLowerCase();
        if (language.contains("ar")
                || language.contains("he")//以色列，希伯来文
                || language.contains("iw")//以色列，希伯来文
                || language.contains("ur")//乌尔都,巴基斯坦
                || language.contains("fa")){//波斯语，伊朗
            return false;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (isShow()){
                closeMenu();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 菜单打开的时候，按下Content关闭菜单
            if (isShow() && isTouchContent(((int) ev.getX()), ((int) ev.getY()))) {
                closeMenu();
                return false;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mIsDragged = false;
                mDownX = ev.getX();
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                checkCanDragged(ev);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mIsDragged) {
                    mDragHelper.processTouchEvent(ev);
                    mIsDragged = false;
                }
                break;
            default:
                if (mIsDragged) {
                    mDragHelper.processTouchEvent(ev);
                }
                break;
        }
        return mIsDragged || super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        int action = ev.getActionMasked();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mDownY = ev.getY();
                mIsDragged = false;
                break;
            case MotionEvent.ACTION_MOVE:
                boolean beforeCheckDrag = mIsDragged;
                checkCanDragged(ev);
                if (mIsDragged) {
                    mDragHelper.processTouchEvent(ev);
                }
                // 开始拖动后，发送一个cancel事件用来取消点击效果
                if (!beforeCheckDrag && mIsDragged){
                    MotionEvent obtain = MotionEvent.obtain(ev);
                    obtain.setAction(MotionEvent.ACTION_CANCEL);
                    super.onTouchEvent(obtain);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mIsDragged || isShow()){
                    // 拖拽后手指抬起，或者已经开启菜单，不应该响应到点击事件
                    mDragHelper.processTouchEvent(ev);
                    ev.setAction(MotionEvent.ACTION_CANCEL);
                    mIsDragged = false;
                }
                break;
            default:
                if (mIsDragged){
                    mDragHelper.processTouchEvent(ev);
                }
                break;
        }
        return mIsDragged || super.onTouchEvent(ev);

    }


    private void checkCanDragged(MotionEvent ev) {
        if (mIsDragged) {
            return;
        }

        float dx = ev.getX() - mDownX;
        float dy = ev.getY() - mDownY;
        boolean isDrag = Math.abs(dx) > mTouchSlop && Math.abs(dx) > Math.abs(dy);
        if (isShow()) {
            // 开启状态下，点击在content上就捕获事件，点击在菜单上则判断touchSlop
            int downX = (int) mDownX;
            int downY = (int) mDownY;
            if (isTouchContent(downX, downY)) {
                mIsDragged = true;
            } else if (isTouchMenu(downX, downY)) {
                mIsDragged = isDrag;
            }
        }else {
            if (isDrag) {
                mIsDragged = true;
            }
        }

        if (mIsDragged) {
            // 开始拖动后，分发down事件给DragHelper
            MotionEvent obtain = MotionEvent.obtain(ev);
            obtain.setAction(MotionEvent.ACTION_DOWN);
            mDragHelper.processTouchEvent(obtain);
            if (getParent() != null) {
                // 解决和父控件的滑动冲突。
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    /**
     * 判断down是否点击在menu上
     * @param x
     * @param y
     * @return
     */
    private boolean isTouchMenu(int x, int y) {
        View contentView = mMenuView;
        if (contentView == null) {
            return false;
        }
        Rect rect = new Rect();
        contentView.getHitRect(rect);
        return rect.contains(x, y);
    }

    /**
     * 判断down是否点击在Content上
     */
    private boolean isTouchContent(int x, int y) {
        View contentView = mContentView;
        if (contentView == null) {
            return false;
        }
        Rect rect = new Rect();
        contentView.getHitRect(rect);
        return rect.contains(x, y);
    }


    @Override
    public void computeScroll()
    {
        if (mDragHelper.continueSettling(true))
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /**
     * menu
     * @return
     */
    public boolean isShow(){
        return mMenuFliPercent == 1.0f?true:false;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams()
    {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new MarginLayoutParams(getContext(), attrs);
    }

    protected LayoutParams generateLayoutParams(LayoutParams p)
    {
        return new MarginLayoutParams(p);
    }



}
