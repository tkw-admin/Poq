package com.tecknoworks.poq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tecknoworks.poq.data.RepositoriesComponent
import com.tecknoworks.poq.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    lateinit var repositoriesComponent: RepositoriesComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        repositoriesComponent = (applicationContext as PoqApplication).poqApplicationComponent.repositoriesComponent().build()
        repositoriesComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}
