package kr.ac.kumoh.s20201414.w1001intentdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.ac.kumoh.s20201414.w1001intentdata.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        //Toast.makeText(this, intent.getStringExtra("image"), Toast.LENGTH_LONG).show()


        val res = when(intent.getStringExtra("image")){
            "jellyfish" -> R.drawable.jellyfish
            "star" -> R.drawable.star
            else -> R.drawable.ic_launcher_background
        }

        binding.imgGundam.setImageResource(res)
    }
}