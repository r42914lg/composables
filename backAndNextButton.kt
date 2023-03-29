package ru.dars.darsapp.core.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dars.darsapp.R
import ru.dars.darsapp.ui.theme.Typography

@Composable
@Preview
fun BackAndNextButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
    buttonText: String = "",
    onClickAction: () -> Unit = {},
    onBack: () -> Unit = {},
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            painterResource(R.drawable.ic_previous),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 16.dp)
                .clickable { onBack() }
        )

        Spacer(modifier = Modifier.width(16.dp))

        CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
            Button(
                enabled = isEnabled,
                shape = RoundedCornerShape(30),
                onClick = { onClickAction() },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.colorBlue)),
                modifier = modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 16.dp, end = 16.dp)

            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = buttonText,
                        style = Typography.button,
                        color = colorResource(R.color.colorWhite)
                    )
                    Image(
                        painterResource(R.drawable.ic_next),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            }
        }
    }
}