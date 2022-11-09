package kr.ac.kumoh.s20201414.w1001intentdata

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.kumoh.s20201414.w1001intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    companion object {
        const val keyName = "image"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.btnGundam.setOnClickListener(this)
        binding.btnZaku.setOnClickListener(this)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode != RESULT_OK)
                return@registerForActivityResult

            val result = it.data?.getIntExtra(ImageActivity.resultName, ImageActivity.NONE)
            val str = when (result) {
                ImageActivity.LIKE -> "좋아요"
                ImageActivity.DISLIKE -> "싫어요"
                else -> ""
            }
            val image = it.data?.getStringExtra(ImageActivity.imageName)

            when (image) {
                "jellyfish" -> binding.btnGundam.text = "건담 ($str)"
                "star" -> binding.btnZaku.text = "자쿠 ($str)"
            }
        }
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, ImageActivity::class.java)
        val value = when (v?.id){
            binding.btnGundam.id -> "jellyfish"
            binding.btnZaku.id -> "star"
            else -> return
        }
        intent.putExtra(keyName, value)
        launcher.launch(intent)
    }
}