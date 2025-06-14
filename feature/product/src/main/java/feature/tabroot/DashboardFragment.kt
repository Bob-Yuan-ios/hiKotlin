package feature.tabroot

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.product.R
import com.example.product.databinding.FragmentDashboardBinding

class DashboardFragment: Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDashboardBinding.bind(view)
        binding.textView.text = "Dashboard"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}