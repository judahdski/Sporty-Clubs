package com.d3if0002.sportyclubs.ui.clublist

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3if0002.sportyclubs.R
import com.d3if0002.sportyclubs.adapter.ClubAdapter
import com.d3if0002.sportyclubs.data.SettingDataStore
import com.d3if0002.sportyclubs.data.dataStore
import com.d3if0002.sportyclubs.databinding.FragmentClubListBinding
import com.d3if0002.sportyclubs.db.ClubDb
import kotlinx.coroutines.launch

class ClubListFragment : Fragment() {

    private val viewModel: ClubListViewModel by lazy {
        val db = ClubDb.getInstance(requireContext())
        val factory = ClubListViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[ClubListViewModel::class.java]
    }

    private lateinit var binding: FragmentClubListBinding
    private lateinit var myAdapter: ClubAdapter
    private var isLinearLayout = true
    private lateinit var layoutDataStore: SettingDataStore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubListBinding.inflate(inflater, container, false)

        binding.tombolTambah.setOnClickListener {
            val action = ClubListFragmentDirections.actionClubListFragmentToCreateDataFragment()
            findNavController().navigate(action)
        }

        myAdapter = ClubAdapter()
        binding.recyclerView.adapter = myAdapter

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutDataStore = SettingDataStore(requireContext().dataStore)
        layoutDataStore.preferenceFlow.asLiveData()
            .observe(viewLifecycleOwner) { value ->
                isLinearLayout = value
                chooseLayout()
                activity?.invalidateOptionsMenu()
            }

        viewModel.getClubs().observe(viewLifecycleOwner) {
            if (it.isEmpty())
                binding.dataNullText.visibility = View.VISIBLE

            myAdapter.updateData(it)
        }

        binding.editButton.setOnClickListener {
            val action = ClubListFragmentDirections.actionClubListFragmentToEditFragment()
            findNavController().navigate(action)
        }
    }

    private fun chooseLayout() {
        with(binding) {
            if (isLinearLayout)
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            else recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setIcon(menuItem: MenuItem) {
        menuItem.icon = if (isLinearLayout)
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_grid_layout)
        else ContextCompat.getDrawable(requireContext(), R.drawable.ic_linear_layout)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)
        val layoutBtn = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutBtn)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayout = !isLinearLayout

                lifecycleScope.launch {
                    layoutDataStore.saveLayoutToPreferencesStore(
                        isLinearLayout, requireContext()
                    )
                }

                chooseLayout()
                setIcon(item)
                true
            }
            R.id.goto_tentang_screen -> {
                val action = ClubListFragmentDirections.actionClubListFragmentToTentangFragment()
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}