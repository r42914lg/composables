package ru.dars.darsapp.core.ui.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dars.darsapp.R
import ru.dars.darsapp.ui.theme.Typography

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun EditText(
    key: Int = -1,
    editFieldInitValue: String = "",
    onEditAction: (newValue: String) -> Unit = {},
) {

    var isEditMode by rememberSaveable { mutableStateOf(false) }
    var rEditField by rememberSaveable(key) { mutableStateOf(editFieldInitValue) }
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            isEditMode = true
        }
    ) {

        val keyboardController = LocalSoftwareKeyboardController.current

        if (isEditMode) {
            Row() {
                BasicTextField(
                    value = rEditField,
                    onValueChange = { rEditField = it },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            onEditAction(rEditField)
                            focusRequester.freeFocus()
                            isEditMode = false
                        }
                    ),
                    textStyle = Typography.h1,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                        .weight(1.0f)
                        .focusRequester(focusRequester)
                )

                Image(
                    painterResource(R.drawable.ic_edit),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.padding(all = 16.dp)
                )

                LaunchedEffect(key) {
                    focusRequester.requestFocus()
                }

                DisposableEffect(key) {
                    onDispose {
                        keyboardController?.hide()
                        isEditMode = false
                    }
                }

                BackHandler {
                    keyboardController?.hide()
                    focusRequester.freeFocus()
                    isEditMode = false
                }
            }
        } else {
            Row() {
                Text(
                    text = rEditField,
                    style = Typography.h1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = colorResource(R.color.colorBlack),
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                        .weight(1.0f)
                )

                keyboardController?.hide()

                Image(
                    painterResource(R.drawable.ic_edit),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.padding(all = 16.dp)
                )
            }
        }
    }
}