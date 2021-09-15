package ru.lacars.cars.ui.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.lacars.cars.databinding.AddFragmentBinding
import ru.lacars.cars.repository.room.Car

class AddFragment : Fragment() {

    private var _binding: AddFragmentBinding? = null
    private val binding: AddFragmentBinding get() = requireNotNull(_binding)
    private lateinit var viewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddViewModel::class.java)

        val actionBar = this.requireActivity().actionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true);

        with(binding) {
            addButton.setOnClickListener {
                saveCar()
                closeFragment()
            }

        }

    }

    private fun closeFragment()
    {
        parentFragmentManager.popBackStack()
    }

    private fun saveCar() {
        val name = binding.nameEditText.text.toString().takeIf { it.isNotBlank() } ?: return
        val color = binding.colorEditText.text.toString().takeIf { it.isNotBlank() } ?: return
        val year = binding.yearEditText.text.toString().toIntOrNull() ?: return

        viewModel.save(Car(0,name,color,year))





    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    companion object {
        fun newInstance() = AddFragment()
    }
}