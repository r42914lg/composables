package ru.dars.darsapp.core.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dars.darsapp.R
import ru.dars.darsapp.ui.theme.Typography

@Composable
@Preview
fun ProgressButton(
    modifier: Modifier = Modifier,
    backgroundColorId: Int = R.color.colorBlue,
    textColorId: Int = R.color.colorWhite,
    text: String = "",
    requestInProgress: Boolean = false,
    onClickAction: () -> Unit = {},
) {
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Button(
            shape = RoundedCornerShape(20),
            elevation = null,
            onClick = { if (!requestInProgress) onClickAction() },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.colorBlue)),
            modifier = modifier.height(50.dp)

        ) {
            if (requestInProgress) {
                CircularProgressIndicator(
                    color = colorResource(id = textColorId),
                    modifier = Modifier.size(25.dp)
                )
            } else {
                Text(
                    text = text,
                    style = Typography.button,
                    color = colorResource(textColorId)
                )
            }
        }
    }
}