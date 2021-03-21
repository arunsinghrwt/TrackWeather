package com.arun.trackweather.Ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.arun.trackweather.Adapter.HourlyAdapterList
import com.arun.trackweather.Model.ResponseModel
import com.arun.trackweather.Network.ViewModelWeather
import com.arun.trackweather.R
import com.arun.trackweather.Utility.Constants
import com.arun.trackweather.Utility.GlideApp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.fxn.stash.Stash
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.daily_raw_layout.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.weather_detail.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment()  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var mView: View
    val mainModel by viewModel<ViewModelWeather>()
    private var mCurrentLocation: Location? = null

    private lateinit var locationManager: LocationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_home, container, false)
        getMyLocation()
        initView()
        initObservers()
        return mView
    }

    private fun initView() {
        backgroundImage()
        Handler(Looper.myLooper()!!).postDelayed({
            if (!Stash.getString(Constants.LAT).isNullOrEmpty()) {
                mainModel.getWeatherDetailList()
            } else {
                Stash.put(Constants.LAT, "26.922070")
                Stash.put(Constants.LNG, "75.778885")
                mainModel.getWeatherDetailList()


            }
            if (isAdded){
                getCityName()
            }

        }, 1000)


    }

    private fun getCityName() {

        val geoCoder = Geocoder(requireActivity()!!.applicationContext, Locale.getDefault())
        try {
            val addresses: List<Address> = geoCoder.getFromLocation(Stash.getString(Constants.LAT).toDouble(), Stash.getString(Constants.LNG).toDouble(), 1)
            var add = ""
            if (addresses.size > 0) {
                Log.e("city","-->>"+addresses[0].locality)
                mView.location.text = addresses[0].locality
            }

        } catch (e1: Exception) {
            e1.printStackTrace()
        }
    }

    private fun initObservers() {
        mainModel.mediatorList.observe(requireActivity(), {
            if (isAdded) {
                mView.hourly_temp.apply {
                    adapter = HourlyAdapterList(it.hourly!!, requireContext())
                }
                dataSetup(it)
            }
        })

    }

    private fun dataSetup(it: ResponseModel) {


        mView.current_temp.text = it!!.current.temp.toString()+"\u2103"
        mView.weather_type.text = it!!.current.weather!![0].main
        mView.max_min.text = it!!.daily!![0].temp.max.toString()+"\u2103"+"/"+it!!.daily!![0].temp.min.toString()+"\u2103"
        mView.app_temp.text = it!!.current.temp.toString()+"\u2103"
        mView.wind.text = it!!.current.wind_speed.toString()+"m/sec"
        mView.humidity.text = it!!.current.humidity.toString()+"%"
        mView.air_pre.text = it!!.current.pressure.toString()+"hPa"
        if( it!!.current.uvi.equals(0.0)){
            mView.uv.text = "Very Weak"
        }else{
            mView.uv.text = "Strong"
        }
        mView.visibility_n.text = it!!.current.visibility.toString()+"m"


        //set daily data

        mView.date1.text = date(it.daily!![0].dt)
        mView.date2.text =date(it.daily!![1].dt)
        mView.date3.text =date(it.daily!![2].dt)
        mView.date4.text =date(it.daily!![3].dt)

        mView.day1.text = weekday(it.daily!![0].dt)
        mView.day2.text =weekday(it.daily!![1].dt)
        mView.day3.text =weekday(it.daily!![2].dt)
        mView.day4.text =weekday(it.daily!![3].dt)

        mView.weather_icon1.setImageResource(image(it.daily!![0].weather!![0].main))
        mView.weather_icon2.setImageResource(image(it.daily!![1].weather!![0].main))
        mView.weather_icon3.setImageResource(image(it.daily!![2].weather!![0].main))
        mView.weather_icon4.setImageResource(image(it.daily!![3].weather!![0].main))

        mView.weather_type1.text = it.daily!![0].weather!![0].main
        mView.weather_type2.text =it.daily!![1].weather!![0].main
        mView.weather_type3.text =it.daily!![2].weather!![0].main
        mView.weather_type4.text =it.daily!![3].weather!![0].main


        mView.temp1.text =it!!.daily!![0].temp.max.toString()+"\u2103"+"/"+it!!.daily!![0].temp.min.toString()+"\u2103"
        mView.temp2.text =it!!.daily!![1].temp.max.toString()+"\u2103"+"/"+it!!.daily!![1].temp.min.toString()+"\u2103"
        mView.temp3.text =it!!.daily!![2].temp.max.toString()+"\u2103"+"/"+it!!.daily!![2].temp.min.toString()+"\u2103"
        mView.temp4.text =it!!.daily!![3].temp.max.toString()+"\u2103"+"/"+it!!.daily!![3].temp.min.toString()+"\u2103"


    }
    private fun image(main: String): Int {
        var image = 0
        when(main){
            "Clouds" -> {
                image = R.drawable.cloudy
            }
            "Clear" -> {
                image = R.drawable.ic_outline_wb_sunny_24
            }
            "Rain" -> {
                image = R.drawable.rain
            }
        }
        return image

    }

    private fun date(dt: Int): String? {
        val sdf = SimpleDateFormat("MMM dd")
        val netDate = Date(dt.toLong() * 1000)
        val date =sdf.format(netDate)
        return date;

    }

    private fun weekday(dt: Int): String? {
        val sdf = SimpleDateFormat("EE")
        val netDate = Date(dt.toLong() * 1000)
        val date =sdf.format(netDate)
        return date;

    }


    private fun backgroundImage() {
        val url = "https://i.pinimg.com/originals/78/7c/b4/787cb463a2395515f1e8e4f62a5886d8.gif"
        val glide = GlideApp.with(this)
        glide.load(url)
            .placeholder(ColorDrawable(Color.GRAY))
            .error(ColorDrawable(Color.GRAY))
            .thumbnail(glide.load(url).override(300).transform(CenterCrop()))
            .transform(CenterCrop())
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(mView.myView)

    }

    fun getMyLocation() {

        val locationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                MY_PERMISSIONS_REQUEST_LOCATION
            )
            return
        }
        locationManager = activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    mCurrentLocation = location
                    Stash.put(Constants.LAT, mCurrentLocation!!.latitude.toString())
                    Stash.put(Constants.LNG, mCurrentLocation!!.longitude.toString())
                    Log.e(
                        "myloc",
                        " ------------------------------------" + mCurrentLocation!!.latitude + "   " + mCurrentLocation!!.longitude
                    )
                }

            }
            .addOnFailureListener { e ->
                e.printStackTrace()
                Log.e(
                    "my loc",
                    " - ----++++++++++++++++++++++++++++++++++++++++++++++++++-----------"
                )
            }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getMyLocation()
            } else {
                getMyLocation()
            }

        }
    }
    companion object {
        val MY_PERMISSIONS_REQUEST_LOCATION = 99
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }




}