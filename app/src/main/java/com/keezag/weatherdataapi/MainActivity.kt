package com.keezag.weatherdataapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.keezag.weatherdataapi.model.OpenWeatherResponse
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    private lateinit var cityEditText: EditText
    private lateinit var countryEditText: EditText
    private lateinit var cityValueTextView: TextView
    private lateinit var weatherValueTextView: TextView
    private lateinit var temperatureValueTextView: TextView
    private lateinit var sensationValueTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.cityEditText = findViewById(R.id.cityEditText)
        this.countryEditText = findViewById(R.id.countryEditText)
        this.cityValueTextView = findViewById(R.id.cityValueTextView)
        this.weatherValueTextView = findViewById(R.id.weatherValueTextView)
        this.temperatureValueTextView = findViewById(R.id.temperatureValueTextView)
        this.sensationValueTextView = findViewById(R.id.sensationValueTextView)

        val button: Button = findViewById(R.id.pesquisarButton)
        button.setOnClickListener {
            getWeather()
        }


    }

    private fun getWeather() {

        val url = "https://api.openweathermap.org/"
        val apiKey = "0223c68b8d2690d19fed1df57956d456"

        var sendCity = cityEditText.text.toString()
        var sendCountry = countryEditText.text.toString()


        val retrofit = Retrofit.Builder()
            .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(OpenWeatherInterface::class.java)
        val call = service.getCurrentWeatherByCity("$sendCity,$sendCountry", "metric", apiKey)


        call.enqueue(object : Callback<OpenWeatherResponse> {
            override fun onFailure(call: Call<OpenWeatherResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "ERROR!", Toast.LENGTH_LONG)
            }

            override fun onResponse(
                call: Call<OpenWeatherResponse>,
                response: Response<OpenWeatherResponse>
            ) {
                if (response?.code() == 200) {
                    val responseWeather = response.body()!!
                    cityValueTextView.text = responseWeather.name
                    weatherValueTextView.text = "${responseWeather.weather[0].main}"
                    temperatureValueTextView.text = "${responseWeather.main.temp}C"
                    sensationValueTextView.text = "${responseWeather.main.feelsLike}C"
                }

            }

        })


    }
}
