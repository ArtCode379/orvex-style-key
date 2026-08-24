package orvexretail.clothing.orvexstylekey.ui.composable.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import orvexretail.clothing.orvexstylekey.R
import orvexretail.clothing.orvexstylekey.data.model.Product
import orvexretail.clothing.orvexstylekey.ui.composable.shared.ZLFJRContentWrapper
import orvexretail.clothing.orvexstylekey.ui.composable.shared.ZLFJREmptyView
import orvexretail.clothing.orvexstylekey.ui.state.DataUiState
import orvexretail.clothing.orvexstylekey.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val productsState by viewModel.productsState.collectAsState()

    HomeContent(
        productsState = productsState,
        modifier = modifier,
        onNavigateToProductDetails = onNavigateToProductDetails,
        onAddProductToCart = viewModel::addToCart,
    )
}

@Composable
private fun HomeContent(
    productsState: DataUiState<List<Product>>,
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: (productId: Int) -> Unit,
    onAddProductToCart: (productId: Int) -> Unit,
) {
    Column(modifier = modifier) {

        ZLFJRContentWrapper(
            dataState = productsState,

            dataPopulated = {
                val data = (productsState as DataUiState.Populated).data
            },

            dataEmpty = {
                ZLFJREmptyView(
                    primaryText = stringResource(R.string.zlfjr_products_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}