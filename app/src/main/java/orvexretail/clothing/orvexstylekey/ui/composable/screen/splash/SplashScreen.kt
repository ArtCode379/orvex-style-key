package orvexretail.clothing.orvexstylekey.ui.composable.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import orvexretail.clothing.orvexstylekey.R
import orvexretail.clothing.orvexstylekey.ui.viewmodel.ZLFJRSplashVM
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: ZLFJRSplashVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit
) {
    val onboarded by viewModel.onboardedState.collectAsStateWithLifecycle()
    val reveal = androidx.compose.runtime.remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        reveal.animateTo(1f, tween(500))
        delay(1000)
        if (onboarded) onNavigateToHomeScreen() else onNavigateToOnboarding()
    }
    Column(modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.product_1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().weight(1.2f)
        )
        Column(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(28.dp).alpha(reveal.value).scale(0.9f + reveal.value * 0.1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painterResource(R.drawable.icon), null, modifier = Modifier.height(96.dp))
            Text(stringResource(R.string.zlfjr_app_name), style = MaterialTheme.typography.headlineLarge)
            Text("Your style, thoughtfully selected", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
