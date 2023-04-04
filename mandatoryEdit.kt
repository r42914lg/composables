package ru.dars.darsapp.core.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dars.darsapp.R
import ru.dars.darsapp.ui.theme.Typography

@Composable
@Preview
fun MandatoryTextEdit(
    modifier: Modifier = Modifier,
    missingValueBackgroundColorId: Int = R.color.textFieldBackgroundMissing,
    defaultBackgroundColorId: Int = R.color.textFieldBackgroundDefault,
    textColorId: Int = R.color.colorBlack,
    missingTextColorId: Int = R.color.colorRed,
    editFieldInitValue: String = "",
    placeholderValue: String = "",
    onValueEdit: (newValue: String) -> Unit = {},
) {

    var rText by rememberSaveable { mutableStateOf(editFieldInitValue) }
    var rMissingFlag by rememberSaveable { mutableStateOf(editFieldInitValue.isEmpty()) }
    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = rText,
        onValueChange = {
            rText = it
            rMissingFlag = rText.isEmpty()
            onValueEdit(rText)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        textStyle = Typography.body2,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 18.5.dp),
            ) {
                if (rMissingFlag) {
                    Text(
                        text = placeholderValue,
                        style = Typography.body2,
                        color = colorResource(missingTextColorId)
                    )
                }
                innerTextField()
            }
        },
        modifier = modifier.background(
            color = colorResource(if (rMissingFlag) missingValueBackgroundColorId else defaultBackgroundColorId),
            shape = RoundedCornerShape(20.dp)
        ).height(56.dp)
    )
}

@Composable
@Preview
fun MandatoryPhoneEdit(
    modifier: Modifier = Modifier,
    missingValueBackgroundColorId: Int = R.color.textFieldBackgroundMissing,
    defaultBackgroundColorId: Int = R.color.textFieldBackgroundDefault,
    textColorId: Int = R.color.colorBlack,
    missingTextColorId: Int = R.color.colorRed,
    editFieldInitValue: String = "",
    placeholderValue: String = "",
    onValueEdit: (newValue: String) -> Unit = {},
) {

    var rText by rememberSaveable { mutableStateOf(editFieldInitValue) }
    var rMissingFlag by rememberSaveable { mutableStateOf(editFieldInitValue.isEmpty()) }
    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = rText,
        onValueChange = {
            rText = it
            rMissingFlag = !rText.isValidPhone()
            onValueEdit(rText)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Phone
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        textStyle = Typography.body2,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 18.5.dp),
            ) {
                if (rMissingFlag) {
                    Text(
                        text = if (rText.isEmpty()) placeholderValue else "",
                        style = Typography.body2,
                        color = colorResource(missingTextColorId)
                    )
                }
                innerTextField()
            }
        },
        modifier = modifier.background(
            color = colorResource(if (rMissingFlag) missingValueBackgroundColorId else defaultBackgroundColorId),
            shape = RoundedCornerShape(20.dp)
        ).height(56.dp)
    )
}