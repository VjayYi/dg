package com.dana.gaib.ga.danagaib.base

/**
 * 数据实体类基类
 */
open class BaseEntity {

    /** 返回信息  */
    open var msg: String? = null
    /** 返回码  */
    open var code: Int = 0
    /** 层级  */
    open var total: Int = 0
}
