package orvexretail.clothing.orvexstylekey.data.repository

import orvexretail.clothing.orvexstylekey.data.datastore.ZLFJROnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ZLFJROnboardingRepo(
    private val zlfjrOnboardingStoreManager: ZLFJROnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return zlfjrOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            zlfjrOnboardingStoreManager.setOnboardedState(state)
        }
    }
}