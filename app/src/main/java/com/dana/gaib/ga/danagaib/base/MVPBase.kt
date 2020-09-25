package com.dana.gaib.ga.danagaib.base


/**
 * Presenter基类
 *
 * @param V MVP View类型 继承[BaseMVPView]
 * @param M MVP Module 继承[BaseMVPModule]
 */
open class BaseMVPPresenter<V : BaseMVPView, M : BaseMVPModule> {

    /** MVP View 对象  */
    protected var mView: V? = null

    /** MVP Module 对象  */
    protected lateinit var mModule: M

    fun setModule(mModule: BaseMVPModule) {
        this.mModule = mModule as M
    }

    /**
     * 界面绑定，关联 MVP View
     *
     * @param view MVP View
     */
    fun attach(view: V) {
        mView = view
    }

    /**
     * 解除绑定，去除 MVP View 引用
     */
    fun detach() {
        mView = null
    }

    /**
     * 检查请求返回数据，并在登录状态异常时弹出提示
     *
     * @param data 返回数据
     * @param T  返回数据类型
     *
     * @return 是否成功
     */
    protected fun <T : BaseEntity> checkResponse(data: T): Boolean {
        return data.code == 200
    }

}

/**
 * MVP Module基类
 */
open class BaseMVPModule() {

}

/**
 * MVP View基类
 */
interface BaseMVPView {

    /**
     * 网络故障
     */
    fun netError()

    /**
     * 无数据
     */
    fun noData()

    /**
     * 加载中
     */
    fun loading()

    /**
     * 网络请求结束
     */
    fun netFinished()
}

/**
 * 网络请求结束回调接口
 *
 * @param E 请求成功返回数据类型
 */
interface OnNetFinishedListener<in E : BaseEntity> {

    /**
     * 网络请求成功
     *
     * @param entity 请求返回数据
     */
    fun onSuccess(entity: E)

    /**
     * 请求失败
     *
     * @param throwable 失败信息
     */
    fun onFailed(throwable: Throwable)
}

/**
 * 空白 Presenter，继承 [BaseMVPPresenter]
 */
class BlankPresenter : BaseMVPPresenter<BaseMVPView, BaseMVPModule>()


