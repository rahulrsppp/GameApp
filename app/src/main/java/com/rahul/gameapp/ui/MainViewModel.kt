package com.rahul.gameapp.ui

import android.app.Application
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rahul.gameapp.R
import com.rahul.gameapp.utils.Event
import kotlinx.coroutines.launch
import java.util.*


class MainViewModel(private val app: Application) : AndroidViewModel(app) {

    /* private val _scoreLiveData  = MutableLiveData<String>()
     val scoreLiveData : LiveData<String>
         get() = _scoreLiveData
 */
    private var _scoreLiveData = ObservableField<String>()
    val scoreLiveData : ObservableField<String>
        get() = _scoreLiveData


    private val _gameOverEvent = MutableLiveData<Event<Int>>()
    val gameOverEvent: LiveData<Event<Int>> = _gameOverEvent

    private val _colorUpdate = MutableLiveData<Event<Int>>()
    val colorUpdate: LiveData<Event<Int>> = _colorUpdate


    private val _colorRed = MutableLiveData<Int>()
    val colorRed: LiveData<Int>
        get() = _colorRed

    private val _colorBlue = MutableLiveData<Int>()
    val colorBlue: LiveData<Int>
        get() = _colorBlue

    private val _colorYellow = MutableLiveData<Int>()
    val colorYellow: LiveData<Int>
        get() = _colorYellow

    private val _colorGreen = MutableLiveData<Int>()
    val colorGreen: LiveData<Int>
        get() = _colorGreen

    private lateinit var currentStatus: String
    private var isBusy: Boolean = false
    private var isStarting: Boolean = false
    private val arrColor : List<String> = listOf("RED","BLUE","YELLOW","GREEN")
    private var count : Int=0
    private var random : Random = Random()
    private var timer: Timer? = Timer()


    init {
        initView()
        turnInToGray()
    }

    private fun initView(){
        _colorRed.value = ResourcesCompat.getColor(app.resources, R.color.red,null)
        _colorBlue.value = ResourcesCompat.getColor(app.resources,R.color.colorPrimaryDark,null)
        _colorYellow.value = ResourcesCompat.getColor(app.resources,R.color.yellow,null)
        _colorGreen.value = ResourcesCompat.getColor(app.resources,R.color.green, null)
        count = 0
        _scoreLiveData.set("Score: $count")
        isStarting = true
    }

    fun retry(){
        timer = Timer()
        initView()
        turnInToGray()
    }

    fun onRedViewClick(){
        if(!isBusy){
            isBusy = true
            if(currentStatus.equals(arrColor.get(0))){
                setData()
            }else{
                gameOver()
            }
        }
    }

    fun onBlueViewClick(){
        if(!isBusy){
            isBusy = true
            if(currentStatus.equals(arrColor.get(1))){
                setData()
            }else{
                gameOver()
            }
        }
    }

    fun onYellowViewClick(){
        if(!isBusy){
            isBusy = true
            if(currentStatus.equals(arrColor.get(2))){
                setData()
            }else{
                gameOver()
            }
        }
    }

    fun onGreenViewClick(){
        if(!isBusy){
            isBusy = true
            if(currentStatus.equals(arrColor.get(3))){
                setData()
            }else{
                gameOver()
            }
        }
    }

    private fun gameOver() {
        _gameOverEvent.value = Event(count)
        timer?.cancel()
    }

    private fun turnInToGray() {
        viewModelScope.launch {
            timer?.apply {
                scheduleAtFixedRate(object : TimerTask() {
                    override fun run() {
                        viewModelScope.launch {
                            isBusy = false
                            val i = random.nextInt(4)
                            _colorUpdate.value = Event(i)
                            currentStatus = arrColor[i]
                            System.out.println("::::: Timer 1")

                        }
                    }
                }, 0, 1000) }
        }
    }

    private fun setData() {
        count++
        _scoreLiveData.set("Score: $count")
    }
}