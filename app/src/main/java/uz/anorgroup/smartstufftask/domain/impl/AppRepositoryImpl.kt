package uz.anorgroup.smartstufftask.domain.impl

import android.os.Handler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.anorgroup.smartstufftask.domain.AppRepository
import kotlin.random.Random

class AppRepositoryImpl : AppRepository {
    private lateinit var map: Array<Array<Int>>
    private var heightCount = 0
    private var widthCount = 0
    private var changeMapListener: ((Array<Array<Int>>) -> Unit)? = null


    override fun loadMap(heightCount: Int, widthCount: Int): Flow<Array<Array<Int>>> = flow {
        this@AppRepositoryImpl.heightCount = heightCount
        this@AppRepositoryImpl.widthCount = widthCount
        map = Array(heightCount) {
            return@Array Array(widthCount) {
                Random.nextInt(0, 2)
            }
        }
        emit(map)
    }.flowOn(Dispatchers.IO)

    override fun clickRectPos(x: Int, y: Int, handler: Handler) {
        if (x < 0 || x >= widthCount) return
        if (y < 0 || y >= heightCount) return
        if (map[x][y] == 1 || map[x][y] == 2) return
        map[x][y] = 2
        changeMapListener?.invoke(map)
        handler.postDelayed({
            clickRectPos(x + 1, y, handler)
            clickRectPos(x - 1, y, handler)
            clickRectPos(x, y - 1, handler)
            clickRectPos(x, y + 1, handler)
        }, 1500)
    }

    override fun setOnChangeMapListener(block: (Array<Array<Int>>) -> Unit) {
        changeMapListener = block
    }
}
