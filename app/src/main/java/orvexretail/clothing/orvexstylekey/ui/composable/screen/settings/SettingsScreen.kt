package orvexretail.clothing.orvexstylekey.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        Text("About", style = MaterialTheme.typography.headlineMedium)
        Card {
            Column(Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SettingRow("Company", "ORVEX RETAIL LTD")
                SettingRow("Application", "Orvex Style Key")
                SettingRow("Version", "1.0")
            }
        }
        Text("Support", style = MaterialTheme.typography.titleLarge)
        Text("Questions about a reservation or collection? Our team is ready to help.")
        Button(
            onClick = {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://orvexretail.surf")))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Customer Support")
        }
    }
}

@Composable
private fun SettingRow(label: String, value: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.titleMedium)
    }
}
