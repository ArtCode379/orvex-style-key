package orvexretail.clothing.orvexstylekey.ui.composable.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import orvexretail.clothing.orvexstylekey.ui.viewmodel.ZLFJRSplashVM
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: ZLFJRSplashVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    val onboardedState by viewModel.onboardedState.collectAsStateWithLifecycle()

    LaunchedEffect(onboardedState) {
        if (onboardedState) {
            onNavigateToHomeScreen()
        } else {
            onNavigateToOnboarding()
        }
    }
}