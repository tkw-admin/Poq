package com.tecknoworks.poq.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tecknoworks.poq.ActivityScope
import com.tecknoworks.poq.api.RepoRequest
import com.tecknoworks.poq.api.model.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@ActivityScope
class RepositoriesViewModel @Inject constructor(private val repoRequest: RepoRequest) {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    val repositoriesLiveData = MutableLiveData<List<Repository>>()

    fun getRepositoryList() {
        scope.launch {
            // We might need a better error handling
            try {
                val repositoryListResponse = repoRequest.getRepos()
                repositoriesLiveData.postValue(repositoryListResponse.body())
            } catch (e : Exception) {
                Log.d("getRepositories", e.toString())
                repositoriesLiveData.postValue(null)
            }

        }
    }
}
