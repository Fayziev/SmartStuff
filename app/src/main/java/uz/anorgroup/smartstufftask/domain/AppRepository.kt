package uz.anorgroup.smartstufftask.domain

import android.os.Handler
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun loadMap(heightCount: Int, widthCount: Int): Flow<Array<Array<Int>>>

    fun clickRectPos(x: Int, y: Int, handler: Handler)
    fun setOnChangeMapListener(block: (Array<Array<Int>>) -> Unit)
}

