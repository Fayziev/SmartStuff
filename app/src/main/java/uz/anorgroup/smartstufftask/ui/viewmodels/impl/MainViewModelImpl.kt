package uz.anorgroup.smartstufftask.ui.viewmodels.impl

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.smartstufftask.domain.AppRepository
import uz.anorgroup.smartstufftask.ui.viewmodels.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(private val repository: AppRepository) : ViewModel(),
    MainViewModel {
    override val mapLiveData: MutableLiveData<Array<Array<Int>>> = MutableLiveData<Array<Array<Int>>>()
    override val changeMapLiveData: MutableLiveData<Array<Array<Int>>> = MutableLiveData<Array<Array<Int>>>()

    init {
        repository.setOnChangeMapListener {
            changeMapLiveData.value = it
        }
    }

    override fun generateMap(heightCount: Int, widthCount: Int) {
        repository.loadMap(heightCount, widthCount).onEach {
            mapLiveData.value = it
        }.catch { }.launchIn(viewModelScope)
    }

    override fun clickRectPos(x: Int, y: Int, handler: Handler) {
        repository.clickRectPos(x, y, handler)
    }
}





