package net.ihaha.sunny.base.data.base

interface IBaseRemoteRepository<out R : IRemoteDataSource> {
    val remoteDataSource: R
}
