package test.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SupplierAccountViewModel constructor(
	// use cases
) : ViewModel() {

    private val _state: MutableStateFlow<AccountState> = MutableStateFlow(AccountState.Loading)
    val state: StateFlow<AccountState>
        get() = _state

    fun onAction(action: AccountAction) {
        when (action) {
            AccountAction.BsdDismissed -> onBsdDismissed()
           // other action handlers
        }
    }

    private fun onBsdDismissed() {
        _state.value = AccountState.Loading
	// proceed with a flow
    }

    sealed interface AccountState {
        object Error: AccountState

        object Loading : AccountState

        // other states
    }
}
