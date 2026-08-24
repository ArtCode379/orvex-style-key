package orvexretail.clothing.orvexstylekey.ui.composable.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import orvexretail.clothing.orvexstylekey.R
import orvexretail.clothing.orvexstylekey.ui.viewmodel.ZLFJROnboardingVM
import org.koin.androidx.compose.koinViewModel

private data class Page(val title: String, val description: String, val image: Int)

private val pages = listOf(
    Page("Discover your signature", "Explore an edit of modern clothing and accessories chosen for effortless, expressive dressing.", R.drawable.product_2),
    Page("Build the whole look", "Filter by category, compare considered details and save your favourites to one simple bag.", R.drawable.product_3),
    Page("Reserve with confidence", "Confirm your selection in seconds and collect it from our store within 24 hours.", R.drawable.product_4)
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: ZLFJROnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit
) {
    val completed by viewModel.onboardingSetState.collectAsState()
    val pager = rememberPagerState { pages.size }
    val scope = rememberCoroutineScope()
    LaunchedEffect(completed) { if (completed) onNavigateToHomeScreen() }
    Column(modifier.fillMaxSize()) {
        HorizontalPager(state = pager, modifier = Modifier.weight(1f)) { index ->
            val page = pages[index]
            Box(Modifier.fillMaxSize()) {
                Image(
                    painterResource(page.image),
                    page.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface.copy(alpha = 0.93f)).padding(26.dp)
                ) {
                    Text(page.title, style = MaterialTheme.typography.headlineMedium)
                    Text(page.description, modifier = Modifier.padding(top = 10.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                pages.indices.forEach { index ->
                    Box(
                        Modifier.size(if (pager.currentPage == index) 18.dp else 7.dp, 7.dp)
                            .clip(CircleShape)
                            .background(if (pager.currentPage == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline)
                    )
                }
            }
            Button(
                onClick = {
                    if (pager.currentPage == pages.lastIndex) {
                        viewModel.setOnboarded()
                    } else {
                        scope.launch { pager.animateScrollToPage(pager.currentPage + 1) }
                    }
                }
            ) {
                Text(if (pager.currentPage == pages.lastIndex) "Get Started" else "Next")
            }
        }
    }
}
