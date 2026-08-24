package orvexretail.clothing.orvexstylekey.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import orvexretail.clothing.orvexstylekey.data.repository.ZLFJROnboardingRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ZLFJROnboardingVM(
    private val onboardingRepository: ZLFJROnboardingRepo,
) : ViewModel() {
    private val _onboardingSetState = MutableStateFlow(false)
    val onboardingSetState: StateFlow<Boolean>
        get() = _onboardingSetState.asStateFlow()

    fun setOnboarded() {
        viewModelScope.launch {
            onboardingRepository.setOnboardingState(true)
            _onboardingSetState.update { true }
        }
    }
}