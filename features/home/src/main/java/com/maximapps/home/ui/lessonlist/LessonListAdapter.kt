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

package com.maximapps.home.ui.lessonlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.maximapps.core.data.models.SharedLesson
import com.maximapps.core.utils.setDelayedClickListener
import com.maximapps.home.R
import com.maximapps.home.data.models.Lesson
import com.maximapps.home.data.toSharedLesson
import com.maximapps.home.databinding.LessonListItemTemplate1Binding
import com.maximapps.home.databinding.LessonListItemTemplate2Binding

/**
 * Register a callback to be invoked when this list item view is clicked.
 *
 * @since 0.1
 */
fun interface OnLessonListItemClicked {
    fun onClicked(item: SharedLesson)
}

/**
 * Implementation of [DiffUtil.ItemCallback] for the lesson list.
 *
 * @since 0.1
 */
class LessonListDiffUtil : DiffUtil.ItemCallback<Lesson>() {
    override fun areItemsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
        return oldItem == newItem
    }
}

/**
 * Implementation of [PagingDataAdapter] for the lesson list.
 *
 * @since 0.1
 */
class LessonListAdapter(callback: DiffUtil.ItemCallback<Lesson>) :
    PagingDataAdapter<Lesson, LessonListAdapter.ItemViewHolder>(callback) {

    private var listener: OnLessonListItemClicked? = null

    fun setOnLessonListItemClicked(listener: OnLessonListItemClicked) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        LessonListItemTypes.Regular -> RegularListItemViewHolder(
            binding = LessonListItemTemplate2Binding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            listener = listener
        )

        LessonListItemTypes.Medium -> MediumLessonItemViewHolder(
            binding = LessonListItemTemplate1Binding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            listener = listener
        )

        else -> throw IllegalArgumentException("Invalid view type.")
    }

    override fun getItemViewType(position: Int) = when (getItem(position)?.template) {
        2 -> LessonListItemTypes.Medium
        else -> LessonListItemTypes.Regular
    }

    abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(lesson: Lesson)
    }

    class RegularListItemViewHolder(
        private val binding: LessonListItemTemplate2Binding,
        private val listener: OnLessonListItemClicked?
    ) : ItemViewHolder(binding.root) {

        override fun bind(lesson: Lesson) {
            itemView.isEnabled = false

            binding.title.text = lesson.title
            binding.date.text = lesson.date
            binding.imageHolder.load(lesson.imageSrc) {
                placeholder(R.drawable.ic_img_placeholder)
                listener(onSuccess = { _, _ -> itemView.isEnabled = true })
            }

            itemView.setDelayedClickListener {
                val bitmap = binding.imageHolder.drawable.toBitmap()
                listener?.onClicked(lesson.toSharedLesson(bitmap))
            }
        }
    }

    class MediumLessonItemViewHolder(
        private val binding: LessonListItemTemplate1Binding,
        private val listener: OnLessonListItemClicked?
    ) : ItemViewHolder(binding.root) {

        override fun bind(lesson: Lesson) {
            itemView.isEnabled = false

            binding.title.text = lesson.title
            binding.date.text = lesson.date
            binding.imageHolder.load(lesson.imageSrc) {
                placeholder(R.drawable.ic_img_placeholder)
                listener(onSuccess = { _, _ -> itemView.isEnabled = true })
            }

            itemView.setDelayedClickListener {
                val bitmap = binding.imageHolder.drawable.toBitmap()
                listener?.onClicked(lesson.toSharedLesson(bitmap))
            }
        }
    }
}
