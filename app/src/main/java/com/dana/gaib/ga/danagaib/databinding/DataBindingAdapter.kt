package com.dana.gaib.ga.danagaib.databinding

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.AppBarLayout

/**
 * DataBinding适配器
 */
object DataBindingAdapter {

    /**
     * 设置 Toolbar Flag，使其在布局向上滚动时自动隐藏
     *
     * @param toolbar [Toolbar]
     * @param canHide 能否自动隐藏
     */
    @BindingAdapter("canHide")
    @JvmStatic
    fun canToolbarHide(toolbar: Toolbar, canHide: Boolean) {
        val layoutParams = toolbar.layoutParams as? AppBarLayout.LayoutParams
        layoutParams?.let {
            it.scrollFlags = if (canHide) {
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS or AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
            } else {
                0
            }
            toolbar.layoutParams = it
        }
    }

    /**
     * 设置 Toolbar 左侧按钮图片
     *
     * @param toolbar [Toolbar]
     * @param leftResID 图片资源 id
     */
    @BindingAdapter("leftIcon")
    @JvmStatic
    fun leftIcon(toolbar: Toolbar, leftResID: Int) {
        if (0 != leftResID) {
            toolbar.setNavigationIcon(leftResID)
        }
    }

    /**
     * 设置 Toolbar 左侧按钮点击事件
     *
     * @param toolbar [Toolbar]
     * @param listener [OnAdapterLeftClickListener] 事件监听对象
     */
    @BindingAdapter("onLeftClick")
    @JvmStatic
    fun leftClick(toolbar: Toolbar, listener: OnAdapterLeftClickListener) {
        toolbar.setNavigationOnClickListener {
            listener.onLeftClick()
        }
    }

    interface OnAdapterLeftClickListener {
        fun onLeftClick()
    }

    /**
     * 设置控件是否可见
     *
     * @param v [View] 控件
     * @param visible 是否可见
     */
    @BindingAdapter("visibility")
    @JvmStatic
    fun setVisibility(v: View, visible: Boolean) {
        v.visibility = if (visible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
