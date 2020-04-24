package net.ihaha.sunny.base.observer

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val computation: CoroutineDispatcher
}