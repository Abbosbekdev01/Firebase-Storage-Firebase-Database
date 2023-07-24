package uz.abbosbek.firebasedatabase_codial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import uz.abbosbek.firebasedatabase_codial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding. root)

        val navController = findNavController(R.id.nav_hos)
        binding.myBottomNavigation.setupWithNavController(navController)

    }
}