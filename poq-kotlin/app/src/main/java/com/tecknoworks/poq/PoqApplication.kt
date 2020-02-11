package com.tecknoworks.poq

import android.app.Application

/**
 * Created by Mihai Ionescu on 2020-01-31.
 */
class PoqApplication : Application() {
    var poqApplicationComponent = DaggerPoqApplicationComponent.create()
}