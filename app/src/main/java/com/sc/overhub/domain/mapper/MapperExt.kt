package com.sc.overhub.domain.mapper

interface MapperExt<out V, in D> {
    fun mapTo(type: D): V
}