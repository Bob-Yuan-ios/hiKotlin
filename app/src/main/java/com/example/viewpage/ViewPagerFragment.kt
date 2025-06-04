package com.example.viewpage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hello.R
import com.example.hello.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment: Fragment (R.layout.fragment_view_pager){

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    private val tabTitles = listOf("Home", "Dashboard", "Notifications")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentViewPagerBinding.bind(view)

        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}