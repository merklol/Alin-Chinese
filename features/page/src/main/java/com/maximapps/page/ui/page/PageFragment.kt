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

package com.maximapps.page.ui.page

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.maximapps.core.data.models.SharedLesson
import com.maximapps.core.utils.toBitmap
import com.maximapps.coreui.toolbar.setup
import com.maximapps.page.R
import com.maximapps.page.data.models.Node
import com.maximapps.page.databinding.FragmentPageBinding
import com.maximapps.page.utils.addViews
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onStates
import javax.inject.Inject

@AndroidEntryPoint
class PageFragment @Inject constructor() : Fragment(R.layout.fragment_page) {
    private val args: PageFragmentArgs by navArgs()
    private val viewModel: PageViewModel by viewModels()
    private val binding: FragmentPageBinding by viewBinding()

    private val lesson get() = args.sharedLesson
    private val toolbar get() = binding.toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        parsePage(lesson)
        renderPage()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.addToFavorites -> {
            lesson?.let {
                viewModel.saveToFavorites(it)
                showMessage(R.string.added_to_favorites, it.primaryTitle)
            }
            true
        }
        R.id.removeFromFavorites -> {
            lesson?.let {
                viewModel.removeFromFavorites(it)
                showMessage(R.string.removed_from_favorites, it.primaryTitle)
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun showMessage(messageResId: Int, str: String = "") {
        Snackbar.make(requireView(), getString(messageResId, str), Snackbar.LENGTH_SHORT).show()
    }

    private fun parsePage(sharedLesson: SharedLesson?) = when (sharedLesson) {
        null -> showMessage(R.string.error_page_message)
        else -> viewModel.createPage(sharedLesson.body)
    }

    private fun renderPage() = onStates(viewModel) {
        when (it) {
            is PageStates.Parsed -> renderContent(it.content)
            is PageStates.Error -> showMessage(R.string.error_page_message)
        }
    }

    private fun renderContent(content: List<Node>) = when {
        content.isEmpty() -> showMessage(R.string.error_page_message)
        else -> binding.container.addViews(requireContext(), content)
    }

    private fun setupToolbar() = lesson?.let {
        toolbar.internal.setupWithNavController(findNavController())
        toolbar.internal.setup(R.menu.page_menu, it.secondaryTitle, ::onOptionsItemSelected)
        toolbar.expandedTitle.text = it.primaryTitle
        toolbar.headerImage.load(it.thumbnail.toBitmap()) { placeholder(R.drawable.ic_img_placeholder) }
    }
}

