package com.qingmei2.kodeinsample

import android.app.Application
import com.qingmei2.kodeinsample.coffee.Coffee
import com.qingmei2.kodeinsample.coffee.Kettle
import com.qingmei2.kodeinsample.coffee.electricHeaterModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.AndroidComponentsWeakScope
import org.kodein.di.generic.*

class MyApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        bind() from instance(Logger())

        import(electricHeaterModule)

        bind<Coffee>() with provider { Coffee(instance()) }

        // this is bound in the scope of an activity so any retrieval using the same activity will return the same Kettle instance
        bind<Kettle<Coffee>>() with scoped(AndroidComponentsWeakScope).singleton {
            Kettle<Coffee>(instance(), instance(), instance(), provider())
        }
    }
}