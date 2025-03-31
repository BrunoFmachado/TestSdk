package com.example.presentation.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.smartpossdk.R
import com.example.smartpossdk.databinding.FragmentPaymentBinding
import com.example.presentation.components.PaymentKeyboardHandler
import com.example.ui.extensions.CurrencyFormatter
import com.example.ui.extensions.applyFadeInAnim
import com.example.presentation.state.UiState
import com.example.presentation.viewModel.PaymentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PaymentFragment : Fragment(R.layout.fragment_payment) {

    private val viewModel: PaymentViewModel by viewModels()
    private lateinit var keyboardHandler: PaymentKeyboardHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPaymentBinding.bind(view)
        val copyButton = binding.btnCopyLink

        view.applyFadeInAnim(requireContext())

        val toolbarTitle = view.findViewById<TextView>(R.id.toolbar_title)
        toolbarTitle.text = getString(R.string.receber_pagamento)

        keyboardHandler = PaymentKeyboardHandler { updated ->
            binding.tvAmount.text = CurrencyFormatter.formatFromCents(updated)
        }

        getKeyboardButtons(view).forEach { button ->
            button.setOnClickListener {
                val text = (button as? TextView)?.text.toString()
                keyboardHandler.onKeyPressed(text)
            }
        }

        binding.btnDelete.setOnClickListener {
            keyboardHandler.onDelete()
        }

        binding.btnContinue.setOnClickListener {
            val rawDescription = binding.etDescription.text.toString().trim()
            val description = rawDescription.ifBlank { getString(R.string.descricao_padrao) }
            val amount = keyboardHandler.getRawValue().toFloatOrNull()?.div(100)

            if (amount == null || amount == 0f) {
                binding.tvStatus.text = getString(R.string.erro_valor_invalido)
                return@setOnClickListener
            }

            viewModel.createPreference(description, amount)
        }

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { state ->
                    when (state) {
                        is UiState.Loading -> {
                            binding.tvStatus.text = getString(R.string.criando_preferencia)
                            copyButton.visibility = View.GONE
                        }

                        is UiState.Success -> {
                            val link = state.data as? String ?: return@collectLatest
                            copyButton.visibility = View.VISIBLE

                            copyButton.setOnClickListener {
                                val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("link", link)
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(requireContext(), getString(R.string.link_copiado), Toast.LENGTH_SHORT).show()
                            }

                            AlertDialog.Builder(requireContext())
                                .setTitle("Atenção")
                                .setMessage("Deseja ir para o site?")
                                .setPositiveButton("Sim") { _, _ ->
                                    startActivity(Intent(Intent.ACTION_VIEW, link.toUri()))
                                }
                                .setNegativeButton("Não", null)
                                .show()
                        }

                        is UiState.Error -> {
                            binding.tvStatus.text = getString(R.string.erro_gerar_link, state.message)
                            copyButton.visibility = View.GONE
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun getKeyboardButtons(view: View): List<View> {
        val ids = listOf(
            R.id.btnZero, R.id.btnUm, R.id.btnDois, R.id.btnTres, R.id.btnQuatro,
            R.id.btnCinco, R.id.btnSeis, R.id.btnSete, R.id.btnOito, R.id.btnNove,
            R.id.btnDoubleZero
        )
        return ids.map(view::findViewById)
    }
}
