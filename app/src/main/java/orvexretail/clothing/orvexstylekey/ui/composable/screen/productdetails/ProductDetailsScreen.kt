package orvexretail.clothing.orvexstylekey.ui.composable.screen.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import orvexretail.clothing.orvexstylekey.R
import orvexretail.clothing.orvexstylekey.data.model.Product
import orvexretail.clothing.orvexstylekey.ui.composable.shared.ZLFJRContentWrapper
import orvexretail.clothing.orvexstylekey.ui.composable.shared.ZLFJREmptyView
import orvexretail.clothing.orvexstylekey.ui.state.DataUiState
import orvexretail.clothing.orvexstylekey.ui.viewmodel.ProductDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val productState by viewModel.productDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.observeProductDetails(productId)
    }

    ProductDetailsScreenContent(
        productState = productState,
        modifier = modifier,
        onAddToCart = viewModel::addProductToCart
    )
}

@Composable
private fun ProductDetailsScreenContent(
    productState: DataUiState<Product>,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit,
) {
    Column(modifier = modifier) {

        ZLFJRContentWrapper(
            dataState = productState,

            dataPopulated = {
                val data = (productState as DataUiState.Populated).data

            },

            dataEmpty = {
                ZLFJREmptyView(
                    primaryText = stringResource(R.string.zlfjr_product_details_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}