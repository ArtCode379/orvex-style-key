package orvexretail.clothing.orvexstylekey.di

import orvexretail.clothing.orvexstylekey.ui.viewmodel.AppViewModel
import orvexretail.clothing.orvexstylekey.ui.viewmodel.CartViewModel
import orvexretail.clothing.orvexstylekey.ui.viewmodel.CheckoutViewModel
import orvexretail.clothing.orvexstylekey.ui.viewmodel.ZLFJROnboardingVM
import orvexretail.clothing.orvexstylekey.ui.viewmodel.OrderViewModel
import orvexretail.clothing.orvexstylekey.ui.viewmodel.ProductDetailsViewModel
import orvexretail.clothing.orvexstylekey.ui.viewmodel.ProductViewModel
import orvexretail.clothing.orvexstylekey.ui.viewmodel.ZLFJRSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        ZLFJRSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ZLFJROnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}