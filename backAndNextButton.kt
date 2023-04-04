package ru.dars.darsapp.core.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
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

    val interactionSource = MutableInteractionSource()

    Row {

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(30))
                .background(colorResource(R.color.textFieldBackgroundDefault))
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { onBack() }
        ) {
            Image(
                painterResource(R.drawable.ic_previous),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = modifier
                    .height(50.dp)
                    .width(164.dp)
                    .clip(RoundedCornerShape(30))
                    .background(colorResource(R.color.colorBlue))
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        if (isEnabled)
                            onClickAction()
                    }

            ) {
                Row {
                    Text(
                        text = buttonText,
                        style = Typography.button,
                        color = colorResource(R.color.colorWhite),
                        modifier = Modifier.padding(start = 24.dp, end = 12.dp, top = 15.dp, bottom = 15.dp)
                    )
                    Image(
                        painterResource(R.drawable.ic_next),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(color = colorResource(R.color.colorWhite)),
                        modifier = Modifier.padding(end = 18.dp, top = 15.dp, bottom = 15.dp)
                    )
                }
            }
        }
}