package com.tecknoworks.poq

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tecknoworks.poq.api.RepoRequest
import com.tecknoworks.poq.api.model.RepositoryDTO
import com.tecknoworks.poq.data.RepositoriesViewModel
import com.tecknoworks.poq.data.mapper.ListMapper
import com.tecknoworks.poq.data.mapper.PoqRepositoryMapper
import com.tecknoworks.poq.data.repository.PoqRepositoryRepository
import io.mockk.coEvery
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Response
import javax.inject.Inject


/**
 * Created by Mihai Ionescu on 2020-01-31.
 */

class AppRepositoryInjectTest {

    @Inject
    lateinit var repoRequest: RepoRequest

    @Inject
    lateinit var poqRepositoryMapper: PoqRepositoryMapper

    @Inject
    lateinit var poqRepositoryRepository: PoqRepositoryRepository

    @Inject
    lateinit var viewModel: RepositoriesViewModel

    @get:Rule
    public var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val component = DaggerTestAppComponent.builder()
            .apiModule(TestApiModule())
            .build()
        component.inject(this)
    }

    @Test
    fun testGetRepos() {
        Assert.assertNotNull(repoRequest)

        val repositoryMock = mockRepositoryDTO()

        // Given
        coEvery { repoRequest.getRepos() } returns Response.success(listOf(repositoryMock))

        // When
        val response = runBlocking { repoRequest.getRepos() }

        // Then
        Assert.assertNotNull(response)
        Assert.assertNotNull(response.body())
        Assert.assertNotNull(response.body()!![0])
    }

    @Test
    fun testMapperSingle() {
        Assert.assertNotNull(repoRequest)

        // Given
        coEvery { repoRequest.getRepos() } returns Response.success(listOf(mockRepositoryDTO()))

        // When
        val response = runBlocking { repoRequest.getRepos() }

        // Then
        Assert.assertNotNull(response)
        Assert.assertNotNull(response.body())
        Assert.assertNotNull(response.body()!![0])

        val poqRepository = poqRepositoryMapper.map(response.body()!![0])
        Assert.assertNotNull(poqRepository)
        Assert.assertEquals(1, poqRepository.id)
        Assert.assertEquals("Test name", poqRepository.name)
        Assert.assertEquals("Test description", poqRepository.description)
    }

    @Test
    fun testMapperList() {
        Assert.assertNotNull(repoRequest)

        // Given
        coEvery { repoRequest.getRepos() } returns Response.success(listOf(mockRepositoryDTO()))

        // When
        val response = runBlocking { repoRequest.getRepos() }

        // Then
        Assert.assertNotNull(response)
        Assert.assertNotNull(response.body())
        Assert.assertNotNull(response.body()!![0])

        val repositoryDTOs = response.body()
        val poqRepositories = ListMapper(poqRepositoryMapper).map(repositoryDTOs.orEmpty())
        Assert.assertNotNull(poqRepositories)
        Assert.assertEquals(1, poqRepositories.size)

        val poqRepository = poqRepositories[0]
        Assert.assertNotNull(poqRepository)
        Assert.assertEquals(1, poqRepository.id)
        Assert.assertEquals("Test name", poqRepository.name)
        Assert.assertEquals("Test description", poqRepository.description)
    }

    @Test
    fun testPoqRepositoryRepository() {
        Assert.assertNotNull(repoRequest)

        // Given
        coEvery { repoRequest.getRepos() } returns Response.success(listOf(mockRepositoryDTO()))

        // When
        val response = runBlocking { poqRepositoryRepository.getRepositories() }

        // Then
        Assert.assertNotNull(response)
    }

    @Test
    fun testViewModel() {
        Assert.assertNotNull(repoRequest)

        // Given
        coEvery { repoRequest.getRepos() } returns Response.success(listOf(mockRepositoryDTO()))

        // When
        viewModel.getRepositoryList()
        runBlocking { delay(1000) }

        val liveData = viewModel.repositoriesLiveData.value

        // Then
        Assert.assertNotNull(liveData)
        Assert.assertEquals(1, liveData!!.size)

        val poqRepository = liveData[0]
        Assert.assertNotNull(poqRepository)
        Assert.assertEquals(1, poqRepository.id)
        Assert.assertEquals("Test name", poqRepository.name)
        Assert.assertEquals("Test description", poqRepository.description)
    }

    fun mockRepositoryDTO(): RepositoryDTO {
        return RepositoryDTO(
            archive_url = "",
            archived = false,
            assignees_url = null,
            blobs_url = null,
            branches_url = null,
            clone_url = null,
            collaborators_url = null,
            comments_url = null,
            commits_url = null,
            compare_url = null,
            contents_url= null,
            contributors_url = null,
            created_at = null,
            default_branch = null,
            deployments_url = null,
            description = "Test description",
            disabled = false,
            downloads_url = null,
            events_url = null,
            fork = false,
            forks = 0,
            forks_count = 0,
            forks_url = null,
            full_name = null,
            git_commits_url = null,
            git_refs_url = null,
            git_tags_url = null,
            git_url = null,
            has_downloads = false,
            has_issues = false,
            has_pages = false,
            has_projects = true,
            has_wiki = false,
            homepage = null,
            hooks_url = null,
            html_url = null,
            id = 1,
            issue_comment_url = null,
            issue_events_url = null,
            issues_url = null,
            keys_url = null,
            labels_url = null,
            language = null,
            languages_url = null,
            licenseDTO = null,
            merges_url = null,
            milestones_url = null,
            mirror_url = null,
            name = "Test name",
            node_id = null,
            notifications_url = null,
            open_issues = 0,
            open_issues_count = 0,
            ownerDTO = null,
            permissionsDTO = null,
            `private` = false,
            pulls_url = null,
            pushed_at = null,
            releases_url = null,
            size = 1,
            ssh_url = null,
            stargazers_count = 10,
            stargazers_url = null,
            statuses_url = null,
            subscribers_url = null,
            subscription_url = null,
            svn_url = null,
            tags_url = null,
            teams_url = null,
            trees_url = null,
            updated_at = null,
            url = null,
            watchers = 0,
            watchers_count = 0
        )
    }

}