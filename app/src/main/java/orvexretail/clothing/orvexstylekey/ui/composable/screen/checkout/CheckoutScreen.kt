package orvexretail.clothing.orvexstylekey.ui.composable.screen.checkout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import orvexretail.clothing.orvexstylekey.ui.state.DataUiState
import orvexretail.clothing.orvexstylekey.ui.viewmodel.CheckoutViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToOrdersScreen: () -> Unit
) {
    val orderState by viewModel.orderState.collectAsStateWithLifecycle()
    val invalidEmail by viewModel.emailInvalidState.collectAsStateWithLifecycle()
    if (orderState is DataUiState.Populated) {
        CheckoutDialog((orderState as DataUiState.Populated).data.orderNumber, onNavigateToOrdersScreen)
    }
    Column(
        modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text("Reserve your order", style = MaterialTheme.typography.headlineMedium)
        Text("We will hold your selection in store for 24 hours.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        OutlinedTextField(viewModel.customerFirstName, viewModel::updateCustomerFirstName, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(viewModel.customerLastName, viewModel::updateCustomerLastName, label = { Text("Address") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = viewModel.customerEmail,
            onValueChange = viewModel::updateCustomerEmail,
            label = { Text("Email") },
            isError = invalidEmail,
            supportingText = { if (invalidEmail) Text("Enter a valid email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = viewModel::placeOrder,
            enabled = viewModel.customerFirstName.isNotBlank() &&
                viewModel.customerLastName.isNotBlank() &&
                viewModel.customerEmail.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Place Order")
        }
    }
}
