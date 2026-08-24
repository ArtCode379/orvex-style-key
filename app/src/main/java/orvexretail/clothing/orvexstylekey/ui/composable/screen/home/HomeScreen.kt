package orvexretail.clothing.orvexstylekey.ui.composable.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import orvexretail.clothing.orvexstylekey.R
import orvexretail.clothing.orvexstylekey.data.model.Product
import orvexretail.clothing.orvexstylekey.data.model.ProductCategory
import orvexretail.clothing.orvexstylekey.ui.composable.shared.ZLFJRContentWrapper
import orvexretail.clothing.orvexstylekey.ui.state.DataUiState
import orvexretail.clothing.orvexstylekey.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit
) {
    val productsState by viewModel.productsState.collectAsState()
    HomeContent(productsState, modifier, onNavigateToProductDetails)
}

@Composable
private fun HomeContent(
    productsState: DataUiState<List<Product>>,
    modifier: Modifier,
    onNavigateToProductDetails: (Int) -> Unit
) {
    ZLFJRContentWrapper(
        dataState = productsState,
        modifier = modifier.fillMaxSize(),
        dataPopulated = {
            val products = (productsState as DataUiState.Populated).data
            var selected by remember { mutableStateOf<ProductCategory?>(null) }
            val filtered = products.filter { selected == null || it.category == selected }
            LazyColumn(
                contentPadding = PaddingValues(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                item { HeroProduct(products.first(), onNavigateToProductDetails) }
                item {
                    Text(
                        text = "CURATED FOR YOU",
                        modifier = Modifier.padding(horizontal = 20.dp),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item { CategoryChip("All", selected == null) { selected = null } }
                        items(ProductCategory.entries) { category ->
                            CategoryChip(stringResource(category.titleRes), selected == category) {
                                selected = category
                            }
                        }
                    }
                }
                items(filtered.chunked(2)) { rowProducts ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        rowProducts.forEachIndexed { index, product ->
                            ProductCard(
                                product = product,
                                tall = index == 1,
                                modifier = Modifier.weight(1f),
                                onClick = { onNavigateToProductDetails(product.id) }
                            )
                        }
                        if (rowProducts.size == 1) {
                            Box(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        },
        dataEmpty = {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.zlfjr_products_state_empty_primary_text))
            }
        }
    )
}

@Composable
private fun HeroProduct(product: Product, onNavigate: (Int) -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth().height(250.dp).clickable { onNavigate(product.id) }
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier.fillMaxSize().background(
                Brush.verticalGradient(listOf(Color.Transparent, Color(0xCC2B202D)))
            )
        )
        Column(modifier = Modifier.align(Alignment.BottomStart).padding(22.dp)) {
            Text("THE NEW EDIT", color = Color.White, style = MaterialTheme.typography.labelLarge)
            Text(product.title, color = Color.White, style = MaterialTheme.typography.headlineLarge)
            Text("£%.2f".format(product.price), color = Color.White, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun CategoryChip(label: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        color = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
        contentColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
        border = BorderStroke(1.dp, if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline)
    ) {
        Text(label, modifier = Modifier.padding(horizontal = 16.dp, vertical = 9.dp), style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
private fun ProductCard(product: Product, tall: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(if (tall) 240.dp else 180.dp).clip(RoundedCornerShape(4.dp))
        )
        Text(product.title, modifier = Modifier.padding(top = 10.dp), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        Text(stringResource(product.category.titleRes), color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
        Text("£%.2f".format(product.price), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleMedium)
    }
}
