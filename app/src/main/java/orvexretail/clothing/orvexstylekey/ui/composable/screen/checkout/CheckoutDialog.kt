package orvexretail.clothing.orvexstylekey.ui.composable.screen.checkout

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun CheckoutDialog(orderNumber: String, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onConfirm,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("View Orders")
            }
        },
        title = { Text("Reservation confirmed") },
        text = {
            Text("Order #" + orderNumber + " is confirmed. We look forward to welcoming you in store within the next 24 hours.")
        }
    )
}
