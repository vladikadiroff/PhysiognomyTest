package ru.vladikadiroff.physiognomy.ui.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_physignomy.*
import ru.vladikadiroff.physiognomy.R
import ru.vladikadiroff.physiognomy.extensions.setupWithNavController


class PhysiognomyActivity : AppCompatActivity(){

    private var currentNavController: LiveData<NavController>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physignomy)
        setSupportActionBar(toolbar)

        // Устанавливаем навигацию при первом запуске
        if (savedInstanceState == null) setupBottomNavigationBar()


    }

    // Возвращаем состояние BottomNavigationBar
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar(){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val navGraphIds = listOf(R.navigation.navigation_test,
            R.navigation.navigation_shop, R.navigation.navigation_about)


        // Устанавливаем bottomNavigationView из списка графов с помошью Google Navigation Extensions

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navHostContainer,
            intent = intent
        )

        // Подписываем ActionBar на каждое изменение bottomNavigationView

        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })

        currentNavController = controller

    }


    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }


}
