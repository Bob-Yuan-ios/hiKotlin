package feature.tabroot

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

import com.example.product.R
import com.example.product.databinding.FragmentHomeBinding


class HomeFragment: Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)
        binding.textView.text = "Home Page"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}