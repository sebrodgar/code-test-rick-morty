package com.srg.codetestrickmorty.common.util.lists

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

object DiffUtilListItemCallback : DiffUtil.ItemCallback<ListItem>() {

    override fun areItemsTheSame(
        oldItem: ListItem,
        newItem: ListItem
    ): Boolean =
        oldItem.itemId == newItem.itemId

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: ListItem,
        newItem: ListItem
    ): Boolean =
        oldItem == newItem
}