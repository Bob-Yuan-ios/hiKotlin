package feature.tabroot

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.product.R
import com.example.product.databinding.FragmentNotificationBinding


class NotificationsFragment: Fragment(R.layout.fragment_notification) {

    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentNotificationBinding.bind(view)
        binding.textView.text = "Notifications"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}