package orvexretail.clothing.orvexstylekey.di

import orvexretail.clothing.orvexstylekey.data.datastore.ZLFJROnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { ZLFJROnboardingPrefs(androidContext()) }
}