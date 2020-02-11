package com.tecknoworks.poq.data

import androidx.lifecycle.MutableLiveData
import com.tecknoworks.poq.data.model.PoqRepository
import com.tecknoworks.poq.data.repository.PoqRepositoryRepository
import com.tecknoworks.poq.log.PoqLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RepositoriesViewModel @Inject constructor(private val poqRepositoryRepository: PoqRepositoryRepository) {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    val repositoriesLiveData = MutableLiveData<List<PoqRepository>>()

    fun getRepositoryList() {
        scope.launch {
            when (val result = poqRepositoryRepository.getRepositories()) {
                is PoqResult.Success -> {
                    repositoriesLiveData.postValue(result.value)
                }
                is PoqResult.Failure -> {
                    PoqLog.logEvent(result.throwable.message.orEmpty())
                    repositoriesLiveData.postValue(null)
                }
            }
        }
    }
}
