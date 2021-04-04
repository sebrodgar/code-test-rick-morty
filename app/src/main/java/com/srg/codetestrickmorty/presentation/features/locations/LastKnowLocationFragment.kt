package com.srg.codetestrickmorty.presentation.features.locations

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.srg.codetestrickmorty.R
import com.srg.codetestrickmorty.common.base.BaseFragment
import com.srg.codetestrickmorty.common.di.injections.ViewModelInjectionFactory
import com.srg.codetestrickmorty.common.extensions.simpleFormat
import com.srg.codetestrickmorty.databinding.FragmentLastKnowLocationBinding
import com.srg.codetestrickmorty.domain.features.locations.models.LastKnowLocationDomainModel
import com.srg.codetestrickmorty.presentation.common.extensions.setUpToolbar
import com.srg.codetestrickmorty.presentation.common.extensions.showError
import com.srg.codetestrickmorty.presentation.common.flow.observeLce
import com.srg.codetestrickmorty.presentation.common.viewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LastKnowLocationFragment @Inject constructor(
    private val vmf: ViewModelInjectionFactory
) : BaseFragment(R.layout.fragment_last_know_location) {

    private val viewModel by viewModels<LastKnowLocationViewModel> { vmf }
    private val binding by viewBinding(FragmentLastKnowLocationBinding::bind)
    private val args: LastKnowLocationFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi(view.context)
        initObservers(view.context)
        getLastKnowLocation()
    }

    private fun initUi(context: Context) {
        setUpToolbar(binding.toolbar)
        binding.toolbar.title = context.getString(R.string.last_know_location_title_screen)
        binding.tvLastKnowLocationCharacterName.text = args.characterName
    }

    private fun initObservers(context: Context) {
        viewModel.lastKnowLocation.observeLce(viewLifecycleOwner,
            onContent = {
                updateUi(context, it)
            },
            onError = {
                showError(context, it) {
                    getLastKnowLocation()
                }
            },
            onLoading = {
                binding.pbLastKnowLocation.isVisible = it
            }
        )
    }

    private fun getLastKnowLocation() {
        viewModel.getLastKnowLocation(args.locationId)
    }

    private fun updateUi(
        context: Context,
        lastKnowLocationDomainModel: LastKnowLocationDomainModel
    ) {
        lastKnowLocationDomainModel.apply {
            binding.tvLastKnowLocationName.apply {
                text = name
                isInvisible = false
            }
            binding.tvLastKnowLocationType.apply {
                text = type
                isInvisible = false
            }
            binding.tvLastKnowLocationDimension.apply {
                text = dimension
                isInvisible = false
            }
            binding.tvLastKnowLocationResidents.apply {
                text = String.format(
                    context.getString(R.string.character_last_known_location_number_of_residents),
                    residents.size
                )
                isInvisible = false
            }
            binding.tvLastKnowLocationCreated.apply {
                text = context.getString(
                    R.string.character_last_known_location_created,
                    created.simpleFormat()
                )
                isInvisible = false
            }
        }
    }
}