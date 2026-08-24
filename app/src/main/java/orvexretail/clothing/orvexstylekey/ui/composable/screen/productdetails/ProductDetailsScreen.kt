package orvexretail.clothing.orvexstylekey.ui.composable.screen.productdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import orvexretail.clothing.orvexstylekey.R
import orvexretail.clothing.orvexstylekey.data.model.Product
import orvexretail.clothing.orvexstylekey.ui.composable.shared.ZLFJRContentWrapper
import orvexretail.clothing.orvexstylekey.ui.state.DataUiState
import orvexretail.clothing.orvexstylekey.ui.viewmodel.ProductDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel()
) {
    val productState by viewModel.productDetailsState.collectAsState()
    LaunchedEffect(productId) { viewModel.observeProductDetails(productId) }
    ProductDetailsContent(productState, modifier, viewModel::addProductToCart)
}

@Composable
private fun ProductDetailsContent(
    productState: DataUiState<Product>,
    modifier: Modifier,
    onAddToCart: () -> Unit
) {
    var cartAdded by remember { mutableStateOf(false) }
    LaunchedEffect(cartAdded) {
        if (cartAdded) {
            delay(2000)
            cartAdded = false
        }
    }
    Box(modifier.fillMaxSize()) {
        ZLFJRContentWrapper(
            dataState = productState,
            dataPopulated = {
                val product = (productState as DataUiState.Populated).data
                Column(
                    modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(bottom = 96.dp)
                ) {
                    AsyncImage(
                        model = product.imageUrl,
                        contentDescription = product.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(360.dp)
                    )
                    Column(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Text(stringResource(product.category.titleRes).uppercase(), color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.labelLarge)
                        Text(product.title, style = MaterialTheme.typography.headlineMedium)
                        Text("£%.2f".format(product.price), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleLarge)
                        HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                        Text(product.description, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyLarge)
                        Text("Considered details, versatile proportions and a polished finish make this an enduring addition to your wardrobe.", style = MaterialTheme.typography.bodyMedium)
                    }
                }
                Surface(
                    shadowElevation = 10.dp,
                    modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("£%.2f".format(product.price), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleLarge)
                        Spacer(Modifier.width(18.dp))
                        Button(
                            onClick = {
                                onAddToCart()
                                cartAdded = true
                            },
                            modifier = Modifier.weight(1f).height(52.dp)
                        ) {
                            Text(stringResource(R.string.zlfjr_button_add_to_cart_label))
                        }
                    }
                }
            },
            dataEmpty = {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(stringResource(R.string.zlfjr_product_details_state_empty_primary_text))
                }
            }
        )
        AnimatedVisibility(
            visible = cartAdded,
            enter = slideInVertically { it },
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Surface(modifier = Modifier.fillMaxWidth(), tonalElevation = 8.dp) {
                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.width(10.dp))
                    Text("Added to cart", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
