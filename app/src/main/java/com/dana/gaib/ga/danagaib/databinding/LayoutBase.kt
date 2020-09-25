package com.dana.gaib.ga.danagaib.databinding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

/**
 * 根布局 Handler
 */
open class RootHandler(private val listener: OnBaseClickListener) : BaseObservable() {


    /** 标记-是否显示标题栏  */
    @get:Bindable
    open var showTitleBar = false
        set(value) {
            field = value
                notifyPropertyChanged(BR.showTitleBar)
        }

    /** 标记-是否显示标题  */
    @get:Bindable
    open var showTitleStr = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showTitleStr)
        }

    /** 标记-是否显示网络异常  */
    @get:Bindable
    open var showNetError = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showNetError)
        }

    /** 标记-是否显示无数据  */
    @get:Bindable
    open var showNoData = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showNoData)
        }

    /** 标记-是否显示加载中  */
    @get:Bindable
    open var showLoading = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showLoading)
        }

    /** 标记- Toolbar 能否隐藏 */
    @get:Bindable
    open var canToolbarHide = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.canToolbarHide)
        }

    /** 左侧按钮资源 id */
    @get:Bindable
    open var ivLeftResID = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.ivLeftResID)
        }

    /** 标题文本  */
    @get:Bindable
    open var titleStr = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.titleStr)
        }

    /**
     * 左侧按钮点击事件
     */
    open fun onLeftClick() {
        listener.onLeftClick()
    }

    /**
     * 无数据界面点击事件
     */
    open fun onNoDataClick() {
        listener.onNoDataClick()
    }

    /**
     * 无网络界面点击事件
     */
    open fun onNetErrorClick() {
        listener.onNetErrorClick()
    }

}


/**
 * 根布局点击事件监听接口
 */
interface OnBaseClickListener {

    /**
     * 左侧按钮点击事件
     */
    fun onLeftClick()

    /**
     * 无数据界面点击
     */
    fun onNoDataClick()

    /**
     * 网络异常界面点击
     */
    fun onNetErrorClick()
}
