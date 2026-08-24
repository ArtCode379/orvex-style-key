package orvexretail.clothing.orvexstylekey.ui.composable.screen.order

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import orvexretail.clothing.orvexstylekey.data.entity.OrderEntity
import orvexretail.clothing.orvexstylekey.ui.state.DataUiState
import orvexretail.clothing.orvexstylekey.ui.theme.OrvexSuccess
import orvexretail.clothing.orvexstylekey.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(modifier: Modifier = Modifier, viewModel: OrderViewModel = koinViewModel()) {
    val state by viewModel.ordersState.collectAsState()
    val orders = (state as? DataUiState.Populated)?.data.orEmpty().sortedByDescending { it.timestamp }
    if (orders.isEmpty()) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No orders yet", style = MaterialTheme.typography.titleLarge)
        }
    } else {
        LazyColumn(
            modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(orders) { order -> OrderCard(order) }
        }
    }
}

@Composable
private fun OrderCard(order: OrderEntity) {
    Card {
        Column(Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Order #" + order.orderNumber, style = MaterialTheme.typography.titleMedium)
                Surface(color = OrvexSuccess.copy(alpha = 0.12f)) {
                    Text("Completed", color = OrvexSuccess, modifier = Modifier.padding(6.dp))
                }
            }
            Text(order.timestamp.toLocalDate().toString(), color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(order.description)
            Text("£%.2f".format(order.price), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleLarge)
        }
    }
}
