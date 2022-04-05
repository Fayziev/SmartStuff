package uz.anorgroup.smartstufftask.ui.screens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.smartstufftask.R
import uz.anorgroup.smartstufftask.databinding.ScreenMainBinding
import uz.anorgroup.smartstufftask.ui.viewmodels.impl.MainViewModelImpl
import uz.anorgroup.smartstufftask.utils.scope
import uz.anorgroup.smartstufftask.utils.toInt

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel by viewModels<MainViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        generateButton.setOnClickListener {
            viewModel.generateMap(textHeightCount.toInt(), textWidthCount.toInt())
        }

        myView.setSelectRectPosition { x, y ->
            Toast.makeText(requireContext(), "($x, $y)", Toast.LENGTH_SHORT).show()
            viewModel.clickRectPos(x, y, Handler(Looper.getMainLooper()))
        }
        viewModel.mapLiveData.observe(viewLifecycleOwner, mapObserver)
        viewModel.changeMapLiveData.observe(viewLifecycleOwner, changeMapObserver)

    }

    private val mapObserver = Observer<Array<Array<Int>>> {
        binding.apply {
            myView.verticalCount = binding.textWidthCount.toInt()
            myView.horizontalCount = binding.textHeightCount.toInt()
            myView.updateMap(it)
        }
    }

    private val changeMapObserver = Observer<Array<Array<Int>>> {
        binding.myView.updateMap(it)
    }
}

