package com.srg.codetestrickmorty.presentation.features.characters.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.srg.codetestrickmorty.common.util.lists.DiffUtilListItemCallback
import com.srg.codetestrickmorty.common.util.lists.ListItem
import com.srg.codetestrickmorty.common.util.lists.ListViewHolder

class CharacterListAdapter(
    comparator: DiffUtil.ItemCallback<ListItem> = DiffUtilListItemCallback,
    private val onItemClickListener: ((ListItem) -> Unit)? = null,
    private val onFavClickListener: ((T: ListItem) -> Unit)? = null
) : PagingDataAdapter<ListItem, ListViewHolder>(comparator) {

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        item?.bind(holder, onItemClickListener, onFavClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemViewType(position: Int): Int =
        getItem(position)?.layoutId ?: throw UnsupportedOperationException("Unknow view")

}