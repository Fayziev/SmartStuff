package uz.anorgroup.smartstufftask.ui.viewmodels

import android.os.Handler
import androidx.lifecycle.LiveData

interface MainViewModel {
    val mapLiveData: LiveData<Array<Array<Int>>>
    val changeMapLiveData: LiveData<Array<Array<Int>>>

    fun generateMap(heightCount: Int, widthCount: Int)
    fun clickRectPos(x: Int, y: Int, handler: Handler)
}
