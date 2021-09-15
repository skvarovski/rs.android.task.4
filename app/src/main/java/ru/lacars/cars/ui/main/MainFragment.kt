package ru.lacars.cars.ui.main


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.observeOn
import ru.lacars.cars.R
import ru.lacars.cars.adapter.CarsAdapter
import ru.lacars.cars.adapter.SwipeHelper
import ru.lacars.cars.databinding.MainFragmentBinding
import ru.lacars.cars.repository.PreferencesOrder
import ru.lacars.cars.ui.add.AddFragment

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private val carAdapter: CarsAdapter = CarsAdapter()
    //private val preferencesOrder: PreferencesOrder? = context?.let { PreferencesOrder(it) }


    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = this.requireActivity().actionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setDisplayShowHomeEnabled(false)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {

        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.carsList.adapter = carAdapter
        SwipeHelper(viewModel::delete).attachToRecyclerView(binding.carsList)
        binding.fabButton.setOnClickListener {
            openAddFragment()
        }




        viewModel.updateList().asLiveData().observe(this.viewLifecycleOwner) { cars ->
            carAdapter.submitList(cars)
                //Log.d("TEST","isRoom = ${viewModel.useRoom}")
        }

        //в случае переключения сортировки
        viewModel.getPreferences().observe(this.viewLifecycleOwner) {
            viewModel.updateList().asLiveData().observe(this.viewLifecycleOwner) { cars ->
                carAdapter.submitList(cars)
                Log.d("TEST","List update by SORT preference")
            }
        }



    }




    private fun openAddFragment() {
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container,AddFragment.newInstance())
            .commit()


    }


    companion object {
        fun newInstance() = MainFragment()
    }


}


