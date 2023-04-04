package ru.dars.darsapp.core.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
fun IconTextNextItem(
    text: String = "Text",
    colorResId: Int = R.color.colorBlue,
    imageResId: Int = R.drawable.ic_bird,
    onClickAction: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
        ) { onClickAction() }
    ) {
        Image(painterResource(imageResId),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(start = 16.dp)
        )

        Text(text = text,
            style = Typography.body2,
            color = colorResource(R.color.colorBlack),
            modifier = Modifier
                .weight(1.0f)
                .padding(end = 16.dp, start = 16.dp)
        )

        Image(painterResource(R.drawable.ic_orange_arrow_next),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}

@Composable
@Preview
fun IconTextSwitchItem(
    text: String = "Text",
    colorResId: Int = R.color.colorBlue,
    imageResId: Int = R.drawable.ic_bird,
    checkedStateInit: Boolean = false,
    onClickAction: (isOn: Boolean) -> Unit = {}
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(painterResource(imageResId),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(start = 16.dp)
        )

        Text(text = text,
            style = Typography.body2,
            color = colorResource(R.color.colorBlack),
            modifier = Modifier
                .weight(1.0f)
                .padding(end = 16.dp, start = 16.dp)
        )

        var checkedState by rememberSaveable { mutableStateOf(checkedStateInit) }

        Switch(
            checked = checkedState,
            onCheckedChange = {
                checkedState = it
                onClickAction(checkedState)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = colorResource(R.color.colorWhite),
                checkedTrackColor = colorResource(R.color.colorBlue),
                uncheckedThumbColor = colorResource(R.color.colorWhite),
                uncheckedTrackColor = colorResource(R.color.colorGray),
            ),
        )
    }
}

@Composable
@Preview
fun TextSwitchItem(
    text: String = "Text",
    textColorResId: Int = R.color.colorBlack,
    backgroundColorResId: Int = R.color.textFieldBackgroundDefault,
    checkedStateInit: Boolean = false,
    onClickAction: (isOn: Boolean) -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(text = text,
            style = Typography.body2,
            color = colorResource(R.color.colorBlack),
            modifier = Modifier
                .weight(1.0f)
                .padding(end = 16.dp, start = 16.dp)
        )

        var checkedState by rememberSaveable { mutableStateOf(checkedStateInit) }
        
        Switch(
            checked = checkedState,
            onCheckedChange = {
                checkedState = it
                onClickAction(checkedState)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = colorResource(R.color.colorWhite),
                checkedTrackColor = colorResource(R.color.colorBlue),
                uncheckedThumbColor = colorResource(R.color.colorWhite),
                uncheckedTrackColor = colorResource(R.color.colorGray),
            )
        )
    }
}