package com.example.boringapi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.boringapi.R
import com.example.boringapi.adapter.ViewPagerAdapter
import com.example.boringapi.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root

        viewPager = view.findViewById(R.id.view_pager)
        adapter = ViewPagerAdapter(requireActivity())
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                resetCircleColors()
                when (position) {
                    0 -> binding.circle1.setImageResource(R.drawable.circle_green)
                    1 -> binding.circle2.setImageResource(R.drawable.circle_green)
                    2 -> binding.circle3.setImageResource(R.drawable.circle_green)
                }
            }
        })
        return view
    }

    private fun resetCircleColors() {
        binding.circle1.setImageResource(R.drawable.circle_white)
        binding.circle2.setImageResource(R.drawable.circle_white)
        binding.circle3.setImageResource(R.drawable.circle_white)
    }
}