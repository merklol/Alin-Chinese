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

package com.maximapps.favorites.ui.favorites

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.maximapps.core.navigation.navigate
import com.maximapps.favorites.R
import com.maximapps.favorites.databinding.FragmentFavoritesBinding
import com.maximapps.favorites.ui.favoriteslist.FavoritesListAdapter
import com.maximapps.favorites.ui.favoriteslist.swipe
import com.maximapps.favorites.utils.ResourcesProvider
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onStates
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment @Inject constructor(
    private val provider: ResourcesProvider
) : Fragment(R.layout.fragment_favorites) {
    private val binding: FragmentFavoritesBinding by viewBinding()
    private val viewModel: FavoritesViewModel by activityViewModels()

    private val adapter = FavoritesListAdapter()

    private val touchCallback = swipe {
        icon = provider.deleteIcon
        color = provider.redColor
        onSwipeLeft = {
            val item = adapter.getItemAt(it)
            viewModel.removeFromFavorites(item)
            showSnackBar(item.title, provider.snackBarActionText) {
                viewModel.saveToFavorites(item)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.favoriteLessons()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onStates(viewModel) {
            when (it) {
                is FavoritesStates.Fetching -> binding.progressBar.isVisible = true
                is FavoritesStates.Loaded -> {
                    adapter.setData(it.data)
                    binding.progressBar.isVisible = false
                }
            }
        }

        binding.favoritesList.adapter = adapter
        ItemTouchHelper(touchCallback).attachToRecyclerView(binding.favoritesList)
        adapter.setOnLessonListItemClicked {
            navigate(FavoritesFragmentDirections.actionFavoritesFragmentToPageFragment(it))
        }
    }

    private fun showSnackBar(text: String, actionText: String, listener: View.OnClickListener) {
        Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT)
            .setActionTextColor(Color.WHITE)
            .setAction(actionText, listener)
            .show()
    }
}
