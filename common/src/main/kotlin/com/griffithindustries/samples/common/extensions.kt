package com.griffithindustries.samples.common

import java.util.*


/**
 * Unwrap a Java Optional instance into a Kotlin nullable type
 */
fun <T> Optional<T>.toNullable(): T? = this.orElse(null)

/**
 * Wrap a Kotlin nullable type into a Java Optional instance
 */
fun <T> T?.toOptional(): Optional<T> = Optional.ofNullable(this)

@OptIn(ExperimentalStdlibApi::class)
fun <K, V> Map<K, V?>.filterNotNullValues(): Map<K, V> =
    buildMap {
        for ((k, v) in this@filterNotNullValues) {
            if (v != null) {
                put(k, v)
            }
        }
    }
