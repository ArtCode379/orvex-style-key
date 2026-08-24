package orvexretail.clothing.orvexstylekey.di

import orvexretail.clothing.orvexstylekey.data.repository.CartRepository
import orvexretail.clothing.orvexstylekey.data.repository.ZLFJROnboardingRepo
import orvexretail.clothing.orvexstylekey.data.repository.OrderRepository
import orvexretail.clothing.orvexstylekey.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        ZLFJROnboardingRepo(
            zlfjrOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}