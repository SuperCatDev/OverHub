package com.sc.overhub.mapper

interface MapperExt<out V, in D> {
    fun mapTo(type: D): V
}