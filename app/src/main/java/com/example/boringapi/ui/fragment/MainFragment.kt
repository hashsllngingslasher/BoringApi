package com.example.boringapi.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.boringapi.R
import com.example.boringapi.api.PrefetchService
import com.example.boringapi.api.RetrofitService
import com.example.boringapi.model.Leisure
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun openUrl(url: String) {
        if(url.isEmpty())  {
            Toast.makeText(requireContext(), "Ссылка недоступна", Toast.LENGTH_SHORT).show()
            return
        }
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)

        try {
            PrefetchService.prefetchUrl(url)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
            return
        }
        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_get_task)!!.setOnClickListener {
            fetchActivity()
        }
        view.findViewById<ImageButton>(R.id.btn_search)!!.setOnClickListener {
            val link = view.findViewById<TextView>(R.id.txt_link)?.text.toString()
            Log.d("data", link)
            openUrl(link)
        }
    }

    private fun fetchActivity() {
        val response = RetrofitService.apiInterface.getActivity()

        response.enqueue(object: Callback<Leisure> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Leisure>, response: Response<Leisure>) {
                val responseBody = response.body()
                if(response.isSuccessful && responseBody != null) {
                    view?.findViewById<TextView>(R.id.txt_activity)?.text = responseBody.activity
                    view?.findViewById<TextView>(R.id.txt_accessibility)?.text = responseBody.accessibility.toString()
                    view?.findViewById<TextView>(R.id.txt_participants)?.text = responseBody.participants.toString()
                    view?.findViewById<TextView>(R.id.txt_type)?.text = responseBody.type
                    view?.findViewById<TextView>(R.id.txt_price)?.text = "${responseBody.price} $"
                    view?.findViewById<TextView>(R.id.txt_link)?.text = responseBody.link
                }
            }

            override fun onFailure(call: Call<Leisure>, t: Throwable) {
                Log.d("API", t.toString())
            }
        })
    }
}