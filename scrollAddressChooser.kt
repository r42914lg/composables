package ru.dars.darsapp.core.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import ru.dars.darsapp.R
import ru.dars.darsapp.domain.models.local.CustomAddress
import ru.dars.darsapp.ui.theme.Typography

@Composable
fun ScrollAddressChooser(
    modifier: Modifier = Modifier,
    addresses: List<CustomAddress>,
    selectedInitial: Int = 0,
    onSelectionChanged: (index: Int) -> Unit = {},
) {

    val interactionSource = MutableInteractionSource()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
        itemsIndexed(addresses) {index, item ->
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(50))
                    .background(colorResource(
                        if (index == selectedInitial)
                            R.color.addressSelectedScroll
                        else
                            R.color.colorWhite)
                    ).clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        if (index != selectedInitial)
                            onSelectionChanged(index)
                    }
            ) {
                Text(
                    text = item.customNameOrStreetHouse,
                    style = Typography.body2,
                    color = colorResource(
                        if (index == selectedInitial)
                            R.color.colorBlue
                        else
                            R.color.colorBlack
                    ),
                    modifier = modifier
                        .padding(8.dp)
                        .wrapContentSize()
                )
            }
        }
    }
}
