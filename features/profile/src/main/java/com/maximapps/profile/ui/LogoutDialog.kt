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

package com.maximapps.profile.ui

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maximapps.profile.R

/**
 * Creates a logout dialog.
 *
 * @since 0.4.1
 */
internal fun logOutDialog(context: Context, onClick: () -> Unit) =
    MaterialAlertDialogBuilder(context)
        .setTitle(context.getString(R.string.logout_dialog_title))
        .setMessage(context.getString(R.string.logout_dialog_message))
        .setPositiveButton(R.string.logout_dialog_positive_btn_text) { _, _ -> onClick() }
        .setNegativeButton(R.string.logout_dialog_negative_btn_text) { _, _ -> }
