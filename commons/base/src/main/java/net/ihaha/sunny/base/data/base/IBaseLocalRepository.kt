package net.ihaha.sunny.base.data.base

interface IBaseLocalRepository<out L : ILocalDataSource> {
    val localDataSource: L
}
