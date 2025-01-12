package com.mdf.test.samir.presentation.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mdf.test.samir.R
import com.mdf.test.samir.data.Resource
import com.mdf.test.samir.databinding.FragmentHomeBinding
import com.mdf.test.samir.domain.model.SortType
import com.mdf.test.samir.presentation.adapters.LoanAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var loanAdapter: LoanAdapter

    val sortBottomSheet by lazy { BottomSheetDialog(requireContext()) }

    private var selectedSortType: SortType? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupMainMenu()
        setupSortBottomSheet()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.loanState.collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.tvEmptyLoans.visibility = View.GONE
                            binding.rvLoan.visibility = View.GONE
                            binding.shimmerFrameLayout.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.shimmerFrameLayout.visibility = View.GONE

                            val data = resource.data
                            if (data.isNullOrEmpty()) {
                                binding.tvEmptyLoans.visibility = View.VISIBLE
                                binding.rvLoan.visibility = View.GONE
                            } else {
                                binding.tvEmptyLoans.visibility = View.GONE
                                binding.rvLoan.visibility = View.VISIBLE
                                loanAdapter.setData(data)
                            }
                        }

                        is Resource.Error -> {
                            binding.tvErrorLoans.visibility = View.VISIBLE
                            binding.shimmerFrameLayout.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        loanAdapter = LoanAdapter { loan ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(loan)
            findNavController().navigate(action)
        }
        binding.apply {
            rvLoan.layoutManager = LinearLayoutManager(requireActivity())
            rvLoan.adapter = loanAdapter
            rvLoan.setHasFixedSize(true)
        }
    }

    private fun setupMainMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_sort -> {
                        sortBottomSheet.show()
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupSortBottomSheet() {
        val parentView = LinearLayout(requireContext())
        val sortView = layoutInflater.inflate(R.layout.bottom_sheet_short, parentView, false)
        sortBottomSheet.setContentView(sortView)

        val radioGroup = sortView.findViewById<RadioGroup>(R.id.rg_sort)
        val applyButton = sortView.findViewById<Button>(R.id.btn_apply_sort)

        when (selectedSortType) {
            SortType.TERM_ASC -> radioGroup.check(R.id.rb_term_low)
            SortType.TERM_DESC -> radioGroup.check(R.id.rb_term_high)
            SortType.RISK_RATING_ASC -> radioGroup.check(R.id.rb_risk_low)
            SortType.RISK_RATING_DESC -> radioGroup.check(R.id.rb_risk_high)
            else -> radioGroup.clearCheck()
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            selectedSortType = when (checkedId) {
                R.id.rb_term_low -> SortType.TERM_ASC
                R.id.rb_term_high -> SortType.TERM_DESC
                R.id.rb_risk_low -> SortType.RISK_RATING_ASC
                R.id.rb_risk_high -> SortType.RISK_RATING_DESC
                else -> null
            }
        }

        applyButton.setOnClickListener {
            selectedSortType?.let { sortType ->
                homeViewModel.getLoansSorted(sortType)
                sortBottomSheet.dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
