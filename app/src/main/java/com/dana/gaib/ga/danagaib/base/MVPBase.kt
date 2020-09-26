package com.dana.gaib.ga.danagaib.base


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



