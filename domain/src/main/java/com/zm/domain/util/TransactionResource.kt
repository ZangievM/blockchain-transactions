package com.zm.domain.util

sealed class TransactionResource<T> {
    class Connected: TransactionResource<Nothing>()
    class NewData<T>(data: T): TransactionResource<T>()
    class Disconnected(): TransactionResource<Nothing>()
    class Failure(val e: Throwable?): TransactionResource<Nothing>()
}

enum class TransactionCommands(val value: String) {
    PING("{\"op\":\"ping\"}"),
    SUBSCRIBE("{\"op\":\"unconfirmed_sub\"}"),
    UNSUBSCRIBE("{\"op\":\"unconfirmed_unsub\"}")
}
