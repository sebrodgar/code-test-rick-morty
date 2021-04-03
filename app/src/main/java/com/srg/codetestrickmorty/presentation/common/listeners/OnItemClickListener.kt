package com.srg.glotonup.presentation.common.listeners

interface OnItemClickListener<T> {
    fun onItemClick(t: T)
}

interface OnItemListClickListener<T> {
    fun onItemListClick(t: T, position: Int)
}