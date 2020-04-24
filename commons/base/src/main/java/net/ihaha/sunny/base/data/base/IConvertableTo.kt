package net.ihaha.sunny.base.data.base

interface IConvertableTo<T> {
    fun convertTo(): T?
}