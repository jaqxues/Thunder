package com.jaqxues.thunder.fragments

import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * This file was created by Jacques Hoffann (jaqxues) in the Project Thunder.<br>
 * Date: 07.07.2019 - Time 01:54.
 */
open class ScopedFragment : Fragment(), CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        job.cancel()
    }
}