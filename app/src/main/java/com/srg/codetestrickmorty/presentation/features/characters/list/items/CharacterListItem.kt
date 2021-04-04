package com.srg.codetestrickmorty.presentation.features.characters.list.items


import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.srg.codetestrickmorty.R
import com.srg.codetestrickmorty.common.util.lists.ListItem
import com.srg.codetestrickmorty.common.util.lists.ListViewHolder
import com.srg.codetestrickmorty.databinding.ListItemCharacterBinding
import com.srg.codetestrickmorty.presentation.features.characters.models.CharacterUiModel

class CharacterListItem(
    val character: CharacterUiModel
) : ListItem(
    R.layout.list_item_character,
    character
) {

    private var _binding: ListItemCharacterBinding? = null
    private val binding: ListItemCharacterBinding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun bind(
        viewHolder: ListViewHolder,
        onClickListener: ((T: ListItem) -> Unit)?
    ) {
        super.bind(viewHolder, onClickListener)
        _binding = ListItemCharacterBinding.bind(viewHolder.containerView)
        binding.tvCharacterName.text = character.name
        Glide.with(viewHolder.itemView.context).load(character.image)
            .into(binding.ivCharacterImage)
        binding.tvCharacterLastKnowLocationText.text = character.location.name
        binding.tvCharacterStatusGender.text = "${character.status.name} - ${character.gender.name}"
        viewHolder.containerView.setOnClickListener {
            onClickListener?.invoke(this)
        }
    }

}