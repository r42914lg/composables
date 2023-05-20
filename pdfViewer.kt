package test.test

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.joanzapata.pdfview.PDFView

@Composable
fun RenderPdf(
    backClickAction: () -> Unit,
) {

    BackHandler {
        backClickAction()
    }

    val asset ="mydoc.pdf"
        
    AndroidView(
        factory = { context ->
            PDFView(context, null)
        },
        update = { pdfView ->
            pdfView
                .fromAsset(asset)
                .swipeVertical(true)
                .load()
        },
    )

    Image(
        painterResource(R.drawable.ic_chevron_left),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(start = 8.dp, top = 54.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { backClickAction() }
    )
}
