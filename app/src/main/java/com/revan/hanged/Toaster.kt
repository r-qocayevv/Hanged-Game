package com.revan.hanged

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest

class Toaster {

    private var toast: Toast? = null

    val toastFlow = MutableSharedFlow<String>()


    suspend fun emitToastMessage(message: String) {
        toastFlow.emit(message)
    }

    suspend fun handleToastMessage(context: Context, lifecycle: Lifecycle) {
        toastFlow
            .asSharedFlow()
            .flowWithLifecycle(lifecycle)
            .collectLatest { toastMessage ->
                toast?.cancel()
                toast = Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT)
                toast?.show()
            }
    }

}