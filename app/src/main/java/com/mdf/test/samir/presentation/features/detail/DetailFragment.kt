package com.mdf.test.samir.presentation.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mdf.test.samir.databinding.FragmentDetailBinding
import com.mdf.test.samir.presentation.adapters.DocumentAdapter
import com.mdf.test.samir.presentation.adapters.InstallmentAdapter
import com.mdf.test.samir.utils.toUsd
import java.util.Locale

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var documentAdapter: DocumentAdapter
    private lateinit var installmentAdapter: InstallmentAdapter

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRepaymentScheduleRecyclerView()
        setupDocumentRecyclerView()

        binding.apply {
            tvBorrowerName.text = args.loan.borrower.name
            tvEmail.text = args.loan.borrower.email
            tvCreditScore.text = String.format(
                Locale.getDefault(), "%,d", args.loan.borrower.creditScore
            )

            tvCollateralType.text = args.loan.collateral.type
            tvCollateralValue.text = args.loan.collateral.value.toUsd()

            installmentAdapter.setItems(args.loan.repaymentSchedule.installments)

            args.loan.documents.let { documents ->
                if (!documents.isNullOrEmpty()) {
                    rvDocuments.visibility = View.VISIBLE
                    tvNoDocuments.visibility = View.GONE
                    documentAdapter.setItems(documents)
                } else {
                    rvDocuments.visibility = View.GONE
                    tvNoDocuments.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupRepaymentScheduleRecyclerView() {
        installmentAdapter = InstallmentAdapter()

        binding.apply {
            rvRepaymentSchedule.layoutManager = LinearLayoutManager(requireActivity())
            rvRepaymentSchedule.adapter = installmentAdapter
            rvRepaymentSchedule.setHasFixedSize(true)
        }
    }

    private fun setupDocumentRecyclerView() {
        documentAdapter = DocumentAdapter { document ->
            val action =
                DetailFragmentDirections.actionDetailFragmentToDocumentFragment(url = document.url)
            findNavController().navigate(action)
        }

        binding.apply {
            rvDocuments.layoutManager = LinearLayoutManager(requireActivity())
            rvDocuments.adapter = documentAdapter
            rvDocuments.setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}