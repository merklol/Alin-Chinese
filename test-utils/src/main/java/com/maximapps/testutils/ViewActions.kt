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

package com.maximapps.testutils

import android.view.View
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import java.util.concurrent.TimeoutException

private const val INITIAL_DELAY = 50L
private const val MS_IN_SEC = 1000

class WaitForAction(private val millis: Long): ViewAction {
    override fun getConstraints(): Matcher<View> = isRoot()

    override fun getDescription() = "Wait for $millis milliseconds."

    override fun perform(uiController: UiController, view: View) {
        uiController.loopMainThreadForAtLeast(millis)
    }
}

class WaitUntilGoneAction(private val timeout: Long) : ViewAction {

    override fun getConstraints(): Matcher<View> {
        return CoreMatchers.any(View::class.java)
    }

    override fun getDescription(): String {
        return "wait up to $timeout milliseconds for the view to be gone"
    }

    override fun perform(uiController: UiController, view: View) {
        val endTime = System.currentTimeMillis() + timeout

        do {
            if (view.visibility == View.GONE) return
            uiController.loopMainThreadForAtLeast(INITIAL_DELAY)
        } while (System.currentTimeMillis() < endTime)

        throw PerformException.Builder()
            .withActionDescription(description)
            .withCause(TimeoutException("Waited $timeout milliseconds"))
            .withViewDescription(HumanReadables.describe(view))
            .build()
    }
}

/**
 * Perform action of waiting for a specific time.
 */
fun waitFor(seconds: Int) = WaitForAction((seconds * MS_IN_SEC).toLong())

fun waitUntilGone(seconds: Int) = WaitUntilGoneAction((seconds * MS_IN_SEC).toLong())
