package ru.dars.darsapp.core.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dars.darsapp.R
import ru.dars.darsapp.ui.theme.Typography

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun FilterTextEdit(
    modifier: Modifier = Modifier,
    defaultBackgroundColorId: Int = R.color.textFieldBackgroundDefault,
    textColorId: Int = R.color.colorBlack,
    placeholderColorId: Int = R.color.colorGray,
    editFieldInitValue: String = "",
    placeholderValue: String = "Название или ИНН организации",
    onValueEdit: (newValue: String) -> Unit = {},
) {

    var rText by rememberSaveable { mutableStateOf(editFieldInitValue) }
    var rMissingFlag by rememberSaveable { mutableStateOf(editFieldInitValue.isEmpty()) }

    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = rText,
        onValueChange = {
            rText = it
            rMissingFlag = rText.isEmpty()
            onValueEdit(rText)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
            }
        ),
        textStyle = Typography.body2,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(defaultBackgroundColorId),
                        shape = RoundedCornerShape(size = 16.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(R.drawable.ic_filter),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.padding(all = 16.dp)
                )
                Spacer(modifier = Modifier.width(width = 8.dp))
                Box {
                    if (rMissingFlag) {
                        Text(
                            text = placeholderValue,
                            style = Typography.body2,
                            color = colorResource(placeholderColorId)
                        )
                    }
                    innerTextField()
                }
            }
        },
    )
}