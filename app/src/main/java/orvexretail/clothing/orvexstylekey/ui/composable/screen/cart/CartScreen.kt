package orvexretail.clothing.orvexstylekey.ui.composable.screen.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import orvexretail.clothing.orvexstylekey.ui.state.DataUiState
import orvexretail.clothing.orvexstylekey.ui.viewmodel.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit
) {
    val state by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val total by viewModel.totalPrice.collectAsStateWithLifecycle()
    val cartItems = (state as? DataUiState.Populated)?.data.orEmpty()
    if (cartItems.isEmpty()) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Your bag is empty", style = MaterialTheme.typography.headlineMedium)
                Text("Start Shopping", color = MaterialTheme.colorScheme.primary)
            }
        }
        return
    }
    Column(modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(cartItems) { item ->
                Card {
                    Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(item.productImageUrl, item.productTitle, contentScale = ContentScale.Crop, modifier = Modifier.size(72.dp))
                        Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                            Text(item.productTitle, style = MaterialTheme.typography.titleMedium)
                            Text("£%.2f".format(item.productPrice), color = MaterialTheme.colorScheme.primary)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                TextButton(onClick = {
                                    if (item.quantity == 1) {
                                        viewModel.deleteFromCart(item.productId)
                                    } else {
                                        viewModel.decrementItemInCart(item.productId)
                                    }
                                }) {
                                    Text("−")
                                }
                                Text(item.quantity.toString())
                                TextButton(onClick = { viewModel.incrementProductInCart(item.productId) }) {
                                    Text("+")
                                }
                            }
                        }
                        IconButton(onClick = { viewModel.deleteFromCart(item.productId) }) {
                            Icon(Icons.Default.DeleteOutline, "Remove")
                        }
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Total", style = MaterialTheme.typography.titleLarge)
            Text("£%.2f".format(total), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleLarge)
        }
        Button(onClick = onNavigateToCheckoutScreen, modifier = Modifier.fillMaxWidth().padding(20.dp)) {
            Text("Proceed to Checkout")
        }
    }
}
