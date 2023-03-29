package ru.dars.darsapp.core.ui.compose

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowToast(text: String) {
    Toast.makeText(
        LocalContext.current,
        text,
        Toast.LENGTH_LONG
    ).show()
}