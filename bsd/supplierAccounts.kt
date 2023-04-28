package test.test

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SupplierAccounts(
    supplierAccountViewModel: SupplierAccountViewModel,
    backClickAction: () -> Unit = {},
) {
    val state by supplierAccountViewModel.state.collectAsState()

    var bsdWasOpen by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )


    fun conditionalBsdOpen() {
        if (modalSheetState.isVisible || !bsdWasOpen) {
            coroutineScope.launch { modalSheetState.show() }
            bsdWasOpen = true
        }
    }

    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch {
            modalSheetState.hide()
        }
    }

    if (modalSheetState.isVisible) {
        DisposableEffect(Unit) {
            onDispose {
                supplierAccountViewModel.onAction(AccountAction.BsdDismissed)
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxHeight(0.93f)
            ) {

                Image(
                    painterResource(R.drawable.ic_resize_indicator),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )

                when (state) {
                    SupplierAccountViewModel.AccountState.Error -> {
                        ShowToast("Some error")
                    }
                    SupplierAccountViewModel.AccountState.Loading -> {
                        coroutineScope.launch { modalSheetState.hide() }
                    }
                    is SupplierAccountViewModel.AccountState.ShowAccountList -> {
                        bsdWasOpen = false
                    }
                    is SupplierAccountViewModel.AccountState.ShowSupplierList -> {
                        bsdWasOpen = false
                    }
                    is SupplierAccountViewModel.AccountState.CreateAccount -> {
                        // some components here (bsd screen)
                        conditionalBsdOpen()
                    }
                }
            }
        }
    ) {
        Scaffold {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
            ) {
		// rest off components (below screen)
            }
        }
    }
}

/**
 * Actions (events) towards view model
 */

sealed interface AccountAction {
    object BsdDismissed : AccountAction
	// other actions
}
