package com.arun.trackweather.Ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arun.trackweather.Adapter.HourlyAdapterList
import com.arun.trackweather.Model.ResponseModel
import com.arun.trackweather.Network.ViewModelWeather
import com.arun.trackweather.R
import com.arun.trackweather.Utility.GlideApp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.weather_detail.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var mView: View
    val mainModel by viewModel<ViewModelWeather>()
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
        initView()
        initObservers()
        return mView
    }

    private fun initView() {
        backgroundImage()
        mainModel.getWeatherDetailList()
    }

    private fun initObservers() {
        mainModel.mediatorList.observe(requireActivity(),{

            if (isAdded){

                mView.hourly_temp.apply {
                    adapter = HourlyAdapterList(it.hourly!!, requireContext())
                }
                dataSetup(it)



            }


        })

    }

    private fun dataSetup(it: ResponseModel) {

        //current detail
        mView.current_temp.text = it!!.current.temp.toString()+"\u2103"
        mView.weather_type.text = it!!.current.weather!![0].main
        mView.max_min.text = it!!.daily!![0].temp.max.toString()+"\u2103"+"/"+it!!.daily!![0].temp.min.toString()+"\u2103"

         mView.app_temp.text = it!!.current.temp.toString()+"\u2103"
        mView.wind.text = it!!.current.wind_speed.toString()
        mView.humidity.text = it!!.current.humidity.toString()
        mView.air_pre.text = it!!.current.pressure.toString()
        mView.uv.text = it!!.current.uvi.toString()
        mView.visibility_n.text = it!!.current.visibility.toString()


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

    companion object {
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