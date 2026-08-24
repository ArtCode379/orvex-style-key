package orvexretail.clothing.orvexstylekey.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import orvexretail.clothing.orvexstylekey.data.repository.ZLFJROnboardingRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ZLFJRSplashVM(
    private val onboardingRepository: ZLFJROnboardingRepo,
) : ViewModel() {
    val onboardedState: StateFlow<Boolean> =
        onboardingRepository.observeOnboardingState()
            .map { it == true }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = false
            )

}