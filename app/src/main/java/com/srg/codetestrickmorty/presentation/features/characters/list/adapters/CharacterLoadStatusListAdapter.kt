package com.srg.codetestrickmorty.presentation.features.characters.list.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.srg.codetestrickmorty.presentation.features.characters.list.items.CharacterLoadStateListItem

class CharacterLoadStatusListAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<CharacterLoadStateListItem>() {

    override fun onBindViewHolder(holder: CharacterLoadStateListItem, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CharacterLoadStateListItem {
        return CharacterLoadStateListItem.create(parent, retry)
    }

}