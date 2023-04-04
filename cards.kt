package ru.dars.darsapp.core.ui.compose

import android.graphics.Rect
import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ru.dars.darsapp.R
import ru.dars.darsapp.ui.theme.Typography

/**
 * Services cards
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun CardWithBackgroundAndText(
    title: String = "Title",
    message: String = "Message",
    colorResId: Int = R.color.colorBlue,
    imageResId: Int? = null,
    onClickAction: () -> Unit = {}
) {
    var isAlreadySqueezed by remember { mutableStateOf(false) }
    var isAlreadyRestored by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (isAlreadySqueezed) SCALE_FACTOR else 1.0f)

    var leftIs by remember { mutableStateOf(0.dp) }
    var topIs by remember { mutableStateOf(0.dp) }
    var widthIs by remember { mutableStateOf(0.dp) }
    var heightIs by remember { mutableStateOf(0.dp) }

    Card(
        shape = RoundedCornerShape(22.dp),
        backgroundColor = colorResource(colorResId),
        modifier = Modifier
            .height(110.dp)
            .scale(scale)
            .onGloballyPositioned { coordinates ->
                leftIs = coordinates.positionInRoot().x.dp
                topIs = coordinates.positionInRoot().y.dp
                widthIs = coordinates.size.width.dp
                heightIs = coordinates.size.height.dp
            }
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (!isAlreadySqueezed) {
                            isAlreadySqueezed = true
                            isAlreadyRestored = false
                        }
                    }
                    MotionEvent.ACTION_MOVE -> {
                        if (!isAlreadySqueezed) {
                            isAlreadySqueezed = true
                            isAlreadyRestored = false
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        if (!isAlreadyRestored) {
                            isAlreadyRestored = true
                            isAlreadySqueezed = false

                            if (eventIsInside(it, leftIs.value.toInt(), topIs.value.toInt(),
                                    widthIs.value.toInt(), heightIs.value.toInt()))
                                onClickAction.invoke()
                        }
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        if (!isAlreadyRestored) {
                            isAlreadyRestored = true
                            isAlreadySqueezed = false
                        }
                    }
                }
                true
            }
    ) {
        ConstraintLayout {
            val (titleText, messageText, cardImage) = createRefs()

            Text(text = title,
                style = Typography.h3,
                color = colorResource(R.color.colorWhite),
                modifier = Modifier.constrainAs(titleText) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
            )

            Text(text = message,
                style = Typography.h4,
                color = colorResource(R.color.colorWhite),
                modifier = Modifier
                    .constrainAs(messageText) {
                        top.linkTo(titleText.bottom, margin = 6.dp)
                        start.linkTo(titleText.start)
                    }
                    .padding(end = 16.dp)
            )

            if (imageResId != null) {
                Image(painterResource(imageResId),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.constrainAs(cardImage) {
                        end.linkTo(parent.end, margin = 16.dp)
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                    }
                )
            }
        }
    }
}

fun eventIsInside(event: MotionEvent, left: Int, top: Int, width: Int, height: Int): Boolean {
    val eventX = event.x
    val eventY = event.y

    val right = left + width
    val bottom = top + height

    val rect = Rect(left, top, right, bottom)
    return rect.contains(eventX.toInt(), eventY.toInt())
}

const val SCALE_FACTOR = 0.98f

/**
 * Address items
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardWithTextAndNext(
    title: String = "Title",
    message: String = "Message",
    onClickAction: () -> Unit = {}
) {
    Card(
        onClick = onClickAction,
        shape = RoundedCornerShape(22.dp),
        backgroundColor = colorResource(R.color.colorWhite),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .shadow(
                shape = RoundedCornerShape(22.dp),
                clip = false,
                elevation = 13.dp,
                ambientColor = colorResource(R.color.colorGray8),
                spotColor = colorResource(R.color.colorGray8),
            )
    ) {
        ConstraintLayout {
            val (titleText, messageText, cardImage) = createRefs()

            Text(text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = Typography.h3,
                color = colorResource(R.color.colorBlack),
                modifier = Modifier.constrainAs(titleText) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }.fillMaxWidth(0.80f)
            )

            Text(text = message,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = Typography.caption,
                color = colorResource(R.color.colorGrayText),
                modifier = Modifier
                    .constrainAs(messageText) {
                        top.linkTo(titleText.bottom, margin = 8.dp)
                        start.linkTo(titleText.start)
                    }
                    .padding(end = 16.dp)
                    .fillMaxWidth(0.80f)
            )

            Image(painterResource(R.drawable.ic_orange_arrow_next),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.constrainAs(cardImage) {
                    end.linkTo(parent.end, margin = 34.dp)
                    bottom.linkTo(parent.bottom, margin = 35.dp)
                    top.linkTo(parent.top, margin = 35.dp)
                }
            )
        }
    }
}