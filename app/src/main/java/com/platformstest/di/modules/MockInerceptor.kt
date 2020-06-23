package com.platformstest.di.modules

import com.platformstest.BuildConfig
import com.platformstest.common.AppConstants
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUri().toString()
            val responseString = when {
                uri.endsWith("starred") -> getListOfReposBeingStarredJson
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(AppConstants.Success)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    ResponseBody.create(
                        "application/json".toMediaTypeOrNull(),
                    responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode")
        }
    }

}

const val getListOfReposBeingStarredJson = """
[{
	"id": 1296269,
	"node_id": "MDEwOlJlcG9zaXRvcnkxMjk2MjY5",
	"name": "Hello-World",
	"full_name": "octocat/Hello-World",
	"private": false,
	"html_url": "https://github.com/octocat/Hello-World",
	"description": "This your first repo!",
	"fork": false,
	"languages_url": "http://api.github.com/repos/octocat/Hello-World/languages",
	"stargazers_count": 80,
	"watchers_count": 80,
	"pushed_at": "2011-01-26T19:06:43Z",
	"created_at": "2011-01-26T19:01:12Z",
	"updated_at": "2011-01-26T19:14:43Z",
	"subscribers_count": 42
}]
"""