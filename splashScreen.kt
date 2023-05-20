package test.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview

import kotlinx.coroutines.delay

@Composable
@Preview
fun SplashScreen(
    onAnimComplete: () -> Unit = {}
) {

    LaunchedEffect(Unit) {
        delay(2000)
        onAnimComplete()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorBlue))
    ) {
        Text(
            text = "Splash screen",
            color = colorResource(id = R.color.colorWhite),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
