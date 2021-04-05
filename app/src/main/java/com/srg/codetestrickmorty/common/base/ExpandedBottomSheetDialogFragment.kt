package com.srg.codetestrickmorty.common.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

abstract class ExpandedBottomSheetDialogFragment(
    @LayoutRes private val layout: Int
) : BaseBottomSheetDialogFragment(layout) {

    override fun onStart() {
        super.onStart()
        setDialogHeight()
        view?.post { displayExpanded() }
    }

    private fun setDialogHeight() {
        dialog?.let {
            val bottomSheet: View =
                it.findViewById(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        }
    }

    private fun displayExpanded() {
        view?.let {
            val parent = it.parent as View
            val params = parent.layoutParams as CoordinatorLayout.LayoutParams
            val behavior = params.behavior
            if (behavior is BottomSheetBehavior) {
                behavior.peekHeight = it.measuredHeight
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }
}
