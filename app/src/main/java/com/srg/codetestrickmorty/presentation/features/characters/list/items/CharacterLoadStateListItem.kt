package com.srg.codetestrickmorty.presentation.features.characters.list.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.srg.codetestrickmorty.R
import com.srg.codetestrickmorty.databinding.CharactersLoadStateFooterViewItemBinding

/**
 * Created by sebrodgar on 17/03/2021.
 */
class CharacterLoadStateListItem(
    private val binding: CharactersLoadStateFooterViewItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btCharacterItemRetryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.tvCharacterItemErrorMsg.text = loadState.error.localizedMessage
        }
        binding.pbCharacterItem.isVisible = loadState is LoadState.Loading
        binding.btCharacterItemRetryButton.isVisible = loadState !is LoadState.Loading
        binding.tvCharacterItemErrorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): CharacterLoadStateListItem {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.characters_load_state_footer_view_item, parent, false)
            val binding = CharactersLoadStateFooterViewItemBinding.bind(view)
            return CharacterLoadStateListItem(binding, retry)
        }
    }

}