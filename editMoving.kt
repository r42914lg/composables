package ru.dars.darsapp.core.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dars.darsapp.R
import ru.dars.darsapp.ui.theme.Typography

@Composable
@Preview
fun EditTextMovingPlaceholder(
    editFieldInitValue: String = "",
    placeHolder: String = "",
    onEditAction: (newValue: String) -> Unit = {},
) {

    var text by remember { mutableStateOf(editFieldInitValue) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BasicTextField(
            modifier = Modifier.width(IntrinsicSize.Min),
            value = text,
            onValueChange = {
                text = it
                onEditAction(text)
            },
            singleLine = true,
            textStyle = Typography.body2,
        )

        Spacer(modifier = Modifier.width(width = 8.dp))

        Text(
            text = placeHolder,
            style = Typography.body2,
            color = colorResource(R.color.colorGrayText)
        )

    }
}
