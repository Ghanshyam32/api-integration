package com.ghanshyam.gameofthronesquotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ghanshyam.gameofthronesquotes.databinding.ActivityMainBinding
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            onClick()
        }
    }

    fun onClick() {
        val queue = Volley.newRequestQueue(this@MainActivity)
        val url = "https://api.gameofthronesquotes.xyz/v1/random"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val jsonObject = response.getJSONObject("character")
                    val quote = response.getString("sentence")
                    val names = jsonObject.getString("name")
                    binding.quote.setText(quote)
                    binding.name.setText(names)
                } catch (e: JSONException) {
                    throw RuntimeException(e)
                }
            }) { }
        queue.add(jsonObjectRequest)
    }

}
