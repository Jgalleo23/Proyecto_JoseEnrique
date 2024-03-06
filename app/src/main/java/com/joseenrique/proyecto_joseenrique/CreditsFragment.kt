package com.joseenrique.proyecto_joseenrique

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.joseenrique.proyecto_joseenrique.databinding.FragmentCreditsBinding
import com.joseenrique.proyecto_joseenrique.databinding.FragmentMenuBinding

class CreditsFragment : Fragment() {
    private var _binding: FragmentCreditsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreditsBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.credits)

        val savedUsername = requireContext().getSharedPreferences("savedUsername", Context.MODE_PRIVATE)
        val username = savedUsername.getString("username", "")
        binding.btContact.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("josegjoseg02@proton.me")) // recipient email
                putExtra(Intent.EXTRA_SUBJECT, "Subject") // email subject
                putExtra(Intent.EXTRA_TEXT, "Email body") // email body
            }

            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
        return binding.root
    }

}