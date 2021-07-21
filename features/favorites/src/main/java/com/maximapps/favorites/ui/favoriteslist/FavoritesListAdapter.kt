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

package com.maximapps.favorites.ui.favoriteslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.maximapps.core.data.models.SharedLesson
import com.maximapps.core.domain.Lesson
import com.maximapps.core.domain.toSharedLesson
import com.maximapps.core.utils.toBitmap
import com.maximapps.favorites.databinding.LessonListItemBinding

/**
 * Register a callback to be invoked when this list item view is clicked.
 *
 * @since 0.3.1
 */
fun interface OnLessonListItemClicked {
    fun onClicked(item: SharedLesson)
}

/**
 * Implementation of [RecyclerView.Adapter] for the favorites list.
 *
 * @since 0.3.1
 */
class FavoritesListAdapter : RecyclerView.Adapter<FavoritesListAdapter.ListItemViewHolder>() {
    private val items = mutableListOf<Lesson>()
    private var listener: OnLessonListItemClicked? = null

    fun setOnLessonListItemClicked(listener: OnLessonListItemClicked) {
        this.listener = listener
    }

    fun setData(data: List<Lesson>) {
        val result = DiffUtil.calculateDiff(FavoritesDiffUtil(items, data))
        items.clear()
        items.addAll(data)
        result.dispatchUpdatesTo(this)
    }

    fun getItemAt(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListItemViewHolder(
        binding = LessonListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false),
        listener = listener
    )

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size

    class ListItemViewHolder(
        private val binding: LessonListItemBinding,
        private val listener: OnLessonListItemClicked?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: Lesson) {
            binding.title.text = lesson.title
            binding.date.text = lesson.date
            binding.imageHolder.setImageBitmap(lesson.image.toBitmap())

            itemView.setOnClickListener {
                val bitmap = binding.imageHolder.drawable.toBitmap()
                listener?.onClicked(lesson.toSharedLesson(bitmap))
            }
        }
    }
}
