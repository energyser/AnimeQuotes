package com.example.animequotes.presentation.main

import android.app.AlertDialog
import android.graphics.ColorFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.animequotes.R
import com.example.animequotes.databinding.FragmentMainBinding
import com.example.animequotes.presentation.model.QuoteType

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.randomQuotesButton.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionSearchFragmentToDetailsFragment(
                    QuoteType.RANDOM.type,
                    ""
                )
            )
        }

        binding.quotesByTitleButton.setOnClickListener {
            showAlertDialog(QuoteType.BY_TITLE)
        }

        binding.quotesByCharacterButton.setOnClickListener {
            showAlertDialog(QuoteType.BY_CHARACTER)
        }
    }

    private fun showAlertDialog(quoteType: QuoteType) {
        val editText = EditText(context)
        editText.background?.apply {
            setTint(ContextCompat.getColor(requireContext(), R.color.purple_500))
        }
        val paddingTopBottom = dpToPx(8)
        val paddingLeftRight = dpToPx(8)
        editText.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom)

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Enter key word:")
        builder.setView(editText)

        builder.setPositiveButton("Ok") { _, _ ->
            val keyWord = editText.text.toString()
            findNavController().navigate(
                MainFragmentDirections.actionSearchFragmentToDetailsFragment(
                    quoteType.type,
                    keyWord
                )
            )
        }
        builder.setNegativeButton("Cancel", null)
        val dialog = builder.create()

        dialog.show()
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * requireContext().resources.displayMetrics.density).toInt()
    }


}