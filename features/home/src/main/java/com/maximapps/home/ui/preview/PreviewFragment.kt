/*
 * MIT License
 *
 * Copyright (c) 2021 Maxim Smolyakov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.maximapps.home.ui.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.maximapps.core.data.models.SharedLesson
import com.maximapps.core.navigation.navigate
import com.maximapps.core.utils.toBitmap
import com.maximapps.home.R
import com.maximapps.home.databinding.FragmentPreviewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PreviewFragment @Inject constructor() : BottomSheetDialogFragment() {
    private val args: PreviewFragmentArgs by navArgs()
    private val viewModel: PreviewViewModel by viewModels()
    private val binding: FragmentPreviewBinding by viewBinding(CreateMethod.INFLATE)

    private val lesson get() = args.sharedLesson

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configBehavior()
        bindData(lesson)
        binding.openBtn.setOnClickListener(::onBtnClicked)
        binding.favoriteBtn.setOnClickListener(::onBtnClicked)
    }

    private fun onBtnClicked(view: View) = when (view.id) {
        R.id.openBtn -> {
            navigate(PreviewFragmentDirections.actionPreviewFragmentToPageFragment(lesson))
            dismiss()
        }
        else -> lesson?.let {
            viewModel.saveToFavorites(it)
            showMessage(R.string.added_to_favorites, it.title)
        }
    }

    private fun showMessage(messageResId: Int, str: String = "") {
        Snackbar.make(binding.rootView, getString(messageResId, str), Snackbar.LENGTH_SHORT).show()
    }

    private fun bindData(sharedLesson: SharedLesson?) {
        when (sharedLesson) {
            null -> showMessage(R.string.error_preview_message)
            else -> {
                binding.title.text = sharedLesson.title
                binding.description.text = sharedLesson.description
                binding.date.text = sharedLesson.date
                binding.imageHolder.load(sharedLesson.image.toBitmap()) {
                    placeholder(R.drawable.ic_img_placeholder)
                }
            }
        }
    }

    private fun configBehavior(coefficient: Double = 0.80) {
        dialog?.setOnShowListener {
            val dialog = it as BottomSheetDialog
            val bottomSheet = dialog.findViewById<View>(R.id.design_bottom_sheet)
            bottomSheet?.let { sheet ->
                val displayMetrics = requireActivity().resources.displayMetrics
                val behavior = BottomSheetBehavior.from(sheet)
                behavior.skipCollapsed = true
                behavior.peekHeight = (displayMetrics.heightPixels * coefficient).toInt()
            }
        }
    }
}
