package com.srg.codetestrickmorty.presentation.features.characters.list

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import com.srg.codetestrickmorty.R
import com.srg.codetestrickmorty.common.base.BaseFragment
import com.srg.codetestrickmorty.common.di.injections.ViewModelInjectionFactory
import com.srg.codetestrickmorty.common.util.lists.ListItem
import com.srg.codetestrickmorty.databinding.FragmentCharacterListBinding
import com.srg.codetestrickmorty.presentation.common.extensions.setUpToolbar
import com.srg.codetestrickmorty.presentation.common.viewBinding
import com.srg.codetestrickmorty.presentation.features.characters.list.adapters.CharacterListAdapter
import com.srg.codetestrickmorty.presentation.features.characters.list.adapters.CharacterLoadStatusListAdapter
import com.srg.codetestrickmorty.presentation.features.characters.list.items.CharacterListItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListFragment @Inject constructor(
    private val vmf: ViewModelInjectionFactory
) : BaseFragment(R.layout.fragment_character_list) {

    private val viewModel by viewModels<CharacterListViewModel> { vmf }
    private val binding by viewBinding(FragmentCharacterListBinding::bind)
    private val adapter = CharacterListAdapter(onItemClickListener = { onItemClick(it) })

    private var charactersJob: Job? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi(view.context)
        initAdapter(view.context)
        setListeners()
        getCharacters()
    }

    private fun initUi(context: Context) {
        setUpToolbar(binding.toolbar)
        binding.toolbar.title = context.getString(R.string.characters_list_title_screen)
    }

    override fun onStart() {
        super.onStart()
        binding.srlCharacter.isEnabled = true
        binding.rvCharacter.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        binding.srlCharacter.isEnabled = false
        binding.rvCharacter.adapter = null
        charactersJob?.cancel()
    }

    private fun setListeners() {
        binding.srlCharacter.setOnRefreshListener {
            binding.srlCharacter.isRefreshing = false
            getCharacters()
        }
    }

    private fun getCharacters() {
        charactersJob?.cancel()
        charactersJob = lifecycleScope.launch {
            viewModel.getCharacters().collectLatest {
                adapter.submitData(it.map { recipesUi ->
                    CharacterListItem(recipesUi)
                })
            }
        }
    }

    private fun initAdapter(context: Context) {
        binding.rvCharacter.adapter = adapter.withLoadStateHeaderAndFooter(
            header = CharacterLoadStatusListAdapter { adapter.retry() },
            footer = CharacterLoadStatusListAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            binding.pbCharacterList.isVisible = loadState.source.refresh is LoadState.Loading
            binding.btCharacterListRetryButton.isVisible =
                loadState.source.refresh is LoadState.Error

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    context,
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }

    private fun onItemClick(listItem: ListItem) {
        (listItem as? CharacterListItem)?.let {
            Toast.makeText(requireContext(), it.character.name, Toast.LENGTH_SHORT).show()
        }

    }
}