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

package com.maximapps.home.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maximapps.core.navigation.navigate
import com.maximapps.home.R
import com.maximapps.home.databinding.FragmentHomeBinding
import com.maximapps.home.databinding.FragmentHomeErrorBinding
import com.maximapps.home.ui.lessonlist.LessonListAdapter
import com.maximapps.home.ui.lessonlist.LessonListDiffUtil
import com.maximapps.home.ui.lessonlist.LessonListLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class HomeFragment @Inject constructor() : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()
    private val errorBinding: FragmentHomeErrorBinding by viewBinding()
    private val viewModel: HomeViewModel by activityViewModels()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        LessonListAdapter(LessonListDiffUtil())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter.withLoadStateFooter(LessonListLoadStateAdapter())
        adapter.addLoadStateListener(::renderLoadStates)

        errorBinding.retryBtn.setOnClickListener { adapter.retry() }

        adapter.setOnLessonListItemClicked {
            navigate(HomeFragmentDirections.actionShowPreviewDialog(it))
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.lessons.collectLatest(adapter::submitData)
            }
        }
    }

    private fun renderLoadStates(states: CombinedLoadStates) {
        errorBinding.group.isVisible = states.refresh is LoadState.Error
        binding.recyclerView.isVisible = states.refresh != LoadState.Loading
        binding.progress.isVisible = states.refresh == LoadState.Loading
    }
}
