package com.qingmei2.kodeinsample.coffee

import com.qingmei2.kodeinsample.Logger
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class Thermosiphon(private val log: Logger, private val heater: Heater) : Pump {

    init {
        log.log("<Creating Thermosiphon>")
    }

    override fun pumpWater() {
        if (heater.isHot)
            log.log("=> => pumping => =>")
        else
            log.log("!!! water is cold !!!")
    }
}

val thermosiphonModule = Kodein.Module("Thermosiphon") {
    bind<Pump>() with singleton { Thermosiphon(instance(), instance()) }
}
