package com.dana.gaib.ga.danagaib.base

import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dana.gaib.ga.danagaib.R
import com.dana.gaib.ga.danagaib.databinding.LayoutBaseBinding
import com.dana.gaib.ga.danagaib.databinding.OnBaseClickListener
import com.dana.gaib.ga.danagaib.databinding.RootHandler
import com.dana.gaib.ga.danagaib.utils.AppManager
import com.dana.gaib.ga.danagaib.utils.StatusBarUtil


/**
 * Activity 基类
 *
 * @param P MVP Presenter 类，继承 [BaseMVPPresenter]，若没有，使用 [BlankPresenter]
 * @param DB DataBinding 类，继承 [ViewDataBinding]
 */
abstract class BaseActivity<DB : ViewDataBinding>: AppCompatActivity(),BaseMVPView,
    OnBaseClickListener {

    /** 当前界面 Context 对象*/
    protected lateinit var mContext: AppCompatActivity

    /** 根布局 DataBinding 对象 */
    protected lateinit var baseBinding: LayoutBaseBinding

    /** 当前界面布局 DataBinding 对象 */
    protected lateinit var mBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 保存当前 Context 对象
        mContext = this

        // 添加到 AppManager 应用管理
        AppManager.addActivity(this)

        // 加载根布局，初始化 DataBinding
        baseBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.layout_base, null, false
        )
        // 绑定事件处理
        baseBinding.handler = RootHandler(this)
    }


    /**
     * 重写 onDestroy() 方法，移除 Activity 管理以及 MVP 生命周期管理
     */
    override fun onDestroy() {

        // 从应用管理移除当前 Activity 对象
        AppManager.removeActivity(this)
        super.onDestroy()
    }

    /**
     * 重写 setContentView(layoutResID) 方法，使其支持 DataBinding 以及标题栏、状态栏初始化操作
     */
    override fun setContentView(layoutResID: Int) {

        // 加载布局，初始化 DataBinding
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            layoutResID, null, false
        )

        // 将当前布局添加到根布局
        baseBinding.flContent.removeAllViews()
        baseBinding.flContent.addView(mBinding.root)

        // 设置布局
        super.setContentView(baseBinding.root)

        // 初始化状态栏
        initStatusBar()

        // 初始化标题栏
        initTitleBar()

    }

    /**
     *  设置 Toolbar
     */
    open protected fun setToolbar() {
        // 添加 Toolbar
        setSupportActionBar(baseBinding.toolbar)
        // 隐藏默认 title
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    /**
     * 初始化标题栏，抽象方法，子类实现标题栏自定义
     */
    open protected fun initTitleBar() {}

    /**
     * 初始化状态栏
     *
     * **系统版本21以上使用主题而非[StatusBarUtil]**
     */
    open protected fun initStatusBar() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            // 系统版本为19时使用
            StatusBarUtil.setResColor(mContext, R.color.colorTheme, 0)
        }
    }


    /**
     * 设置标题栏显示
     *
     * @param showTitle 是否显示
     */
    protected fun showTitleBar(showTitle: Boolean = true) {
        baseBinding.handler?.showTitleBar = showTitle
        if (showTitle) {
            setToolbar()
        }
    }

    /**
     * 设置标题栏能否隐藏
     *
     * @param canHide 能否隐藏
     */
    protected fun setToolbarHide(canHide: Boolean = true) {
        baseBinding.handler?.canToolbarHide = canHide
    }

    /**
     * 设置标题文本
     *
     * @param strResID 标题文本资源id
     */
    protected fun setTitleStr(@StringRes strResID: Int) {
        baseBinding.handler?.showTitleStr = true
        baseBinding.handler?.titleStr = getString(strResID)
    }

    /**
     * 设置标题文本
     *
     * @param str      标题文本
     */
    protected fun setTitleStr(str: String) {
        baseBinding.handler?.showTitleStr = true
        baseBinding.handler?.titleStr = str
    }

    /**
     * 设置标题栏左侧图标，默认返回按钮
     *
     * @param resID     标题栏左侧图标资源id，默认返回按钮
     */
    protected fun setIvLeft(@DrawableRes resID: Int = 0) {
        if (0 == resID) {
            // 使用默认图标
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        } else {
            // 使用指定图标
            baseBinding.handler?.ivLeftResID = resID
        }
    }

    /**
     * 重写BaseMvpView中方法，网络异常时调用
     */
    override fun netError() {
        val handler = baseBinding.handler
        handler?.let {
            if (it.showNoData) {
                it.showNoData = false
            }
            if (it.showLoading) {
                val drawable = baseBinding.ivLoading.drawable
                (drawable as? AnimationDrawable)?.stop()
                it.showLoading = false
            }
            if (!it.showNetError) {
                it.showNetError = true
            }
            listComplete()
        }
    }

    /**
     * 重写BaseMvpView中方法，无数据时调用
     */
    override fun noData() {
        val handler = baseBinding.handler
        handler?.let {
            if (it.showNetError) {
                it.showNetError = false
            }
            if (it.showLoading) {
                val drawable = baseBinding.ivLoading.drawable
                (drawable as? AnimationDrawable)?.stop()
                it.showLoading = false
            }
            if (!it.showNoData) {
                it.showNoData = true
            }
            listComplete()
        }
    }

    /**
     * 重写BaseMvpView中方法，加载数据时调用
     */
    override fun loading() {
        val handler = baseBinding.handler
        handler?.let {
            if (it.showNetError) {
                it.showNetError = false
            }
            if (it.showNoData) {
                it.showNoData = false
            }
            if (!it.showLoading) {
                val drawable = baseBinding.ivLoading.drawable
                (drawable as? AnimationDrawable)?.start()
                it.showLoading = true
            }
        }
    }

    /**
     * 重写BaseMvpView中方法，网络请求结束后调用，隐藏其他界面
     */
    override fun netFinished() {
        val handler = baseBinding.handler
        handler?.let {
            if (it.showNetError) {
                it.showNetError = false
            }
            if (it.showNoData) {
                it.showNoData = false
            }
            if (it.showLoading) {
                val drawable = baseBinding.ivLoading.drawable
                (drawable as? AnimationDrawable)?.stop()
                it.showLoading = false
            }
            listComplete()
        }
    }

    /**
     * 使用 [com.wj.swipelayout.SwipeToLoadLayout] 时重写，完成刷新步骤
     */
    open protected fun listComplete() {}

    /**
     * 标题栏左侧点击事件，默认结束当前界面
     */
    override fun onLeftClick() {
        finish()
    }

    /**
     * 无数据界面点击事件，默认显示加载中
     */
    override fun onNoDataClick() {
        loading()
    }

    /**
     * 网络异常界面点击事件，默认显示加载中
     */
    override fun onNetErrorClick() {
        loading()
    }
}