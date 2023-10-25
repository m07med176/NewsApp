package iti.workshop.domain.utils

sealed class NetworkResponseState<T> {
    class OnIdle<T>: NetworkResponseState<T>()
    class OnLoading<T>: NetworkResponseState<T>()
    class OnSuccess<T>(val data:T): NetworkResponseState<T>()
    class OnError<T>(val error:Throwable): NetworkResponseState<T>()
    class OnNetworkError<T>(val code:Int, val data:String): NetworkResponseState<T>()
}