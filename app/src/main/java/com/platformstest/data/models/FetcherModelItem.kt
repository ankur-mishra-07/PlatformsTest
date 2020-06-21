package com.platformstest.data.models

import android.R
import android.widget.ImageView
import androidx.annotation.Keep
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import androidx.room.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName


@Keep
@Entity(tableName = "people_table")
data class FetcherModelItem(
    @SerializedName("archive_url")
    var archiveUrl: String = "",
    @SerializedName("assignees_url")
    var assigneesUrl: String = "",
    @SerializedName("blobs_url")
    var blobsUrl: String = "",
    @SerializedName("branches_url")
    var branchesUrl: String = "",
    @SerializedName("collaborators_url")
    var collaboratorsUrl: String = "",
    @SerializedName("comments_url")
    var commentsUrl: String = "",
    @SerializedName("commits_url")
    var commitsUrl: String = "",
    @SerializedName("compare_url")
    var compareUrl: String = "",
    @SerializedName("contents_url")
    var contentsUrl: String = "",
    @SerializedName("contributors_url")
    var contributorsUrl: String = "",
    @SerializedName("deployments_url")
    var deploymentsUrl: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("downloads_url")
    var downloadsUrl: String = "",
    @SerializedName("events_url")
    var eventsUrl: String = "",
    @SerializedName("fork")
    var fork: String = "",
    @SerializedName("forks_url")
    var forksUrl: String = "",
    @SerializedName("full_name")
    @ColumnInfo(name = "fullname")
    var fullName: String = "",
    @SerializedName("git_commits_url")
    var gitCommitsUrl: String = "",
    @SerializedName("git_refs_url")
    var gitRefsUrl: String = "",
    @SerializedName("git_tags_url")
    var gitTagsUrl: String = "",
    @SerializedName("hooks_url")
    var hooksUrl: String = "",
    @SerializedName("html_url")
    var htmlUrl: String = "",
    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @SerializedName("issue_comment_url")
    var issueCommentUrl: String = "",
    @SerializedName("issue_events_url")
    var issueEventsUrl: String = "",
    @SerializedName("issues_url")
    var issuesUrl: String = "",
    @SerializedName("keys_url")
    var keysUrl: String = "",
    @SerializedName("labels_url")
    var labelsUrl: String = "",
    @SerializedName("languages_url")
    var languagesUrl: String = "",
    @SerializedName("merges_url")
    var mergesUrl: String = "",
    @SerializedName("milestones_url")
    var milestonesUrl: String = "",
    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String = "",
    @SerializedName("node_id")
    @ColumnInfo(name = "usernodeid")
    var nodeId: String = "",
    @SerializedName("notifications_url")
    var notificationsUrl: String = "",
    @SerializedName("owner")
    @Embedded
    @Nullable
    var owner: Owner? = null,
    @SerializedName("private")
    var private_data: String = "",
    @SerializedName("pulls_url")
    var pullsUrl: String = "",
    @SerializedName("releases_url")
    var releasesUrl: String = "",
    @SerializedName("stargazers_url")
    var stargazersUrl: String = "",
    @SerializedName("statuses_url")
    var statusesUrl: String = "",
    @SerializedName("subscribers_url")
    var subscribersUrl: String = "",
    @SerializedName("subscription_url")
    var subscriptionUrl: String = "",
    @SerializedName("tags_url")
    var tagsUrl: String = "",
    @SerializedName("teams_url")
    var teamsUrl: String = "",
    @SerializedName("trees_url")
    var treesUrl: String = "",
    @SerializedName("url")
    var url: String = ""
)

@Keep
data class Owner(
    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatar")
    var avatarUrl: String,
    @SerializedName("events_url")
    @ColumnInfo(name = "eventurlone")
    var eventsurlone: String,
    @SerializedName("followers_url")
    var followersUrl: String,
    @SerializedName("following_url")
    var followingUrl: String,
    @SerializedName("gists_url")
    var gistsUrl: String,
    @SerializedName("gravatar_id")
    var gravatarId: String,
    @SerializedName("html_url")
    @ColumnInfo(name = "htmlUrl1")
    var htmlUrl: String,
    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    var id: Int,
    @SerializedName("login")
    var login: String,
    @SerializedName("node_id")
    var nodeId: String,
    @SerializedName("organizations_url")
    var organizationsUrl: String,
    @SerializedName("received_events_url")
    var receivedEventsUrl: String,
    @SerializedName("repos_url")
    var reposUrl: String,
    @SerializedName("site_admin")
    var siteAdmin: Boolean,
    @SerializedName("starred_url")
    var starredUrl: String,
    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("url")
    @ColumnInfo(name = "urlone")
    var urlone: String
)

// important code for loading image here
@BindingAdapter("avatar")
fun loadImage(imageView: ImageView, imageURL: String?) {
    Glide.with(imageView.context)
        .setDefaultRequestOptions(
            RequestOptions()
                .circleCrop()
        )
        .load(imageURL)
        .placeholder(R.drawable.bottom_bar)
        .into(imageView)
}