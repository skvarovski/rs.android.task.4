package ru.lacars.cars.ui.main


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.lacars.cars.R
import ru.lacars.cars.adapter.CarsAdapter
import ru.lacars.cars.adapter.SwipeHelper
import ru.lacars.cars.databinding.MainFragmentBinding
import ru.lacars.cars.repository.room.Car
import ru.lacars.cars.ui.add.AddFragment

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private val adapter: CarsAdapter? get() = views { carsList.adapter as? CarsAdapter }
    private var binding: MainFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = this.requireActivity().actionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setDisplayShowHomeEnabled(false)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = MainFragmentBinding.inflate(inflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        views {
            carsList.adapter = CarsAdapter()
            SwipeHelper(viewModel::delete).attachToRecyclerView(carsList)
            /*addButton.setOnClickListener {
                saveNote()
            }*/

            fabButton.setOnClickListener {
                openAddFragment()
            }

        }

        viewModel.cars.onEach(::renderCars).launchIn(lifecycleScope)
        //viewModel.newCaption.onEach(::renderCaption).launchIn(lifecycleScope)

    }


    private fun openAddFragment() {
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container,AddFragment.newInstance())
            .commit()


    }


    private fun saveNote() {
        views {
            //val noteText = addNoteEditText.text.toString().takeIf { it.isNotBlank() } ?: return@views
            //viewModel.save(noteText)
            //addNoteEditText.setText("")
        }
    }

    /*private fun renderCaption(caption: String) {
        views { captionTextView.text = caption }
    }*/

    private fun renderCars(cars: List<Car>) {
        Log.d("TEST","Render $cars")
        adapter?.submitList(cars)

    }

    private fun <T> views(block: MainFragmentBinding.() -> T): T? = binding?.block()


    companion object {
        fun newInstance() = MainFragment()
    }

}


